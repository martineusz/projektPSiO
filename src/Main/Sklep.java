package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Produkt.*;
import Obserwator.*;


public class Sklep {
    private List<Klient> listaKlientow;
    public Klient zalogowanyKlient;
    public Promocja promocja;
    private boolean czyZalogowany;
    private List<Produkt> listaProduktow;

    public Sklep(List<Klient> listaKlientow, Klient zalogowanyKlient, boolean czyZalogowany, List<Produkt> listaProduktow) {
        this.listaKlientow = listaKlientow;
        this.zalogowanyKlient = zalogowanyKlient;
        this.czyZalogowany = czyZalogowany;
        this.listaProduktow = listaProduktow;
        this.promocja = new Promocja();
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
    private static final long serialVersionUID = 1234123563789L;
    public void zalogujSie(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj login: ");
        String login=scan.nextLine();
        System.out.print("Podaj haslo: ");
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
            this.czyZalogowany=false;
            System.out.println("Wylogowano!");
        }
        else{
            System.out.println("Nie jesteś zalogowany.");
        }
    }
    //rejestracja
    public void zarejestruj(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj login : ");
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
        String haslo;
        while(true) {
            System.out.print("Podaj haslo : ");
            haslo = scan.nextLine();
            if (haslo.length() < 8) {
                System.out.println("Haslo musi miec conajmniej 8 znakow!");
            }
            else{
                break;
            }
        }

        String imie;
        do {
            System.out.print("Podaj imie : ");
            imie = scan.nextLine();
        } while (imie.equals(""));

        String nazwisko;
        do {
            System.out.print("Podaj nazwisko : ");
            nazwisko = scan.nextLine();
        }while(nazwisko.equals(""));

        String numerTelefonu;
        while(true) {
            System.out.print("Podaj numer telefonu : ");
            numerTelefonu = scan.nextLine();
            if (!isNumeric(numerTelefonu)||numerTelefonu.length()!= 9) {
                System.out.println("Niepoprawny numer telefonu!");
            }
            else{
                break;
            }
        }


        String adresEmail;
        while(true) {
            System.out.print("Podaj adres Email : ");
            adresEmail=scan.nextLine();
            if (!adresEmail.contains("@") || adresEmail.indexOf("@") == 0 || adresEmail.indexOf("@") == adresEmail.length() - 1) {
                System.out.println("Email nieprawidlowy");
            }else{
                break;
            }
        }

        listaKlientow.add(new Klient(imie, nazwisko, login, haslo, numerTelefonu, adresEmail));
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
    private boolean isNumericF(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    private boolean isBoolean(String str){
        if(str.equals("true")||str.equals("false")){
            return true;
        }else{
            return false;
        }
    }
    public void usunKonto(){
        if(czyZalogowany) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Podaj login");
            String login = scan.nextLine();
            System.out.print("Podaj haslo");
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
    public void dodajProdukt(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Podaj typ produktu do dodania (obuwie, bluza, koszulka, spodnie)");
        String wybor=scan.nextLine();
        switch (wybor){
            case"obuwie":  dodajObuwie();  break;
            case"bluza":   dodajBluze();   break;
            case"koszulka":dodajKoszulke();break;
            case"spodnie": dodajSpodnie(); break;
            default:
                System.out.println("Nieprawidłowy typ, spróbuj ponownie."); dodajProdukt(); break;
        }

    }
    public void dodajObuwie(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Podaj id produktu: ");
        String idProduktu = scan.nextLine();
        for(int i=0; i<listaProduktow.size(); i++){
            if(idProduktu.equals(listaProduktow.get(i).getIdProduktu())){
                System.out.println("To id jest juz uzyte!");
                dodajObuwie();
                return;
            }
        }

        String cena;
        while(true) {
            System.out.print("Podaj cene: ");
            cena = scan.nextLine();
            if(isNumericF(cena)){
                break;
            }else{
                System.out.println("Musi byc liczba!");
            }
        }

        System.out.print("Podaj nazwe produktu: ");
        String nazwa = scan.nextLine();

        String iloscWMagazynie;
        while (true) {
            System.out.print("Podaj ilosc w magazynie: ");
            iloscWMagazynie = scan.nextLine();
            if(isNumeric(iloscWMagazynie)){
                break;
            }else{
                System.out.println("Musi byc liczba calkowita!");
            }
        }

        System.out.print("Podaj opis: ");
        String opis=scan.nextLine();

        System.out.print("Podaj material: ");
        String material=scan.nextLine();

        System.out.print("Podaj kolor: ");
        String kolor=scan.nextLine();

        System.out.print("Podaj nazwe producenta: ");
        String nazwaProducenta=scan.nextLine();

        System.out.print("Podaj kraj pochodzenia: ");
        String krajPochodzenia=scan.nextLine();

        String rozmiarObuwia;
        while(true) {
            System.out.print("Podaj rozmiar obuwia: ");
            rozmiarObuwia = scan.nextLine();
            if(isNumericF(rozmiarObuwia)){
                break;
            }else{
                System.out.println("Musi byc liczba!");
            }

        }

        System.out.print("Podaj typ obuwia: ");
        String typObuwia=scan.nextLine();

        System.out.print("podaj typ podeszwy: ");
        String typPodeszwy=scan.nextLine();


        listaProduktow.add(new Obuwie(idProduktu, Float.parseFloat(cena), nazwa, Integer.parseInt(iloscWMagazynie), opis, material, kolor, new Producent(nazwaProducenta,
                krajPochodzenia), Float.parseFloat(rozmiarObuwia), typObuwia, typPodeszwy));
    }
    public void dodajBluze(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Podaj id produktu: ");
        String idProduktu=scan.nextLine();
        for(int i=0; i<listaProduktow.size(); i++){
            if(idProduktu.equals(listaProduktow.get(i).getIdProduktu())){
                System.out.println("To id jest juz uzyte!");
                dodajBluze();
                return;
            }
        }

        String cena;
        while(true) {
            System.out.print("Podaj cene: ");
            cena = scan.nextLine();
            if(isNumericF(cena)){
                break;
            }else{
                System.out.println("Musi byc liczba!");
            }
        }

        System.out.print("Podaj nazwe produktu: ");
        String nazwa=scan.nextLine();

        String iloscWMagazynie;
        while (true) {
            System.out.print("Podaj ilosc w magazynie: ");
            iloscWMagazynie = scan.nextLine();
            if(isNumeric(iloscWMagazynie)){
                break;
            }else{
                System.out.println("Musi byc liczba calkowita!");
            }
        }

        System.out.print("Podaj opis: ");
        String opis=scan.nextLine();

        System.out.print("Podaj material: ");
        String material=scan.nextLine();

        System.out.print("Podaj kolor: ");
        String kolor=scan.nextLine();

        System.out.print("Podaj nazwe producenta: ");
        String nazwaProducenta=scan.nextLine();

        System.out.print("Podaj kraj pochodzenia: ");
        String krajPochodzenia=scan.nextLine();

        System.out.print("Podaj rozmiar bluzy: ");
        String rozmiarBluzy=scan.nextLine();

        String czyZKapturem;
        while(true) {
            System.out.print("Czy jest z kapturem (true|false): ");
            czyZKapturem = scan.nextLine();
            if(isBoolean(czyZKapturem)){
                break;
            }else{
                System.out.println("Musi byc true lub false");
            }
        }

        System.out.print("Podaj dekolt bluzy: ");
        String dekoltBluzy=scan.nextLine();

        System.out.print("Podaj kroj bluzy: ");
        String krojBluzy=scan.nextLine();


        listaProduktow.add(new Bluza(idProduktu, Float.parseFloat(cena), nazwa, Integer.parseInt(iloscWMagazynie), opis, material, kolor, new Producent(nazwaProducenta,
                krajPochodzenia), rozmiarBluzy, Boolean.parseBoolean(czyZKapturem), dekoltBluzy, krojBluzy));
    }
    public void dodajKoszulke(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Podaj id produktu: ");
        String idProduktu=scan.nextLine();
        for(int i=0; i<listaProduktow.size(); i++){
            if(idProduktu.equals(listaProduktow.get(i).getIdProduktu())){
                System.out.println("To id jest juz uzyte!");
                dodajKoszulke();
                return;
            }
        }

        String cena;
        while(true) {
            System.out.print("Podaj cene: ");
            cena = scan.nextLine();
            if(isNumericF(cena)){
                break;
            }else{
                System.out.println("Musi byc liczba!");
            }
        }

        System.out.print("Podaj nazwe produktu: ");
        String nazwa=scan.nextLine();

        String iloscWMagazynie;
        while (true) {
            System.out.print("Podaj ilosc w magazynie: ");
            iloscWMagazynie = scan.nextLine();
            if(isNumeric(iloscWMagazynie)){
                break;
            }else{
                System.out.println("Musi byc liczba calkowita!");
            }
        }

        System.out.print("Podaj opis: ");
        String opis=scan.nextLine();

        System.out.print("Podaj material: ");
        String material=scan.nextLine();

        System.out.print("Podaj kolor: ");
        String kolor=scan.nextLine();

        System.out.print("Podaj nazwe producenta: ");
        String nazwaProducenta=scan.nextLine();

        System.out.print("Podaj kraj pochodzenia: ");
        String krajPochodzenia=scan.nextLine();

        System.out.print("Podaj rozmiar koszulki: ");
        String rozmiarKoszulki=scan.nextLine();

        System.out.print("Podaj dekolt koszulki: ");
        String dekoltKoszulki=scan.nextLine();

        System.out.print("Podaj kroj koszulki: ");
        String krojKoszulki=scan.nextLine();


        listaProduktow.add(new Koszulka(idProduktu, Float.parseFloat(cena), nazwa, Integer.parseInt(iloscWMagazynie), opis, material, kolor, new Producent(nazwaProducenta, krajPochodzenia),
                rozmiarKoszulki, dekoltKoszulki, krojKoszulki));
    }
    public void dodajSpodnie(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Podaj id produktu: ");
        String idProduktu=scan.nextLine();
        for(int i=0; i<listaProduktow.size(); i++){
            if(idProduktu.equals(listaProduktow.get(i).getIdProduktu())){
                System.out.println("To id jest juz uzyte!");
                dodajSpodnie();
                return;
            }
        }

        String cena;
        while(true) {
            System.out.print("Podaj cene: ");
            cena = scan.nextLine();
            if(isNumericF(cena)){
                break;
            }else{
                System.out.println("Musi byc liczba!");
            }
        }

        System.out.print("Podaj nazwe produktu: ");
        String nazwa=scan.nextLine();

        String iloscWMagazynie;
        while (true) {
            System.out.print("Podaj ilosc w magazynie: ");
            iloscWMagazynie = scan.nextLine();
            if(isNumeric(iloscWMagazynie)){
                break;
            }else{
                System.out.println("Musi byc liczba calkowita!");
            }
        }

        System.out.print("Podaj opis: ");
        String opis=scan.nextLine();

        System.out.print("Podaj material: ");
        String material=scan.nextLine();

        System.out.print("Podaj kolor: ");
        String kolor=scan.nextLine();

        System.out.print("Podaj nazwe producenta: ");
        String nazwaProducenta=scan.nextLine();

        System.out.print("Podaj kraj pochodzenia: ");
        String krajPochodzenia=scan.nextLine();

        System.out.print("Podaj rozmiar spodni: ");
        String rozmiarSpodni=scan.nextLine();

        String dlugoscSpodni;
        while(true) {
            System.out.print("Podaj dlugosc spodni: ");
            dlugoscSpodni = scan.nextLine();
            if(isNumericF(dlugoscSpodni)){
                break;
            }else{
                System.out.println("Musi byc liczba");
            }
        }

        System.out.print("Podaj typ spodni: ");
        String typSpodni=scan.nextLine();

        System.out.print("Podaj kroj spodni: ");
        String krojSpodni=scan.nextLine();


        listaProduktow.add(new Spodnie(idProduktu, Float.parseFloat(cena), nazwa, Integer.parseInt(iloscWMagazynie), opis, material, kolor, new Producent(nazwaProducenta, krajPochodzenia),
                rozmiarSpodni, Float.parseFloat(dlugoscSpodni), typSpodni, krojSpodni));
    }

    public void usunProdukt(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj id produktu ktory chcesz usunac: ");
        String id=scan.nextLine();
        for(int i=0; i<listaProduktow.size(); i++){
            if(listaProduktow.get(i).getIdProduktu().equals(id)){
                listaProduktow.remove(i);
                System.out.println("Produkt zostal usuniety!");
                return;
            }
        }
        System.out.println("Produktu nie ma na liscie.");
    }
    public void wczytajListeKlientow(){
        try (ObjectInputStream odczyt = new ObjectInputStream(new FileInputStream("ListaKlientow.ser"))) {
            Object obj = null;
            while (true) {
                try {
                    obj = odczyt.readObject();
                    listaKlientow.add((Klient) obj);
                } catch (EOFException e) {
                    break;
                }
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public void zapiszListeKlientow(){
        try (ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream("ListaKlientow.ser"))){
            for(int i=0; i<listaKlientow.size(); i++){
                zapis.writeObject(listaKlientow.get(i));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void wczytajListeProduktow(){
        utworzListeDostepnychProduktow();
        try (ObjectInputStream odczyt = new ObjectInputStream(new FileInputStream("ListaProduktow.ser"))){
            Object obj = null;
            while (true) {
                try {
                    obj = odczyt.readObject();

                    listaProduktow.add((Produkt) obj);

                } catch (EOFException e) {
                    break;
                }
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public void zapiszListeProduktow(){
        utworzListeDostepnychProduktow();
        try (ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream("ListaProduktow.ser"))){
            for(int i=0; i<listaProduktow.size(); i++){
                if(listaProduktow.get(i).sprawdzDostepnoscProduktu()){
                    zapis.writeObject(listaProduktow.get(i));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void wypiszWszystkieProdukty() {
        utworzListeDostepnychProduktow();
        for (int i = 0; i < listaProduktow.size(); i++) {
            System.out.println((i+1) + ".");
            System.out.println(listaProduktow.get(i).toString());
        }
    }

    public void utworzListeDostepnychProduktow() {
        List<Produkt> listaDostepnychProduktow = new ArrayList<>();

        for (Produkt produkt : listaProduktow) {
            if (produkt.getIloscWMagazynie() > 0) {
                listaDostepnychProduktow.add(produkt);
            }
        }
        listaProduktow = listaDostepnychProduktow;
    }
}
