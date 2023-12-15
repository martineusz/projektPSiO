import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sklep {
    private List<Klient> listaKlientow;
    private Klient zalogowanyKlient;
    private boolean czyZalogowany;
    private List<Produkt> listaProduktow;

    public Sklep(List<Klient> listaKlientow, Klient zalogowanyKlient, boolean czyZalogowany, List<Produkt> listaProduktow) {
        this.listaKlientow = listaKlientow;
        this.zalogowanyKlient = zalogowanyKlient;
        this.czyZalogowany = czyZalogowany;
        this.listaProduktow = listaProduktow;
    }

    public List<Klient> getListaKlientow() {
        return listaKlientow;
    }

    public void setListaKlientow(List<Klient> listaKlientow) {
        this.listaKlientow = listaKlientow;
    }

    public Klient getZalogowanyKlient() {
        return zalogowanyKlient;
    }

    public void setZalogowanyKlient(Klient zalogowanyKlient) {
        this.zalogowanyKlient = zalogowanyKlient;
    }

    public boolean isCzyZalogowany() {
        return czyZalogowany;
    }

    public void setCzyZalogowany(boolean czyZalogowany) {
        this.czyZalogowany = czyZalogowany;
    }

    public List<Produkt> getListaProduktow() {
        return listaProduktow;
    }

    public void setListaProduktow(List<Produkt> listaProduktow) {
        this.listaProduktow = listaProduktow;
    }

    public void zalogujSie(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj login: ");
        String login=scan.nextLine();
        System.out.println("Podaj haslo: ");
        String haslo=scan.nextLine();
        for(int i=0; i<listaKlientow.size(); i++){
            if(login.equals(listaKlientow.get(i).getLogin())&&haslo.equals(listaKlientow.get(i).getHaslo())){
                this.zalogowanyKlient=listaKlientow.get(i);
                this.czyZalogowany=true;
                System.out.println("Zalogowano!");
                return;
            }
        }
        System.out.println("Hasło lub login nieprawidłowe");
    }
    public void wylogujSie(){
        if(czyZalogowany) {
            this.zalogowanyKlient = null;
            System.out.println("Wylogowano!");
        }
        else{
            System.out.println("Nie jesteś zalogowany.");
        }
    }
    //rejestracja
    public void zarejestruj(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj login : ");
        String login=scan.nextLine();
        if(login.equals("")){
            System.out.println("Niepoprawny login");
            zarejestruj();
            return;
        }
        for(int i=0; i<listaKlientow.size(); i++){
            if(login.equals(listaKlientow.get(i).getLogin())){
                System.out.println("Login niedostepny!");
                zarejestruj();
                return;
            }
        }
        System.out.println("Podaj haslo : ");
        String haslo=scan.nextLine();
        if(haslo.length()<8){
            System.out.println("Haslo musi miec conajmniej 8 znakow!");
            zarejestruj();
            return;
        }
        System.out.println("Podaj imie : ");
        String imie=scan.nextLine();
        if(imie.equals("")){
            zarejestruj();
            return;
        }
        System.out.println("Podaj nazwisko : ");
        String nazwisko=scan.nextLine();
        if(nazwisko.equals("")){
            zarejestruj();
            return;
        }
        System.out.println("Podaj numer telefonu : ");
        String numerTelefonu=scan.nextLine();
        if(numerTelefonu.length()!=9){
            System.out.println("Niepoprawny numer telefonu!");
            zarejestruj();
            return;
        }
        if(!isNumeric(numerTelefonu)){
            System.out.println("Niepoprawny numer telefonu!");
            zarejestruj();
            return;
        }
        System.out.println("Podaj adres Email : ");
        String adresEmail=scan.nextLine();
        if(!adresEmail.contains("@")||adresEmail.indexOf("@")==0||adresEmail.indexOf("@")==adresEmail.length()-1){
            System.out.println("Email nieprawidlowy");
            zarejestruj();
            return;
        }

        listaKlientow.add(new Klient(new Koszyk(),login,haslo,imie, nazwisko,numerTelefonu,
                adresEmail));
        System.out.println("Rejestracja zakończona!");
        zalogowanyKlient=listaKlientow.get(listaKlientow.size()-1);
        czyZalogowany=true;
    }
    private boolean isNumeric(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public void usunKonto(){
        if(czyZalogowany) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Podaj login");
            String login = scan.nextLine();
            System.out.println("Podaj haslo");
            String haslo = scan.nextLine();
                if (login.equals(zalogowanyKlient.getLogin()) && haslo.equals(zalogowanyKlient.getHaslo())) {
                    if (listaKlientow.contains(zalogowanyKlient)) {
                        listaKlientow.remove(zalogowanyKlient);
                    }
                    zalogowanyKlient = null;
                    czyZalogowany=false;
                    System.out.println("Konto zostalo usuniete!");
                }
        }
        else{
            System.out.println("Nie jestes zalogowany!");
        }
    }
    public void dodajProdukt(Produkt produkt){
        if(!listaProduktow.contains(produkt)){
            listaProduktow.add(produkt);
            System.out.println("Dodano produkt do listy!");
        }
        else{
            System.out.println("Produkt znajduje sie juz na liscie!");
        }
    }
    public void usunProdukt(Produkt produkt){
        if(listaProduktow.contains(produkt)){
            listaProduktow.remove(produkt);
            System.out.println("Produkt został usunięty z listy!");
        }
        else{
            System.out.println("Produktu nie ma na liście!");
        }
    }


    public void wczytajListeKlientow(){
        try {
            ObjectInputStream odczyt = new ObjectInputStream(new FileInputStream("ListaKlientow.ser"));
            listaKlientow = (ArrayList<Klient>) odczyt.readObject();

            odczyt.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public void zapiszListeKlientow(){
        try {
            ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream("ListaKlientow.ser"));
            zapis.writeObject(listaKlientow);
            zapis.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void wczytajListeProduktow(){
        try {
            ObjectInputStream odczyt = new ObjectInputStream(new FileInputStream("ListaProduktow.ser"));
            listaProduktow= (List<Produkt>) odczyt.readObject();

            odczyt.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void zapiszListeProduktow(){
        try {
            ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream("ListaProduktow.ser"));
            zapis.writeObject(listaProduktow);

            zapis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
