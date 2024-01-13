package code.Produkt;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public abstract class Produkt implements Serializable {
    private String idProduktu;
    private double cena;
    private String nazwa;
    private String opis;
    private String material;
    private String kolor;
    private Producent producent;
    private ImageIcon icon;
    private TreeMap<String, Integer> rozmiary;

    public Produkt(String idProduktu, double cena, String nazwa, TreeMap rozmiary, String opis, String material,
                   String kolor, Producent producent, ImageIcon icon) {
        this.idProduktu = idProduktu;
        this.cena = cena;
        this.nazwa = nazwa;
        this.rozmiary = rozmiary;
        this.opis = opis;
        this.material = material;
        this.kolor = kolor;
        this.producent = producent;
        this.icon = icon;
    }
    private static final long serialVersionUID = 4362596792216997619L;

    public void zmienRozmiar(String rozmiar, Integer ilosc) {
        rozmiary.put(rozmiar, ilosc);
    }

    public TreeMap<String, Integer> getRozmiary() {
        return rozmiary;
    }

    public ArrayList<String> getRozmiaryAsList() {
        return new ArrayList<>(rozmiary.keySet());
    }

    public boolean sprawdzDostepnosc(String rozmiar) {
        if (rozmiary.get(rozmiar) > 0) return true;
        else return false;
    }

    public void setRozmiary(TreeMap<String, Integer> rozmiary) {
        this.rozmiary = rozmiary;
    }

    public String getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(String idProduktu) {
        this.idProduktu = idProduktu;
    }

    public double getCena() {
        cena = (Math.floor(cena * 100.0) / 100.0);
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getKolor() {
        return kolor;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public Producent getProducent() {
        return producent;
    }

    public void setProducent(Producent producent) {
        this.producent = producent;
    }

    public ImageIcon getIcon(int x, int y) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }


    @Override
    public String toString() {
        return "Produkt {" +
                "ID: '" + idProduktu + '\'' +
                ", cena: " + cena +
                ", nazwa: '" + nazwa + '\'' +
                ", opis: '" + opis + '\'' +
                ", materiał: '" + material + '\'' +
                ", kolor: '" + kolor + '\'' +
                ", producent: " + producent +
                '}';
    }
}