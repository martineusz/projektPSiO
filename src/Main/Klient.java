package Main;

import Obserwator.*;
import Produkt.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

public class Klient implements Serializable {

    private String imie;
    private String nazwisko;
    private String login;
    private String numer_telefonu;
    private String adresEmail;
    private String haslo;
    final Koszyk koszyk;
    private boolean CzyPromocja;
    Obserwator obs;
    private static final long serialVersionUID = 1275997691580326078L;

    public Klient(String imie, String nazwisko, String login, String haslo, String numer_telefonu) {
        this.login = login;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numer_telefonu = numer_telefonu;
        this.haslo = haslo;
        this.koszyk = new Koszyk();
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNumer_telefonu() {
        return numer_telefonu;
    }

    public void setNumer_telefonu(String numer_telefonu) {
        this.numer_telefonu = numer_telefonu;
    }

    public String getAdresEmail() {
        return adresEmail;
    }

    public void setAdresEmail(String adresEmail) {
        this.adresEmail = adresEmail;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getHaslo() {
        return haslo;
    }

// W KOSZYKU!!! NIE USUWAC!!!
//    public void sprawdzKoszyk() {
//        koszyk.SprawdzZawartosc();
//    }
//
//    public void dodajProduktDoKoszyka(Produkt produkt) {
//        koszyk.dodajProdukt(produkt);
//    }
//
//    public void usunProduktZKoszyka(Produkt produkt) {
//        koszyk.dodajProdukt(produkt);
//    }

    public void zapiszNaPromocje(Promocja promocja, String rodzaj) {
        if (CzyPromocja) {
            System.out.println(this.imie + "juz w newsletterze o promocjach");
        } else {
            dodajDoNewslettera(promocja, rodzaj);
        }
    }

    public void wypiszZPromocji(Promocja promocja) {
        if (CzyPromocja) {
            usunZNewslettera(promocja);
        } else {
            System.out.println(this.imie + "nie jest zapisany na promocje");
        }
    }



    private void dodajDoNewslettera(Podmiot podmiot, String rodzaj) {
        switch (rodzaj) {
            case "sms":
                obs = new ObserwatorSMS(this.numer_telefonu);
                podmiot.dodajObserwatora(obs);
                CzyPromocja = true;
                break;
            case "email":
                obs = new ObserwatorEmail(this.adresEmail);
                podmiot.dodajObserwatora(obs);
                CzyPromocja = true;
                break;
            default:
                throw new IllegalArgumentException("zly rodzaj");
        }
    }

    private void usunZNewslettera(Podmiot podmiot) {
        podmiot.usunObserwatora(obs);
    }
}
