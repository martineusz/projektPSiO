public class Spodnie extends Produkt{
    private String rozmiarSpodni;
    private float dlugoscSpodni;
    private String typSpodni;
    private String krojSpodni;
    public Spodnie(String idProduktu, double cena, String nazwa, int iloscWMagazynie, String opis, String material,
                  String kolor, Producent producent, String rozmiarSpodni, float dlugoscSpodni, String typSpodni, String krojSpodni){
        super(idProduktu, cena, nazwa, iloscWMagazynie, opis, material, kolor, producent);
        this.rozmiarSpodni=rozmiarSpodni;
        this.dlugoscSpodni=dlugoscSpodni;
        this.typSpodni=typSpodni;
        this.krojSpodni=krojSpodni;
    }
}
