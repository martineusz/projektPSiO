package code.Produkt;

import javax.swing.*;
import java.io.Serializable;

public class Spodnie extends Produkt implements Serializable {
    private String rozmiarSpodni;
    private float dlugoscSpodni;
    private String typSpodni;
    private String krojSpodni;
    
    public Spodnie(String idProduktu, double cena, String nazwa, int iloscWMagazynie, String opis, String material,
                   String kolor, Producent producent, String rozmiarSpodni, float dlugoscSpodni, String typSpodni, String krojSpodni, ImageIcon icon){
        super(idProduktu, cena, nazwa, iloscWMagazynie, opis, material, kolor, producent, icon);
        this.rozmiarSpodni=rozmiarSpodni;
        this.dlugoscSpodni=dlugoscSpodni;
        this.typSpodni=typSpodni;
        this.krojSpodni=krojSpodni;
    }
    private static final long serialVersionUID = 802590927388564171L;

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

@Override
public String toString() {
    return super.toString()+
            " Spodnie {" +
            "rozmiar: " + rozmiarSpodni +
            ", długość: '" + dlugoscSpodni + '\'' +
            ", typ: '" + typSpodni + '\'' +
            ", krój: '" + krojSpodni + '\'' +
            '}';
}
}