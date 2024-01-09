package DostawaStrategia;
import Main.Koszyk;

import java.io.Serializable;

public class DostawaKurier implements DostawaStrategia, Serializable {
    private String adresKlienta;
    private String numerTelefonuKlienta;
    private boolean statusDostawy;
    private static final long serialVersionUID = 2414353077718088096L;

    public void dodajKoszt(Koszyk koszyk){
            koszyk.setWartoscZamowienia(koszyk.getWartoscZamowienia()+19.99);
    }


    public void wyslijPaczke(String adresKlienta){
        System.out.println("Wysłano paczke na adres: "+ adresKlienta);
    }
}
