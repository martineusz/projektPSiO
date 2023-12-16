import java.util.ArrayList;

public class Koszyk {
    private double wartoscZamowienia;
    private String adresDostawy;
    private String sposobDostawy;
    private ArrayList<Produkt> listaProduktow;
    private PlaceniaStrategia placenieStrategia;
    private DostawaStrategia dostawaStrategia;


    public void dodajProdukt(Produkt produkt){
        listaProduktow.add(produkt);
    }

    public void usunProdukt(Produkt produkt){
        listaProduktow.remove(produkt);
    }

    public void ustawMetodePlatnosci(){

    }

    public void ustawMetodeDostawy(){

    }

    public void zlozZamowienie(){

    }

}
