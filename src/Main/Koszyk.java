package Main;

import DostawaStrategia.DostawaStrategia;

import java.util.ArrayList;

public class Koszyk {
    private double wartoscZamowienia;
    private ArrayList<Produkt> listaProduktow = null;
    private PlaceniaStrategia placenieStrategia; // = new PlacenieStrategia();
    private DostawaStrategia dostawaStrategia; // -||-||-||-||-||-||-||-||-

    public Koszyk(ArrayList<Produkt> listaProduktow) {
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

    }

}
