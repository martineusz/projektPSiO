package code.Produkt;

import javax.swing.*;
import java.io.Serializable;
import java.util.TreeMap;

public class Obuwie extends Produkt implements Serializable {
    private String typObuwia;
    private String typPodeszwy;

    public Obuwie(String idProduktu, double cena, String nazwa, TreeMap rozmiary, String opis, String material,
                  String kolor, Producent producent, String typObuwia, String typPodeszwy, ImageIcon icon){
        super(idProduktu, cena, nazwa, rozmiary, opis, material, kolor, producent, icon);
        this.typObuwia=typObuwia;
        this.typPodeszwy=typPodeszwy;
    }
    private static final long serialVersionUID = 4206840118419129017L;

    public String getTypObuwia() {
        return typObuwia;
    }

    public void setTypObuwia(String typObuwia) {
        this.typObuwia = typObuwia;
    }

    public String getTypPodeszwy() {
        return typPodeszwy;
    }

    public void setTypPodeszwy(String typPodeszwy) {
        this.typPodeszwy = typPodeszwy;
    }

    @Override
    public String toString() {
        return  super.toString()+
                " Obuwie {" +
                ", typ: '" + typObuwia + '\'' +
                ", typ podeszwy: '" + typPodeszwy + '\'' +
                '}';
    }
}