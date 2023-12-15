public class Spodnie extends Produkt{
    private String rozmiarSpodni;
    private float dlugoscSpodni;
    private String typSpodni;
    private String krojSpodni;
    
    public Spodnie(String idProduktu, double cena, String nazwa, int iloscWMagazynie, String opis, String material,
                  String kolor, Producent producent, String rozmiarSpodni, float dlugoscSpodni, String typSpodni, String krojSpodni) {
        super(idProduktu, cena, nazwa, iloscWMagazynie, opis, material, kolor, producent);
        this.rozmiarSpodni=rozmiarSpodni;
        this.dlugoscSpodni=dlugoscSpodni;
        this.typSpodni=typSpodni;
        this.krojSpodni=krojSpodni;
    }

    public String getRozmiarSpodni() {
	return rozmiarSpodni;
}

public void setRozmiarSpodni(String rozmiarSpodni) {
	this.rozmiarSpodni=rozmiarSpodni;
}

public float getDlugoscSpodni () {
	return dlugoscSpodni;
}

public void setRozmiarSpodni(float dlugoscSpodni) {
	this.dlugoscSpodni=dlugoscSpodni;
}

public String getTypSpodni() {
	return typSpodni;
}

public void setTypSpodni(String typSpodni) {
	this.typSpodni=typSpodni;
}

public String getKrojSpodni() {
	return krojSpodni;
}

public void setKrojSpodni(String krojSpodni) {
	this.krojSpodni=krojSpodni;
}

    
}
