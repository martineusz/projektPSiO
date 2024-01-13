package code.Produkt;

import javax.swing.*;
import java.io.Serializable;
import java.util.TreeMap;

public class Koszulka extends Produkt implements Serializable {
    private String dekoltKoszulki;
    private String krojKoszulki;

    public Koszulka(String idProduktu, double cena, String nazwa, TreeMap rozmiary, String opis, String material,
                    String kolor, Producent producent, String dekoltKoszulki, String krojKoszulki, ImageIcon icon){
        super(idProduktu, cena, nazwa, rozmiary, opis, material, kolor, producent, icon);
        this.dekoltKoszulki=dekoltKoszulki;
        this.krojKoszulki=krojKoszulki;
    }
    private static final long serialVersionUID = -4985346083138998214L;

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
        return  super.toString()+
                " Koszulka {" +
                ", dekolt: '" + dekoltKoszulki + '\'' +
                ", krój: '" + krojKoszulki + '\'' +
                '}';
    }
}