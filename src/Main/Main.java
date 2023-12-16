package Main;

import java.util.ArrayList;
import java.util.Scanner;
import Produkt.*;
import Obserwator.*;

public class Main {
    public static void main(String[] args) {
        Sklep sklep = new Sklep(new ArrayList<Klient>(), null, false, new ArrayList<Produkt>());
        Promocja promocja = new Promocja();

        sklep.wczytajListeProduktow();
        sklep.wczytajListeKlientow();

        Scanner scan = new Scanner(System.in);
        String wybor;
        menu();

        Loop: while(scan.hasNextLine()) {
            wybor = scan.nextLine();
            switch (wybor) {
                case "1": //zaloguj sie
                    sklep.zalogujSie();
                    menuKlientZalogowany();

                    while(scan.hasNext()){
                        wybor = scan.nextLine();
                        switch(wybor){
                            case "1": // dodaj do koszyka

                                break;
                            case "2":
                                koszyk();
                                Loop2: while(scan.hasNextLine()){
                                    wybor = scan.nextLine;
                                    switch (wybor){
                                        case "1": // usun produkt

                                            break;
                                        case "2": // sprawdz zawartosc

                                            break;
                                        case "3": // zloz zamowienie

                                            break;
                                        case "4": //
                                            break Loop2;
                                    }
                                }
                                break;
                            case "3": // promocje
                                promocje();
                                Loop3: while(scan.hasNext()){
                                    wybor=scan.nextLine();
                                    switch (wybor){
                                        case "1":
                                    }
                                }
                                break;

                            case "4":
                                sklep.wylogujSie();
                                break;
                        }
                    }



                    break;
                case "2": // zarejestruj sie
                    sklep.zarejestruj();
                    break;
                case "koniec": //wyjdz z programu
                    break Loop;
                default:
                    System.out.println("Wprowadzono nieprawidlowa opcje!");;
            }
            menu();
        }

        sklep.zapiszListeProduktow();
        sklep.zapiszListeKlientow();
    }

    public static void menu(){
        System.out.println("Wybierz opcje: ");
        System.out.println("1. Zaloguj sie");
        System.out.println("2. Zarejestruj sie");
        System.out.println("Napisz 'koniec' aby zakonczyc");
        System.out.print("Wybor: ");
    }

    public static void menuKlientZalogowany(){
        System.out.println("Wybierz opcje: ");
        System.out.println("1. Dodaj do koszyka");
        System.out.println("2. Koszyk");
        System.out.println("3. Promocje");
        System.out.println("4. Wyloguj sie");
        System.out.print("Wybor: ");
    }

    public static void koszyk(){
        System.out.println("Wybierz opcje: ");
        System.out.println("1. Usun z koszyka");
        System.out.println("2. Sprawdz zawartosc koszyka");
        System.out.println("3. Zloz zamowienie ");
        System.out.println("4. Wroc");
        System.out.print("Wybor: ");
    }

    public static void promocje(){
        System.out.println("Wybierz opcje: ");
        System.out.println("1. Zapisz do promocji");
        System.out.println("2. Wypisz z promocji");
        System.out.println("3. Wroc");
        System.out.print("Wybor: ");
    }
}