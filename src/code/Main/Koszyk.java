package code.Main;


import code.DostawaStrategia.DostawaStrategia;
import code.PlacenieStrategia.PlacenieStrategia;
import code.PlacenieStrategia.placBlikiem;
import code.PlacenieStrategia.placKarta;
import code.Produkt.Produkt;
import code.inputValidate.ZlyAdresException;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Koszyk implements Serializable {
    private double wartoscZamowienia;
    private static final long serialVersionUID = 5580514503487693430L;
    private PlacenieStrategia placenieStrategia;
    private DostawaStrategia dostawaStrategia;
    private String adres;
    private ArrayList<ProduktWKoszyku> produktyWKoszyku;

    public Koszyk() {
        this.produktyWKoszyku = new ArrayList<>();
    }

    public double getWartoscZamowienia() {
        wartoscZamowienia = 0.00;
        //TODO szubidibaa
//        if(mapaProduktow != null){
//            for (Produkt produkt : this.mapaProduktow.keySet()) {
//                wartoscZamowienia += produkt.getCena()*this.mapaProduktow.get(produkt);
//            }
//        }

        return wartoscZamowienia;
    }

    public ArrayList<ProduktWKoszyku> getProduktyWKoszyku() {
        return produktyWKoszyku;
    }

    public void setProduktyWKoszyku(ArrayList<ProduktWKoszyku> produktyWKoszyku) {
        this.produktyWKoszyku = produktyWKoszyku;
    }

    public void usunProduktZKoszyka(Produkt produkt){
        for (int i = 0; i < produktyWKoszyku.size(); i++) {
            if(produktyWKoszyku.get(i).getProdukt() == produkt){
                produktyWKoszyku.remove(produktyWKoszyku.get(i));
            }
        }
    }

    public void setWartoscZamowienia(double wartoscZamowienia) {
        this.wartoscZamowienia = wartoscZamowienia;
    }

    public void dodajProdukt(Produkt produkt, String rozmiar) {
        produktyWKoszyku.add(new ProduktWKoszyku(produkt, rozmiar, 1));
    }

    public void ustawMetodePlatnosci(PlacenieStrategia placenieStrategia){
        this.placenieStrategia = placenieStrategia;
    }

    public void ustawMetodeDostawy(DostawaStrategia dostawaStrategia){
        this.dostawaStrategia = dostawaStrategia;
    }

    public boolean zrealizujDostawe(String adres, String kodBlik, String numerKarty, String dataWygasniecia,String cvv, String imie, String nazwisko) {
        if(dostawaStrategia != null){
//            TODO: zamienic na nowa liste
//            for (Produkt produkt : this.listaProduktow) {
//
//                if(!produkt.sprawdzDostepnoscProduktu()) {
//                    System.out.println("Zamówienie niezrealizowane");
//                    return false;
//                }
//
//
//            }

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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String stworzAdres(String imie, String nazwisko, String kraj, String numerTelefonu, String ulica, String numerDomu, String email, String kodPocztowy, String miejscowosc){
        try {
            ZlyAdresException.checkIfEmpty(imie,nazwisko,kraj,numerTelefonu,ulica,numerDomu,email,kodPocztowy,miejscowosc);
            ZlyAdresException.checkPhoneNumber(numerTelefonu);
            ZlyAdresException.checkEmail(email);
            ZlyAdresException.checkKodPocztowy(kodPocztowy);

            adres = imie +
                    " " + nazwisko +
                    "\n" + ulica +
                    " " + numerDomu +
                    "\n" + kodPocztowy +
                    " " + miejscowosc +
                    " " + kraj;
        }catch (ZlyAdresException e1){
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return adres;
    }
}
