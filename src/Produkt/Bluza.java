package Produkt;

public class Bluza extends Produkt{
    private String rozmiarBluzy;
    private boolean czyZKapturem;
    private String dekoltBluzy;
    String krojBluzy;

    public Bluza(String idProduktu, double cena, String nazwa, int iloscWMagazynie, String opis, String material, String kolor, String marka, String krajPochodenia, String rozmiarBluzy, boolean czyZKapturem, String dekoltBluzy, String krojBluzy) {
        super(idProduktu, cena, nazwa, iloscWMagazynie, opis, material, kolor, marka, krajPochodenia);
        this.rozmiarBluzy = rozmiarBluzy;
        this.czyZKapturem = czyZKapturem;
        this.dekoltBluzy = dekoltBluzy;
        this.krojBluzy = krojBluzy;
    }

    public String getRozmiarBluzy() {
        return rozmiarBluzy;
    }

    public void setRozmiarBluzy(String rozmiarBluzy) {
        this.rozmiarBluzy = rozmiarBluzy;
    }

    public boolean getCzyZKapturem() {
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
                "Rozmiar bluzy: " + rozmiarBluzy +
                "Czy z kapturem: " + czyZKapturem +
                "Dekolt bluzy: " + dekoltBluzy +
                "Kroj bluzy: " + krojBluzy;
    }
}
