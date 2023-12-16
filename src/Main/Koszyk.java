package Main;

import java.util.ArrayList;

public class Koszyk {
    private double wartoscZamowienia;
    ArrayList<Produkt> listaProduktow;

    public Koszyk() {
        this.listaProduktow = new ArrayList<>();
    }

    public void dodajDoKoszyka(Produkt produkt) {
        listaProduktow.add(produkt);
    }

    public void usunZKoszyka(Produkt produkt) {
        listaProduktow.remove(produkt);
    }



}
