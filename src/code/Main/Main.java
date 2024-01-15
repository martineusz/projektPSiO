
package code.Main;

import javax.swing.*;
import code.InterfejsGraficzny.Rejestracja;
import code.Obserwator.PromocjaLogika;
import code.Produkt.Produkt;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Sklep sklep = new Sklep(new ArrayList<Klient>(), null, false, new ArrayList<Produkt>());

        JFrame jFrame = new JFrame();
        ImageIcon icon = new ImageIcon("src/resources/Obrazki/logoSklepu.png");
        jFrame.setIconImage(icon.getImage());
        jFrame.setTitle("Sklep");
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        sklep.promocja.wczytajObserwatorowPromocji();
        sklep.promocja.wczytajPromocje();
        sklep.wczytajListeProduktow();
        sklep.wczytajListeKlientow();

        Rejestracja.ShopPage(sklep, jFrame);

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Dziala");
                sklep.zapiszListeProduktow();
                sklep.zapiszListeKlientow();
                PromocjaLogika.zapiszPromocje();
                PromocjaLogika.zapiszObserwatorowPromocji();
                jFrame.setVisible(false);
            }
        });


        Scanner scan = new Scanner(System.in);
        String wybor;
        // int wyborInt;

        // menu();
        Loop: while(scan.hasNextLine()) {
            wybor = scan.nextLine();
            switch (wybor) {
                case "2": // zarejestruj sie
                    Rejestracja.RejestrPage(sklep, jFrame);
                    if (!sklep.isCzyZalogowany()) {
                        break;
                    }
                case "1": //zaloguj się
                    if (!sklep.isCzyZalogowany()) {
                        Rejestracja.ShopPage(sklep, jFrame);
                    }
            }
        }
             /*       if(sklep.isCzyZalogowany()){

                        menuKlientZalogowany();
                        LoopKlient: while (scan.hasNext()) {
                            wybor = scan.nextLine();
                            switch (wybor) {
                                case "1": // dodaj do koszyka
                                    System.out.println("WYBIERZ PRODUKT KTORY CHCESZ KUPIC: ");
                                    sklep.wypiszWszystkieProdukty();
                                    System.out.print("Wybor (0 - cofnij): ");
                                    LoopDodawanie:
                                    while (scan.hasNextLine()) {
                                        if (scan.hasNextInt()) {
                                            wyborInt = scan.nextInt();


                                            switch(wyborInt) {
                                                case 0:
                                                    break LoopDodawanie;
                                                default:
                                                    try {
                                                        sklep.zalogowanyKlient.koszyk.dodajProdukt(sklep.getListaProduktow().get(wyborInt - 1));
                                                    } catch (Exception e) {
                                                        System.out.println("Numer niemożliwy do wybrania");
                                                    }
                                                    break;
                                            }}
                                        else {
                                            System.out.println("Podano nieprawidłową liczbę.");
                                            scan.nextLine();}
                                        System.out.println("WYBIERZ PRODUKT KTORY CHCESZ KUPIC: ");
                                        sklep.wypiszWszystkieProdukty();
                                        System.out.print("Wybor (0 - cofnij): ");
                                    }
                                    //sklep.zalogowanyKlient.koszyk.dodajProdukt();
                                    break;
                                case "2":
                                    koszyk();
                                    Loop2:
                                    while (scan.hasNextLine()) {
                                        wybor = scan.nextLine();
                                        switch (wybor) {
                                            case "1": // usun produkt
                                                System.out.println("USUN WYBRANY PRODUKT: ");
                                                sklep.zalogowanyKlient.koszyk.sprawdzZawartosc();
                                                System.out.print("Wybor(0 - cofnij): ");
                                                LoopUsuwanie:
                                                while (scan.hasNextLine()) {
                                                    if (scan.hasNextInt()) {
                                                        wyborInt = scan.nextInt();

                                                        switch(wyborInt) {
                                                            case 0:
                                                                break LoopUsuwanie;
                                                            default:
                                                                try {
                                                                    sklep.zalogowanyKlient.koszyk.usunProdukt( wyborInt- 1);
                                                                } catch(Exception e) {
                                                                    System.out.println("numer niemozliwy do wybrania");
                                                                }
                                                                break LoopUsuwanie;
                                                        }}
                                                    else {
                                                        System.out.println("Podano nieprawidłową wartość.");
                                                        scan.nextLine();}
                                                }
                                                break;
                                            case "2": // sprawdz zawartosc
                                                sklep.zalogowanyKlient.koszyk.sprawdzZawartosc();
                                                break;
                                            case "3": // zloz zamowienie
                                                if (sklep.zalogowanyKlient.koszyk.czyKoszykMaProdukty()) {
                                                    String adres = null;
                                                    boolean zamowienie = false;
                                                    zamawianieDostawa();
                                                    LoopZamawianie:
                                                    while (scan.hasNext()) {
                                                        wybor = scan.nextLine();
                                                        DecimalFormat decfor = new DecimalFormat("0.00");
                                                        switch (wybor) {
                                                            case "1":
                                                                sklep.zalogowanyKlient.koszyk.ustawMetodeDostawy(new DostawaPaczkomat());
                                                                System.out.println("Koncowy koszt: " + decfor.format((15.99 + sklep.zalogowanyKlient.koszyk.getWartoscZamowienia())));
                                                                System.out.print("Wpisz adres paczkomatu (Wroc - 0): ");
                                                                adres = scan.nextLine();
                                                                if (!Objects.equals(adres, "0")) {
                                                                    zamowienie = true;
                                                                }
                                                                break;
                                                            case "2":
                                                                sklep.zalogowanyKlient.koszyk.ustawMetodeDostawy(new DostawaKurier());
                                                                System.out.println("Koncowy koszt: "+ decfor.format((19.99 + sklep.zalogowanyKlient.koszyk.getWartoscZamowienia())));
                                                                System.out.print("Wpisz adres zamieszkania (Wroc - 0): ");
                                                                adres = scan.nextLine();
                                                                if (!Objects.equals(adres, "0")) {
                                                                    zamowienie = true;
                                                                }
                                                                break;
                                                            case "3":
                                                                break LoopZamawianie;
                                                        }
                                                        if (zamowienie) {
                                                           zamawianiePlatnosc();
                                                            while (scan.hasNext()) {
                                                                wybor = scan.nextLine();
                                                                switch (wybor) {
//                                                                    case "1":
//                                                                        sklep.zalogowanyKlient.koszyk.ustawMetodePlatnosci(new placBlikiem());
//                                                                        sklep.zalogowanyKlient.koszyk.zrealizujDostawe(adres);
//                                                                        break LoopZamawianie;
//                                                                    case "2":
//                                                                        sklep.zalogowanyKlient.koszyk.ustawMetodePlatnosci(new placKarta());
//                                                                        sklep.zalogowanyKlient.koszyk.zrealizujDostawe(adres);
//                                                                        break LoopZamawianie;
//                                                                    case "3":
//                                                                        break LoopZamawianie;
                                                                }
                                                                zamawianiePlatnosc();
                                                            }
                                                        }
                                                        else zamawianieDostawa();
                                                    }
                                                    break;
                                                }
                                                else {
                                                    System.out.println("W koszyku nie ma produktów");
                                                    break;
                                                }
                                            case "4": //
                                                break Loop2;
                                        }
                                        koszyk();
                                    }
                                    break;
                                case "3": // promocje
                                    promocje();
                                    Loop3:
                                    while (scan.hasNext()) {
                                        wybor = scan.nextLine();
                                        switch (wybor) {
                                            case "1": //zapisz na promocje
                                                promocjeZapisz();
                                                wybor = scan.nextLine();
                                                switch(wybor) {
                                                    case "1":
                                                        sklep.zalogowanyKlient.zapiszNaPromocje(sklep.promocja, "sms");
                                                        break;
                                                    case "2":
                                                        sklep.zalogowanyKlient.zapiszNaPromocje(sklep.promocja, "email");
                                                        break;
                                                    case "3":
                                                        break;
                                                    default:
                                                        System.out.println("nieprawidlowa opcja");
                                                }
                                                break;
                                            case "2": //wypisz z promocji
                                                sklep.zalogowanyKlient.wypiszZPromocji(sklep.promocja);
                                                break;

                                            case "3": //Wroc
                                                break Loop3;
                                        }
                                        promocje();
                                    }
                                    break;

                                case "4":
                                    sklep.wylogujSie();
                                    break LoopKlient;
                            }
                            menuKlientZalogowany();
                        }
                    }


                    break;
                case "3":
                    admin();
                    GUIadmin.openAdmin(sklep, jFrame);
                    LoopAdmin:
                    while (scan.hasNext()) {
                        wybor = scan.nextLine();
                        switch (wybor) {
                            case "1":
                                System.out.println("WYBIERZ PRODUKT KTORY CHCESZ PRZECENIC: ");
                                sklep.wypiszWszystkieProdukty();
                                System.out.print("Wybor (0 - cofnij): ");
                                LoopDodajPromocje:
                                while (scan.hasNextLine()) {
                                    if (scan.hasNextInt()) {
                                        wyborInt = scan.nextInt();
                                        switch(wyborInt) {
                                            case 0:
                                                break LoopDodajPromocje;
                                            default:
                                                try {
                                                    sklep.promocja.ustawPromocjeNaProdukt(sklep.getListaProduktow().get(wyborInt - 1), 0.1F, "Obnizka default");
                                                } catch(Exception e) {
                                                    System.out.println("numer niemozliwy do wybrania");
                                                }
                                                break;
                                        }}
                                    else {
                                        System.out.println("Podano nieprawidłową wartość.");
                                        scan.nextLine();}
                                    System.out.println("WYBIERZ PRODUKT KTORY CHCESZ PRZECENIC: ");
                                    sklep.wypiszWszystkieProdukty();
                                    System.out.print("Wybor (0 - cofnij): ");
                                }
                                break;
                            case "2":

                                break;
                            case "3":

                                break;
                            case "4":
                                break LoopAdmin;
                        }
                        admin();
                    }
                    break;
                case "koniec": //wyjdz z programu
                    break Loop;
                default:
                    System.out.println("Wprowadzono nieprawidlowa opcje!");;
            }
            menu();
        }*/

    }

//    public static void menu(){
//        System.out.println("\nWybierz opcje: ");
//        System.out.println("1. Zaloguj sie");
//        System.out.println("2. Zarejestruj sie");
//        System.out.println("3. Admin");
//        System.out.println("Napisz 'koniec' aby zakonczyc");
//        System.out.print("Wybor: ");
//    }
//
//    public static void menuKlientZalogowany(){
//        System.out.println("\nMENU Wybierz opcje: ");
//        System.out.println("1. Dodaj do koszyka");
//        System.out.println("2. Koszyk");
//        System.out.println("3. Promocje");
//        System.out.println("4. Wyloguj sie");
//        System.out.print("Wybor: ");
//    }
//
//    public static void koszyk(){
//        System.out.println("\nKOSZYK Wybierz opcje: ");
//        System.out.println("1. Usun z koszyka");
//        System.out.println("2. Sprawdz zawartosc koszyka");
//        System.out.println("3. Zloz zamowienie ");
//        System.out.println("4. Wroc");
//        System.out.print("Wybor: ");
//    }
//
//    public static void promocje(){
//        System.out.println("\nPROMOCJE Wybierz opcje: ");
//        System.out.println("1. Zapisz do promocji");
//        System.out.println("2. Wypisz z promocji");
//        System.out.println("3. Wroc");
//        System.out.print("Wybor: ");
//    }
//
//    public static void promocjeZapisz(){
//        System.out.println("\nZAPISZ NA PROMOCJE: Wybierz opcje: ");
//        System.out.println("1. SMS");
//        System.out.println("2. E-MAIL");
//        System.out.println("3. Wroc");
//        System.out.print("Wybor: ");
//    }
//
//    public static void zamawianieDostawa() {
//        System.out.println("\nSposob dostawy: Wybierz opcje: ");
//        System.out.println("1. Paczkomat (15.99 zl)");
//        System.out.println("2. Kurier (19.99 zl)");
//        System.out.println("3. Wroc");
//        System.out.print("Wybor: ");
//    }
//
//    public static void zamawianiePlatnosc() {
//        System.out.println("\nSposob płatności: Wybierz opcje: ");
//        System.out.println("1. BLIK");
//        System.out.println("2. Karta płatnicza");
//        System.out.println("3. Wroc");
//        System.out.print("Wybor: ");
//    }
//
//    public static void admin() {
//        System.out.println("\nMENU ADMIN: Wybierz opcje: ");
//        System.out.println("1. Dodaj promocje");
//        System.out.println("2. Dodaj produkt do sklepu");
//        System.out.println("3. Usun produkt ze sklepu");
//        System.out.println("4. Wroc");
//        System.out.print("Wybor: ");
//    }
}
