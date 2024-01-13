package code.Main;




import code.DostawaStrategia.DostawaStrategia;
import code.PlacenieStrategia.PlacenieStrategia;
import code.PlacenieStrategia.placBlikiem;
import code.PlacenieStrategia.placKarta;
import code.Produkt.Produkt;
import code.inputValidate.ZlyAdresException;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Koszyk implements Serializable {
    private double wartoscZamowienia;
    private static final long serialVersionUID = 5580514503487693430L;
    private ArrayList<Produkt> listaProduktow = null;
    private PlacenieStrategia placenieStrategia; // = new PlacenieStrategia();
    private DostawaStrategia dostawaStrategia; // -||-||-||-||-||-||-||-||-
    private String adres;
    //private Map<Produkt,Integer>

    public Koszyk() {
        this.listaProduktow = new ArrayList<Produkt>();
    }

    public double getWartoscZamowienia() {
        wartoscZamowienia = 0.00;
        for (Produkt produkt : getListaProduktow()) {
            wartoscZamowienia += produkt.getCena();
        }
        return wartoscZamowienia;
    }

    public void setWartoscZamowienia(double wartoscZamowienia) {
        this.wartoscZamowienia = wartoscZamowienia;
    }

    public boolean czyZawieraProdukt(Produkt produkt) {
    	return listaProduktow.contains(produkt);
    }

    public void dodajProdukt(Produkt produkt){
        listaProduktow.add(produkt);
    }

    public void usunProdukt(int i){
        listaProduktow.remove(i);
    }

    public void usunProdukt(Produkt produkt) {
    	listaProduktow.remove(produkt);
    }


    public void ustawMetodePlatnosci(PlacenieStrategia placenieStrategia){
        this.placenieStrategia = placenieStrategia;
    }

    public void ustawMetodeDostawy(DostawaStrategia dostawaStrategia){
        this.dostawaStrategia = dostawaStrategia;
    }

    public boolean zrealizujDostawe(String adres, String kodBlik, String numerKarty, String dataWygasniecia,String cvv, String imie, String nazwisko) {
        if(dostawaStrategia != null){
            for (Produkt produkt : this.listaProduktow) {
                /*
                if(!produkt.sprawdzDostepnoscProduktu()) {
                    System.out.println("Zamówienie niezrealizowane");
                    return false;
                }

                 */
            }

            dostawaStrategia.dodajKoszt(this);

            if(placenieStrategia instanceof placBlikiem) {
                placenieStrategia.wprowadzDane(kodBlik);
            } else if(placenieStrategia instanceof placKarta){
                placenieStrategia.wprowadzDane(numerKarty,dataWygasniecia,cvv,imie,nazwisko);
            }

            if (placenieStrategia.plac()) {
                /*for (Produkt produkt : this.listaProduktow) {
                    produkt.setIloscWMagazynie(produkt.getIloscWMagazynie() - 1);
                }*/
                dostawaStrategia.wyslijPaczke(adres);
                //this.listaProduktow.clear();
                return true; //zakreskowane bo impostor to dodal :)
            }
            else {
                System.out.println("Płatność zakończona niepowodzeniem. Proszę zweryfikować poprawność danych.");
                return false;
            }
        }
        else {
            System.out.println("Nie wybrano strategii dostawy!");
            return false;
        }
    }
//
//    public void zlozZamowienie(){
//        System.out.println("Złożono zamówienie na ");
//        for (Produkt produkt:this.listaProduktow) {
//            System.out.println(produkt.toString());
//        }
//        if (dostawaStrategia instanceof DostawaPaczkomat) {
//            System.out.println("Paczka trafi do paczkomatu za 2 dni");
//        } else if (dostawaStrategia instanceof DostawaKurier) {
//            System.out.println("Kurier przywiezie paczkę do wskazanego adresu za 3 dni");
//        }
//    }

    public void sprawdzZawartosc() {
        if (listaProduktow.size() == 0) {
            System.out.println("PUSTY KOSZYK");
        } else {
            for (int i = 0; i < listaProduktow.size(); i++) {
                System.out.println((i+1) + ".");
                //System.out.println(listaProduktow.get(i).toString());
                System.out.println("ID produktu: " + listaProduktow.get(i).getIdProduktu());
                System.out.println("Nazwa produktu: " + listaProduktow.get(i).getNazwa()+"\n");
            }
        }
        System.out.println("LACZNA CENA: " + getWartoscZamowienia());
    }

/*    public double obliczWartoscZamowienia() {
        double wartoscZamowienia = 0;
        for (Produkt produkt : listaProduktow) {
            wartoscZamowienia += produkt.getCena();
        }
        return wartoscZamowienia;
    }*/

    public boolean czyKoszykMaProdukty() {
        if (this.listaProduktow.size() > 0) {
            return true;
        }
        else {return false;}
    }

    public ArrayList<Produkt> getListaProduktow() {
        return listaProduktow;
    }

    public void setListaProduktow(ArrayList<Produkt> listaProduktow) {
        this.listaProduktow = listaProduktow;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String stworzAdres(String imie, String nazwisko, String kraj, String numerTelefonu, String ulica, String numerDomu, String email, String kodPocztowy, String miejscowosc){
        try {
            ZlyAdresException.checkIfEmpty(imie,nazwisko,kraj,numerTelefonu,ulica,numerDomu,email,kodPocztowy,miejscowosc);
            ZlyAdresException.checkPhoneNumber(numerTelefonu);
            ZlyAdresException.checkEmail(email);
            ZlyAdresException.checkKodPocztowy(kodPocztowy);

            adres = imie +
                    " " + nazwisko +
                    "\n" + ulica +
                    " " + numerDomu +
                    "\n" + kodPocztowy +
                    " " + miejscowosc +
                    " " + kraj;
        }catch (ZlyAdresException e1){
            JOptionPane.showMessageDialog(null, e1.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return adres;
    }
}
