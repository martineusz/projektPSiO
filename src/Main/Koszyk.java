package Main;
import Produkt.Produkt;

import DostawaStrategia.DostawaStrategia;
import DostawaStrategia.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Koszyk implements Serializable {
    private double wartoscZamowienia;
    private ArrayList<Produkt> listaProduktow = null;
    //private PlaceniaStrategia placenieStrategia; // = new PlacenieStrategia();
    private DostawaStrategia dostawaStrategia; // -||-||-||-||-||-||-||-||-

    public Koszyk() {
        this.listaProduktow = new ArrayList<Produkt>();
    }

    public double getWartoscZamowienia() {
        return wartoscZamowienia;
    }

    public void setWartoscZamowienia(double wartoscZamowienia) {
        this.wartoscZamowienia = wartoscZamowienia;
    }

    public void dodajProdukt(Produkt produkt){
        listaProduktow.add(produkt);
    }

    public void usunProdukt(Produkt produkt){
        listaProduktow.remove(produkt);
    }

    public void ustawMetodePlatnosci(){
        System.out.println("t");
    }

    public void ustawMetodeDostawy(DostawaStrategia dostawaStrategia){
        this.dostawaStrategia = dostawaStrategia;
    }

    public void zrealizujDostawe(String adres){
        if(dostawaStrategia != null){
            dostawaStrategia.wyslijPaczke(adres);
        }
        else {
            System.out.println("Nie wybrano strategii dostawy!");
        }
    }

    public void zlozZamowienie(){
        System.out.println("Złożono zamówienie na ");
        for (Produkt produkt:this.listaProduktow) {
            System.out.println(produkt.toString());
        }
        if (dostawaStrategia instanceof DostawaPaczkomat) {
            System.out.println("Paczka trafi do paczkomatu za 2 dni");
        } else if (dostawaStrategia instanceof DostawaKurier) {
            System.out.println("Kurier przywiezie paczkę do wskazanego adresu za 3 dni");
        }
    }

    public void sprawdzZawartosc() {
        if (listaProduktow.size() == 0) {
            System.out.println("PUSTY KOSZYK");
        } else {
            for (int i = 0; i < listaProduktow.size(); i++) {
                System.out.println(listaProduktow.get(i).toString());
            }
        }
    }

    public double obliczWartoscZamowienia() {
        double wartoscZamowienia = 0.0;
        for (Produkt produkt : listaProduktow) {
            wartoscZamowienia += produkt.getCena();
        }
        return wartoscZamowienia;
    }

}
