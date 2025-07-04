package code.Main;


import code.DostawaStrategia.DostawaStrategia;
import code.PlacenieStrategia.PlacenieStrategia;
import code.PlacenieStrategia.placBlikiem;
import code.PlacenieStrategia.placKarta;
import code.Produkt.Produkt;
import code.inputValidate.ZlyAdresException;

import java.io.Serializable;
import java.util.ArrayList;

public class Koszyk implements Serializable {
    private float wartoscZamowienia;
    private static final long serialVersionUID = 5580514503487693430L;
    private PlacenieStrategia placenieStrategia;
    private DostawaStrategia dostawaStrategia;
    private String adres;
    private ArrayList<ProduktWKoszyku> produktyWKoszyku;

    public Koszyk() {
        this.produktyWKoszyku = new ArrayList<>();
    }

    public float getWartoscZamowienia() {
        wartoscZamowienia = 0.00f;
        if(produktyWKoszyku != null){
            for (ProduktWKoszyku produktWKoszyku : produktyWKoszyku) {
                wartoscZamowienia += produktWKoszyku.getProdukt().getCena()*produktWKoszyku.getIloscWKoszyku();
            }
        }
        wartoscZamowienia =(float) Math.floor(wartoscZamowienia*100f)/100f;
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
                return;
            }
        }
    }

    public void setWartoscZamowienia(float wartoscZamowienia) {
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
            dostawaStrategia.dodajKoszt(this);

            if(placenieStrategia instanceof placBlikiem) {
                placenieStrategia.wprowadzDane(kodBlik);
            } else if(placenieStrategia instanceof placKarta){
                placenieStrategia.wprowadzDane(numerKarty,dataWygasniecia,cvv,imie,nazwisko);
            } else {
                return false;
            }
            if (placenieStrategia.plac()) {
                dostawaStrategia.wyslijPaczke(adres);
                return true;
            }
            else {
                return false;
            }
        }
        else {
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
            //JOptionPane.showMessageDialog(null, e1.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return adres;
    }

    public PlacenieStrategia getPlacenieStrategia() {
        return placenieStrategia;
    }

    public void setPlacenieStrategia(PlacenieStrategia placenieStrategia) {
        this.placenieStrategia = placenieStrategia;
    }

    public DostawaStrategia getDostawaStrategia() {
        return dostawaStrategia;
    }

    public void setDostawaStrategia(DostawaStrategia dostawaStrategia) {
        this.dostawaStrategia = dostawaStrategia;
    }
}
