package Main;

import java.util.ArrayList;
import java.util.Scanner;
import Produkt.*;
import Obserwator.*;

public class Main {
    public static void main(String[] args) {
        Sklep sklep = new Sklep(new ArrayList<Klient>(), null, false, new ArrayList<Produkt>());
        sklep.wczytajListeProduktow();
        sklep.wczytajListeKlientow();

        Scanner scan = new Scanner(System.in);
        String wybor;
        menu();

        Loop: while(scan.hasNextLine()) {
            wybor = scan.nextLine();
            switch (wybor) {
                case "1":
                    sklep.zalogujSie();

                    break;
                case "2":
                    sklep.zarejestruj();
                    break;
                case "koniec":
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
}