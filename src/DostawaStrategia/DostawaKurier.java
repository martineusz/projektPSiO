package DostawaStrategia;
import Main.Koszyk;
public class DostawaKurier implements DostawaStrategia {
    private String adresKlienta;
    private String numerTelefonuKlienta;
    private boolean statusDostawy;

    public void dodajKoszt(Koszyk koszyk){
        if(koszyk.getWartoscZamowienia()<200){
            koszyk.setWartoscZamowienia(koszyk.getWartoscZamowienia()+19.99);
        }
    }


    public void wyslijPaczke(String adresKlienta){
        System.out.println("Wysłano paczke na adres: "+ adresKlienta);
    }
}
