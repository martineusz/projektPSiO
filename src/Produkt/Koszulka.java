package Produkt;

import javax.print.DocFlavor;

public class Koszulka extends Produkt{
    private String rozmiarKoszulki;
    private String dekoltKoszulki;
    private String krojKoszulki;

    public Koszulka(String idProduktu, double cena, String nazwa, int iloscWMagazynie, String opis, String material, String kolor, Producent producent, String rozmiarKoszulki, String dekoltKoszulki, String krojKoszulki) {
        super(idProduktu, cena, nazwa, iloscWMagazynie, opis, material, kolor, producent);
        this.rozmiarKoszulki = rozmiarKoszulki;
        this.dekoltKoszulki = dekoltKoszulki;
        this.krojKoszulki = krojKoszulki;
    }

    public String getRozmiarKoszulki() {
        return rozmiarKoszulki;
    }

    public void setRozmiarKoszulki(String rozmiarKoszulki) {
        this.rozmiarKoszulki = rozmiarKoszulki;
    }

    public String getDekoltKoszulki() {
        return dekoltKoszulki;
    }

    public void setDekoltKoszulki(String dekoltKoszulki) {
        this.dekoltKoszulki = dekoltKoszulki;
    }

    public String getKrojKoszulki() {
        return krojKoszulki;
    }

    public void setKrojKoszulki(String krojKoszulki) {
        this.krojKoszulki = krojKoszulki;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Rozmiar koszulki: " + rozmiarKoszulki +
                "Dekolt koszulki: " + dekoltKoszulki +
                "Kroj koszulki: " + krojKoszulki;
    }
}
