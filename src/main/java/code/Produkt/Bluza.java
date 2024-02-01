package code.Produkt;

import javax.swing.*;
import java.io.Serializable;
import java.util.TreeMap;

public class Bluza extends Produkt implements Serializable {
    private boolean czyZKapturem;
    private String dekoltBluzy;
    private String krojBluzy;
    
    public Bluza(String idProduktu, float cena, String nazwa, TreeMap rozmiary, String opis, String material,
                 String kolor, Producent producent, boolean czyZKapturem, String dekoltBluzy, String krojBluzy, ImageIcon icon){
        super(idProduktu, cena, nazwa, rozmiary, opis, material, kolor, producent, icon);
        this.czyZKapturem=czyZKapturem;
        this.dekoltBluzy=dekoltBluzy;
        this.krojBluzy=krojBluzy;
    }
    private static final long serialVersionUID = -2576567318809067419L;

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
                ", kaptur: '" + (czyZKapturem ? "tak" : "nie") + '\'' +
                ", dekolt: '" + dekoltBluzy + '\'' +
                ", krój: '" + krojBluzy + '\'' +
                '}';
    }
}
