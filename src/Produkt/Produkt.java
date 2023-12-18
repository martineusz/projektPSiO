package Produkt;
import java.io.Serializable;

public abstract class Produkt implements Serializable {
    private String idProduktu;
    private double cena;
    private String nazwa;
    private int iloscWMagazynie;
    private String opis;
    private String material;
    private String kolor;
    private Producent producent;

    public Produkt(String idProduktu, double cena, String nazwa, int iloscWMagazynie, String opis, String material,
                   String kolor, Producent producent) {
        this.idProduktu = idProduktu;
        this.cena = cena;
        this.nazwa = nazwa;
        this.iloscWMagazynie = iloscWMagazynie;
        this.opis = opis;
        this.material = material;
        this.kolor = kolor;
        this.producent = producent;
    }
    private static final long serialVersionUID = 4362596792216997619L;


    public String getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(String idProduktu) {
        this.idProduktu = idProduktu;
    }

    public double getCena() {
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

    public int getIloscWMagazynie() {
        return iloscWMagazynie;
    }

    public void setIloscWMagazynie(int iloscWMagazynie) {
        this.iloscWMagazynie = iloscWMagazynie;
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
    
    public boolean sprawdzDostepnoscProduktu(){
        return this.iloscWMagazynie>0;
    }
    
    public void zwiekszIlosc(Produkt produkt, int zwiekszIlosc){
        produkt.setIloscWMagazynie(getIloscWMagazynie()+zwiekszIlosc);
    }

    @Override
    public String toString() {
        return "Produkt {" +
                "ID: '" + idProduktu + '\'' +
                ", cena: " + cena +
                ", nazwa: '" + nazwa + '\'' +
                ", ilość w magazynie: " + iloscWMagazynie +
                ", opis: '" + opis + '\'' +
                ", materiał: '" + material + '\'' +
                ", kolor: '" + kolor + '\'' +
                ", producent: " + producent +
                '}';
    }
}