package DostawaStrategia;
import Main.Koszyk;

import java.io.Serializable;

public class DostawaPaczkomat implements DostawaStrategia, Serializable {
    private String adresPaczkomatu;
    private String numerTelefonuKlienta;
    private static final long serialVersionUID = -7116484754427963615L;

    public void dodajKoszt(Koszyk koszyk){
        koszyk.setWartoscZamowienia(koszyk.getWartoscZamowienia()+15.99);
    }


    public void wyslijPaczke(String adresPaczkomatu){
        System.out.println("Wysłano paczke do paczkomatu o adresie: " + adresPaczkomatu);
    }
}
