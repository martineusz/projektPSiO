package code.Produkt;

import javax.swing.*;
import java.io.Serializable;

public class Obuwie extends Produkt implements Serializable {
    private float rozmiarObuwia;
    private String typObuwia;
    private String typPodeszwy;

    public Obuwie(String idProduktu, double cena, String nazwa, int iloscWMagazynie, String opis, String material,
                  String kolor, Producent producent, float rozmiarObuwia, String typObuwia, String typPodeszwy, ImageIcon icon){
        super(idProduktu, cena, nazwa, iloscWMagazynie, opis, material, kolor, producent, icon);
        this.rozmiarObuwia=rozmiarObuwia;
        this.typObuwia=typObuwia;
        this.typPodeszwy=typPodeszwy;
    }
    private static final long serialVersionUID = 4206840118419129017L;

    public float getRozmiarObuwia() {
        return rozmiarObuwia;
    }

    public void setRozmiarObuwia(float rozmiarObuwia) {
        this.rozmiarObuwia = rozmiarObuwia;
    }

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
                "rozmiar: " + rozmiarObuwia +
                ", typ: '" + typObuwia + '\'' +
                ", typ podeszwy: '" + typPodeszwy + '\'' +
                '}';
    }
}