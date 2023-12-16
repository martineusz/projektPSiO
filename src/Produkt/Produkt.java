package Produkt;

public abstract class Produkt {
    private String idProduktu;
    private double cena;
    private String nazwa;
    private int iloscWMagazynie;
    private String opis;
    private String material;
    private String kolor;
    private Producent producent = null;

    public Produkt(String idProduktu, double cena, String nazwa, int iloscWMagazynie, String opis, String material, String kolor, String marka, String krajPochodenia) {
        this.idProduktu = idProduktu;
        this.cena = cena;
        this.nazwa = nazwa;
        this.iloscWMagazynie = iloscWMagazynie;
        this.opis = opis;
        this.material = material;
        this.kolor = kolor;
        this.producent = new Producent(marka, krajPochodenia);
    }

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

    public boolean sprawdzDostepnoscProduktu(Produkt produkt, String idProduktu){
        if (produkt.getIdProduktu()==idProduktu){
            return true;
        }
        else{
            return false;
        }
    }

    public void zwiekszIlosc(Produkt produkt, int zwiekszIlosc){
        produkt.setIloscWMagazynie(getIloscWMagazynie()+zwiekszIlosc);
    }

    @Override
    public String toString() {
        super.toString();
        return  "\nNazwa='" + nazwa +
                "\nID produktu='" + idProduktu +
                "\nCena=" + cena +
                "\nIlosc w magazynie=" + iloscWMagazynie +
                "\nOpis='" + opis +
                "\nMaterial='" + material +
                "\nKolor='" + kolor +
                "\nProducent=" + producent.toString();
    }
}
