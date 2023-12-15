import java.text.DecimalFormat;
import java.util.List;

public class Klient {

        private String imie;
        private String nazwisko;
        private String login;
        private String numer_telefonu;
        final Koszyk koszyk;

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

        public List<Produkt> sprawdzKoszyk() {
            if (koszyk.listaProduktow.size() == 0) {
                System.out.println("PUSTY KOSZYK");
            }
            return koszyk.listaProduktow;
        }

        public void dodajProduktDoKoszyka(Produkt produkt) {
            koszyk.dodajDoKoszyka(produkt);
        }

        public void usunProduktZKoszyka(Produkt produkt) {koszyk.dodajDoKoszyka(produkt);}

}
