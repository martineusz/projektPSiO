import java.io.Serializable;

public class Klient implements Obserwator, Serializable {
    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private String numerTelefonu;
    private String adresEmail;
    private Koszyk koszyk = new Koszyk();
    private Obserwator obserwator;

    public Klient(Koszyk koszyk, String login, String haslo, String imie, String nazwisko, String numerTelefonu,
                  String adresEmail) {
        this.koszyk = koszyk;
        this.login = login;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerTelefonu = numerTelefonu;
        this.adresEmail = adresEmail;
    }

    public Koszyk getKoszyk() {
        return koszyk;
    }

    public void setKoszyk(Koszyk koszyk) {
        this.koszyk = koszyk;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
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

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public String getAdresEmail() {
        return adresEmail;
    }

    public void setAdresEmail(String adresEmail) {
        this.adresEmail = adresEmail;
    }

    public Obserwator getObserwator() {
        return obserwator;
    }

    public void setObserwator(Obserwator obserwator) {
        this.obserwator = obserwator;
    }

    @Override
    public void powiadom() {
        System.out.println();
    }
    public void zapisz(Podmiot podmiot){
        podmiot.dodajKlienta(this);
    }

    @Override
    public String toString() {
        return "Klient{" +
                "login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", numerTelefonu='" + numerTelefonu + '\'' +
                ", adresEmail='" + adresEmail + '\'' +
                ", koszyk=" + koszyk +
                ", obserwator=" + obserwator +
                '}';
    }
}
