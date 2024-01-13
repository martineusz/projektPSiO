package code.Obserwator;

import code.Produkt.Produkt;

import java.io.Serializable;
import java.util.ArrayList;

public class Promocja implements Serializable {
    String nazwa;
    float obnizka;
    ArrayList<Produkt> produkty;

    public Promocja(String nazwa, float obnizka, ArrayList<Produkt> produkty) {
        this.nazwa = nazwa;
        this.obnizka = obnizka;
        this.produkty = produkty;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getObnizka() {
        return obnizka;
    }

    public void setObnizka(float obnizka) {
        this.obnizka = obnizka;
    }

    public ArrayList<Produkt> getProdukty() {
        return produkty;
    }

    public void setProdukty(ArrayList<Produkt> produkty) {
        this.produkty = produkty;
    }
}
