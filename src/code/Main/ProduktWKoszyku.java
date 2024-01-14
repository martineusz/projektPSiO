package code.Main;

import code.Produkt.Produkt;

import java.io.Serializable;

public class ProduktWKoszyku implements Serializable {
    Produkt produkt;
    String rozmiar;
    int iloscWKoszyku;

    public ProduktWKoszyku(Produkt produkt, String rozmiar, int iloscWKoszyku) {
        this.produkt = produkt;
        this.rozmiar = rozmiar;
        this.iloscWKoszyku = iloscWKoszyku;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public String getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(String rozmiar) {
        this.rozmiar = rozmiar;
    }

    public int getIloscWKoszyku() {
        return iloscWKoszyku;
    }

    public void setIloscWKoszyku(int iloscWKoszyku) {
        this.iloscWKoszyku = iloscWKoszyku;
    }
}
