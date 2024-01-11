package code.Produkt;

import javax.swing.*;
import java.io.Serializable;

public class Bluza extends Produkt implements Serializable {
    private String rozmiarBluzy;
    private boolean czyZKapturem;
    private String dekoltBluzy;
    private String krojBluzy;
    
    public Bluza(String idProduktu, double cena, String nazwa, int iloscWMagazynie, String opis, String material,
                 String kolor, Producent producent, String rozmiarBluzy, boolean czyZKapturem, String dekoltBluzy, String krojBluzy, ImageIcon icon){
        super(idProduktu, cena, nazwa, iloscWMagazynie, opis, material, kolor, producent, icon);
        this.rozmiarBluzy=rozmiarBluzy;
        this.czyZKapturem=czyZKapturem;
        this.dekoltBluzy=dekoltBluzy;
        this.krojBluzy=krojBluzy;
    }
    private static final long serialVersionUID = -2576567318809067419L;

    public String getRozmiarBluzy() {
        return rozmiarBluzy;
    }

    public void setRozmiarBluzy(String rozmiarBluzy) {
        this.rozmiarBluzy = rozmiarBluzy;
    }

    public boolean isCzyZKapturem() {
        return czyZKapturem;
    }

    public void setCzyZKapturem(boolean czyZKapturem) {
        this.czyZKapturem = czyZKapturem;
    }

    public String getDekoltBluzy() {
        return dekoltBluzy;
    }

    public void setDekoltBluzy(String dekoltBluzy) {
        this.dekoltBluzy = dekoltBluzy;
    }

    public String getKrojBluzy() {
        return krojBluzy;
    }

    public void setKrojBluzy(String krojBluzy) {
        this.krojBluzy = krojBluzy;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Bluza {" +
                "rozmiar: " + rozmiarBluzy +
                ", kaptur: '" + (czyZKapturem ? "tak" : "nie") + '\'' +
                ", dekolt: '" + dekoltBluzy + '\'' +
                ", krój: '" + krojBluzy + '\'' +
                '}';
    }
}
