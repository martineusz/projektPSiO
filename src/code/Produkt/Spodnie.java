package code.Produkt;

import javax.swing.*;
import java.io.Serializable;
import java.util.TreeMap;

public class Spodnie extends Produkt implements Serializable {
    private float dlugoscSpodni;
    private String typSpodni;
    private String krojSpodni;
    
    public Spodnie(String idProduktu, double cena, String nazwa, TreeMap rozmiary, String opis, String material,
                   String kolor, Producent producent, float dlugoscSpodni, String typSpodni, String krojSpodni, ImageIcon icon){
        super(idProduktu, cena, nazwa, rozmiary, opis, material, kolor, producent, icon);
        this.dlugoscSpodni=dlugoscSpodni;
        this.typSpodni=typSpodni;
        this.krojSpodni=krojSpodni;
    }
    private static final long serialVersionUID = 802590927388564171L;

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
            ", długość: '" + dlugoscSpodni + '\'' +
            ", typ: '" + typSpodni + '\'' +
            ", krój: '" + krojSpodni + '\'' +
            '}';
}
}