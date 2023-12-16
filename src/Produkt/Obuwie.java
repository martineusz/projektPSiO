package Produkt;

public class Obuwie extends Produkt {
    private float rozmiarObuwia;
    private String typObuwia;
    private String typPodeszwy;

    public Obuwie(String idProduktu, double cena, String nazwa, int iloscWMagazynie, String opis, String material, String kolor, String marka, String krajPochodenia, float rozmiarObuwia, String typObuwia, String typPodeszwy) {
        super(idProduktu, cena, nazwa, iloscWMagazynie, opis, material, kolor, marka, krajPochodenia);
        this.rozmiarObuwia = rozmiarObuwia;
        this.typObuwia = typObuwia;
        this.typPodeszwy = typPodeszwy;
    }

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
        return super.toString() +   "\nRozmiar obuwia: " + rozmiarObuwia +
                                    "\nTyp obuwia: " + typObuwia +
                                    "\nTyp podeszwy: " + typPodeszwy;
    }
}
