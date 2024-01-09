package Main;
import Produkt.*;

import DostawaStrategia.DostawaStrategia;
import DostawaStrategia.*;
import PlacenieStrategia.*;

import javax.swing.*;
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
        wartoscZamowienia = 0.00;
        for (Produkt produkt : getListaProduktow()) {
            wartoscZamowienia += produkt.getCena();
        }
        return wartoscZamowienia;
    }

    public void setWartoscZamowienia(double wartoscZamowienia) {
        this.wartoscZamowienia = wartoscZamowienia;
    }

    public boolean czyZawieraProdukt(Produkt produkt) {
    	return listaProduktow.contains(produkt);
    }

    public void dodajProdukt(Produkt produkt){
        listaProduktow.add(produkt);
    }

    public void usunProdukt(int i){
        listaProduktow.remove(i);
    }

    public void usunProdukt(Produkt produkt) {
    	listaProduktow.remove(produkt);
    }


    public void ustawMetodePlatnosci(PlacenieStrategia placenieStrategia){
        this.placenieStrategia = placenieStrategia;
    }

    public void ustawMetodeDostawy(DostawaStrategia dostawaStrategia){
        this.dostawaStrategia = dostawaStrategia;
    }

    public boolean zrealizujDostawe(String adres, String kodBlik, String numerKarty, String dataWygasniecia,String cvv, String imie, String nazwisko) {
        if(dostawaStrategia != null){
            for (Produkt produkt : this.listaProduktow) {
                if(!produkt.sprawdzDostepnoscProduktu()) {
                    System.out.println("Zamówienie niezrealizowane");
                    return false;
                }
            }

            dostawaStrategia.dodajKoszt(this);

            if(placenieStrategia instanceof placBlikiem) {
                placenieStrategia.wprowadzDane(kodBlik);
            } else if(placenieStrategia instanceof placKarta){
                placenieStrategia.wprowadzDane(numerKarty,dataWygasniecia,cvv,imie,nazwisko);
            }

            if (placenieStrategia.plac()) {
                /*for (Produkt produkt : this.listaProduktow) {
                    produkt.setIloscWMagazynie(produkt.getIloscWMagazynie() - 1);
                }*/
                dostawaStrategia.wyslijPaczke(adres);
                //this.listaProduktow.clear();
                return true; //zakreskowane bo impostor to dodal :)
            }
            else {
                System.out.println("Płatność zakończona niepowodzeniem. Proszę zweryfikować poprawność danych.");
                return false;
            }
        }
        else {
            System.out.println("Nie wybrano strategii dostawy!");
            return false;
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
        System.out.println("LACZNA CENA: " + getWartoscZamowienia());
    }

/*    public double obliczWartoscZamowienia() {
        double wartoscZamowienia = 0;
        for (Produkt produkt : listaProduktow) {
            wartoscZamowienia += produkt.getCena();
        }
        return wartoscZamowienia;
    }*/

    public boolean czyKoszykMaProdukty() {
        if (this.listaProduktow.size() > 0) {
            return true;
        }
        else {return false;}
    }

    public ArrayList<Produkt> getListaProduktow() {
        return listaProduktow;
    }

    public void setListaProduktow(ArrayList<Produkt> listaProduktow) {
        this.listaProduktow = listaProduktow;
    }
}
