package Main;

import Obserwator.*;

import java.text.DecimalFormat;
import java.util.List;

public class Klient {

        private String imie;
        private String nazwisko;
        private String login;
        private String numer_telefonu;
        private String adresEmail;
        final Koszyk koszyk;
        private boolean CzyPromocja;
        Obserwator obs;

        public Klient(String imie, String nazwisko, String login, String numer_telefonu) {
            this.login = login;
            this.imie = imie;
            this.nazwisko = nazwisko;
            this.numer_telefonu = numer_telefonu;
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

    public List<Produkt> sprawdzKoszyk() {
            if (koszyk.listaProduktow.size() == 0) {
                System.out.println("PUSTY KOSZYK");
            }
            return koszyk.listaProduktow;
        }

        public void dodajProduktDoKoszyka(Produkt produkt) {
            koszyk.dodajProdukt(produkt);
        }

        public void usunProduktZKoszyka(Produkt produkt) {koszyk.dodajProdukt(produkt);}

        public void zapiszNaNewsletter(Promocja promocja, String rodzaj) {
            if(CzyPromocja) {
                System.out.println(this.imie + "juz w promocji");
            }
            else {
                switch(rodzaj) {
                    case "sms":
                        obs = new ObserwatorSMS(this.numer_telefonu);
                        promocja.dodajObserwatora(obs);
                        CzyPromocja = true;
                        break;
                    case "email":
                        obs = new ObserwatorEmail(this.adresEmail);
                        promocja.dodajObserwatora(obs);
                        CzyPromocja = true;
                        break;
                    default:
                        throw new IllegalArgumentException("zly rodzaj");
                }
            }
        }
        public void wypiszZNewslettera(Promocja promocja) {
            if(CzyPromocja) {
                promocja.usunObserwatora(obs);
            }else {
                System.out.println(this.imie + "nie jest zapisany na promocje");
            }
        }
}
