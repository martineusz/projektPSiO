package DostawaStrategia;
import Main.Koszyk;
public class DostawaPaczkomat implements DostawaStrategia {
    private String adresPaczkomatu;
    private String numerTelefonuKlienta;

    public void dodajKoszt(Koszyk koszyk){
        koszyk.setWartoscZamowienia(koszyk.getWartoscZamowienia()+15.99);
    }


    public void wyslijPaczke(String adresPaczkomatu){
        System.out.println("Wysłano paczke do paczkomatu o adresie: " + adresPaczkomatu);
    }
}
