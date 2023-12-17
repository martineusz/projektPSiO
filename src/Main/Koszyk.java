package Main;
import Produkt.Produkt;

import DostawaStrategia.DostawaStrategia;
import DostawaStrategia.*;
import PlacenieStrategia.PlacenieStrategia;
import java.text.DecimalFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Koszyk implements Serializable {
    private double wartoscZamowienia;
    private static final long serialVersionUID = 5580514503487693430L;
    private ArrayList<Produkt> listaProduktow = null;
    private PlacenieStrategia placenieStrategia; // = new PlacenieStrategia();
    private DostawaStrategia dostawaStrategia; // -||-||-||-||-||-||-||-||-

    public Koszyk() {
        this.listaProduktow = new ArrayList<Produkt>();
    }

    public double getWartoscZamowienia() {
        return wartoscZamowienia;
    }

    public void setWartoscZamowienia(double wartoscZamowienia) {
        DecimalFormat df = new DecimalFormat("#.##");
        this.wartoscZamowienia = wartoscZamowienia;
    }

    public void dodajProdukt(Produkt produkt){
        listaProduktow.add(produkt);
    }

    public void usunProdukt(int i){
        listaProduktow.remove(i);
    }

    public void ustawMetodePlatnosci(PlacenieStrategia placenieStrategia){
        this.placenieStrategia = placenieStrategia;
    }

    public void ustawMetodeDostawy(DostawaStrategia dostawaStrategia){
        this.dostawaStrategia = dostawaStrategia;
    }

    public void zrealizujDostawe(String adres) {
        if(dostawaStrategia != null){
            for (Produkt produkt : this.listaProduktow) {
                if(!produkt.sprawdzDostepnoscProduktu()) {
                    System.out.println("Zamówienie niezrealizowane");
                    return;
                }
            }

            dostawaStrategia.dodajKoszt(this);

            placenieStrategia.wprowadzDane();
            if (placenieStrategia.plac()) {
                for (Produkt produkt : this.listaProduktow) {
                    produkt.setIloscWMagazynie(produkt.getIloscWMagazynie() - 1);
                }
                dostawaStrategia.wyslijPaczke(adres);
                this.listaProduktow.clear();
            }
            else {
                System.out.println("Płatność zakończona niepowodzeniem. Proszę zweryfikować poprawność danych.");
            }
        }
        else {
            System.out.println("Nie wybrano strategii dostawy!");
        }
    }
//
//    public void zlozZamowienie(){
//        System.out.println("Złożono zamówienie na ");
//        for (Produkt produkt:this.listaProduktow) {
//            System.out.println(produkt.toString());
//        }
//        if (dostawaStrategia instanceof DostawaPaczkomat) {
//            System.out.println("Paczka trafi do paczkomatu za 2 dni");
//        } else if (dostawaStrategia instanceof DostawaKurier) {
//            System.out.println("Kurier przywiezie paczkę do wskazanego adresu za 3 dni");
//        }
//    }

    public void sprawdzZawartosc() {
        if (listaProduktow.size() == 0) {
            System.out.println("PUSTY KOSZYK");
        } else {
            for (int i = 0; i < listaProduktow.size(); i++) {
                System.out.println((i+1) + ".");
                //System.out.println(listaProduktow.get(i).toString());
                System.out.println("ID produktu: " + listaProduktow.get(i).getIdProduktu());
                System.out.println("Nazwa produktu: " + listaProduktow.get(i).getNazwa()+"\n");
            }
        }
        System.out.println("LACZNA CENA: " + obliczWartoscZamowienia());
    }

    public double obliczWartoscZamowienia() {
        double wartoscZamowienia = 0.0;
        for (Produkt produkt : listaProduktow) {
            wartoscZamowienia += produkt.getCena();
        }
        return wartoscZamowienia;
    }

    public boolean czyKoszykMaProdukty() {
        if (this.listaProduktow.size() > 0) {
            return true;
        }
        else {return false;}
    }
}
