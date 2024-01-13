package code.Main;

import code.Obserwator.Promocja;

import code.Produkt.*;
import code.inputValidate.DaneProduktuException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;


import javax.swing.*;


public class Sklep {
    public ArrayList<Klient> listaKlientow;
    public Klient zalogowanyKlient;
    public Promocja promocja;
    private boolean czyZalogowany;
    private ArrayList<Produkt> listaProduktow;

    public Sklep(ArrayList<Klient> listaKlientow, Klient zalogowanyKlient, boolean czyZalogowany, ArrayList<Produkt> listaProduktow) {
        this.listaKlientow = listaKlientow;
        this.zalogowanyKlient = zalogowanyKlient;
        this.czyZalogowany = czyZalogowany;
        this.listaProduktow = listaProduktow;
        this.promocja = new Promocja();
    }

    public ArrayList<Klient> getListaKlientow() {
        return listaKlientow;
    }

    public void setListaKlientow(ArrayList<Klient> listaKlientow) {
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

    public ArrayList<Produkt> getListaProduktow() {
        return listaProduktow;
    }

    public void setListaProduktow(ArrayList<Produkt> listaProduktow) {
        this.listaProduktow = listaProduktow;
    }

    private static final long serialVersionUID = 1234123563789L;

    public boolean zalogujSie(String login, String haslo) {
        haslo = hashPassword(haslo);
        boolean zalogowano = false;
        for (int i = 0; i < listaKlientow.size(); i++) {
            if (login.equals(listaKlientow.get(i).getLogin()) && haslo.equals(listaKlientow.get(i).getHaslo())) {
                this.zalogowanyKlient = listaKlientow.get(i);
                this.czyZalogowany = true;
                System.out.println("Zalogowano!");
                zalogowano = true;
            }
            else {
                System.out.println("Hasło lub login nieprawidłowe");}
        }
        return zalogowano;
    }

    public void wylogujSie() {
        if (czyZalogowany) {
            this.zalogowanyKlient = null;
            this.czyZalogowany = false;
            System.out.println("Wylogowano!");
        } else {
            System.out.println("Nie jesteś zalogowany.");
        }
    }

    //rejestracja
    public void zarejestruj() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj login : ");
        String login = scan.nextLine();
        if (login.equals("")) {
            System.out.println("Niepoprawny login");
            zarejestruj();
            return;
        }
        for (int i = 0; i < listaKlientow.size(); i++) {
            if (login.equals(listaKlientow.get(i).getLogin())) {
                System.out.println("Login niedostepny!");
                zarejestruj();
                return;
            }
        }
        String haslo;
        while (true) {
            System.out.print("Podaj haslo : ");
            haslo = scan.nextLine();
            if (haslo.length() < 8) {
                System.out.println("Haslo musi miec conajmniej 8 znakow!");
            } else {
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
        } while (nazwisko.equals(""));

        String numerTelefonu;
        while (true) {
            System.out.print("Podaj numer telefonu : ");
            numerTelefonu = scan.nextLine();
            if (!isNumeric(numerTelefonu) || numerTelefonu.length() != 9) {
                System.out.println("Niepoprawny numer telefonu!");
            } else {
                break;
            }
        }


        String adresEmail;
        while (true) {
            System.out.print("Podaj adres Email : ");
            adresEmail = scan.nextLine();
            if (!adresEmail.contains("@") || adresEmail.indexOf("@") == 0 || adresEmail.indexOf("@") == adresEmail.length() - 1) {
                System.out.println("Email nieprawidlowy");
            } else {
                break;
            }
        }

        listaKlientow.add(new Klient(imie, nazwisko, login, haslo, numerTelefonu, adresEmail));
        System.out.println("Rejestracja zakończona!");
        zalogowanyKlient = listaKlientow.get(listaKlientow.size() - 1);
        czyZalogowany = true;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isNumericF(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBoolean(String str) {
        if (str.equals("true") || str.equals("false")) {
            return true;
        } else {
            return false;
        }
    }

    public void usunKonto() {
        if (czyZalogowany) {
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
                czyZalogowany = false;
                System.out.println("Konto zostalo usuniete!");
            }
        } else {
            System.out.println("Nie jestes zalogowany!");
        }
    }

    public void dodajObuwie(String idProduktu, String cena, String nazwa, TreeMap rozmiary, String opis, String material,
                            String kolor, String nazwaProducenta, String krajPochodzenia, String typObuwia,
                            String typPodeszwy, ImageIcon icon) {
        try {
            DaneProduktuException.pusteException(idProduktu);
            Float temp = Float.parseFloat(cena);
            DaneProduktuException.pusteException(nazwa);
            DaneProduktuException.pusteException(opis);
            DaneProduktuException.pusteException(material);
            DaneProduktuException.pusteException(kolor);
            DaneProduktuException.pusteException(nazwaProducenta);
            DaneProduktuException.pusteException(krajPochodzenia);
            DaneProduktuException.pusteException(typObuwia);
            DaneProduktuException.pusteException(typPodeszwy);

            Obuwie obuwie = new Obuwie(idProduktu, temp, nazwa, rozmiary, opis, material, kolor, new Producent(nazwaProducenta,
                    krajPochodzenia), typObuwia, typPodeszwy, icon);

            listaProduktow.add(obuwie);
            JOptionPane.showMessageDialog(null, "Produkt dodany pomyślnie", "ADMIN",
                    JOptionPane.INFORMATION_MESSAGE);

            zapiszListeProduktow();
        }catch (DaneProduktuException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Nieprawidłowe dane", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void dodajBluze(String idProduktu, String cena, String nazwa, TreeMap rozmiary, String opis, String material,
                           String kolor, String nazwaProducenta, String krajPochodzenia, boolean czyZKapturem,
                           String dekoltBluzy, String krojBluzy, ImageIcon icon) {
        try {
            DaneProduktuException.pusteException(idProduktu);
            Float temp = Float.parseFloat(cena);
            DaneProduktuException.pusteException(nazwa);
            DaneProduktuException.pusteException(opis);
            DaneProduktuException.pusteException(material);
            DaneProduktuException.pusteException(kolor);
            DaneProduktuException.pusteException(nazwaProducenta);
            DaneProduktuException.pusteException(krajPochodzenia);
            DaneProduktuException.pusteException(dekoltBluzy);
            DaneProduktuException.pusteException(krojBluzy);

            listaProduktow.add(new Bluza(idProduktu, Float.parseFloat(cena), nazwa, rozmiary, opis, material, kolor, new Producent(nazwaProducenta,
                    krajPochodzenia), czyZKapturem, dekoltBluzy, krojBluzy, icon));
            JOptionPane.showMessageDialog(null, "Produkt dodany pomyślnie", "ADMIN",
                    JOptionPane.INFORMATION_MESSAGE);

            zapiszListeProduktow();
        }catch (DaneProduktuException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Nieprawidłowe dane", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void dodajKoszulke(String idProduktu, String cena, String nazwa, TreeMap rozmiary, String opis, String material,
                              String kolor, String nazwaProducenta, String krajPochodzenia,
                              String dekoltKoszulki, String krojKoszulki, ImageIcon icon) {
        try {
            DaneProduktuException.pusteException(idProduktu);
            Float temp = Float.parseFloat(cena);
            DaneProduktuException.pusteException(nazwa);
            DaneProduktuException.pusteException(opis);
            DaneProduktuException.pusteException(material);
            DaneProduktuException.pusteException(kolor);
            DaneProduktuException.pusteException(nazwaProducenta);
            DaneProduktuException.pusteException(krajPochodzenia);
            DaneProduktuException.pusteException(dekoltKoszulki);
            DaneProduktuException.pusteException(krojKoszulki);

            listaProduktow.add(new Koszulka(idProduktu, Float.parseFloat(cena), nazwa, rozmiary, opis, material, kolor, new Producent(nazwaProducenta, krajPochodzenia), dekoltKoszulki, krojKoszulki, icon));
            JOptionPane.showMessageDialog(null, "Produkt dodany pomyślnie", "ADMIN",
                    JOptionPane.INFORMATION_MESSAGE);

            zapiszListeProduktow();
        }catch (DaneProduktuException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Nieprawidłowe dane", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void dodajSpodnie(String idProduktu, String cena, String nazwa, TreeMap rozmiary, String opis, String material,
                             String kolor, String nazwaProducenta, String krajPochodzenia, String dlugoscSpodni, String typSpodni, String krojSpodni, ImageIcon icon) {
        try {
            DaneProduktuException.pusteException(idProduktu);
            Float temp = Float.parseFloat(cena);
            DaneProduktuException.pusteException(nazwa);
            DaneProduktuException.pusteException(opis);
            DaneProduktuException.pusteException(material);
            DaneProduktuException.pusteException(kolor);
            DaneProduktuException.pusteException(nazwaProducenta);
            DaneProduktuException.pusteException(krajPochodzenia);
            DaneProduktuException.pusteException(typSpodni);
            DaneProduktuException.pusteException(krojSpodni);

            listaProduktow.add(new Spodnie(idProduktu, Float.parseFloat(cena), nazwa, rozmiary, opis, material, kolor, new Producent(nazwaProducenta, krajPochodzenia), Float.parseFloat(dlugoscSpodni), typSpodni, krojSpodni, icon));
            JOptionPane.showMessageDialog(null, "Produkt dodany pomyślnie", "ADMIN",
                    JOptionPane.INFORMATION_MESSAGE);

            zapiszListeProduktow();
        }catch (DaneProduktuException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Nieprawidłowe dane", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void usunProdukt(int i) {
        listaProduktow.remove(i);
    }

    public void wczytajListeKlientow() {
        try (ObjectInputStream odczyt = new ObjectInputStream(new FileInputStream("ListaKlientow.ser"))) {
            Object obj = null;
            while ((obj = odczyt.readObject()) != null) {
                if (obj instanceof Klient) {
                    listaKlientow.add((Klient) obj);
                }
            }
        } catch (EOFException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void zapiszListeKlientow () {
        try (ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream("ListaKlientow.ser"))) {
            for (int i = 0; i < listaKlientow.size(); i++) {
                zapis.writeObject(listaKlientow.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void wczytajListeProduktow(){
        utworzListeDostepnychProduktow();
        try (ObjectInputStream odczyt = new ObjectInputStream(new FileInputStream("ListaProduktow.ser"))){
            Object obj;
            while ((obj = odczyt.readObject()) != null) {
                Produkt produkt = (Produkt) obj;
                    listaProduktow.add(produkt);

            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public void zapiszListeProduktow(){
        utworzListeDostepnychProduktow();
        try (ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream("ListaProduktow.ser"))){
            for (Produkt produkt : listaProduktow) {
                    zapis.writeObject(produkt);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void wypiszWszystkieProdukty() {
        ArrayList<Produkt> dostepneProdukty = utworzListeDostepnychProduktow();
        for (int i = 0; i < dostepneProdukty.size(); i++) {
            System.out.println((i+1) + ".");
            System.out.println(dostepneProdukty.get(i).toString());
        }
    }

    public ArrayList<Produkt> utworzListeDostepnychProduktow() {
        ArrayList<Produkt> listaDostepnychProduktow = new ArrayList<>();

        for (Produkt produkt : listaProduktow) {
            /*
            if (produkt.getIloscWMagazynie() > 0) {
                listaDostepnychProduktow.add(produkt);
            }

             */
        }
        return listaDostepnychProduktow;
    }

    public static  String hashPassword(String password) {

        String hashedPassword = "";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            hashedPassword = hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hashedPassword;
    }
}
