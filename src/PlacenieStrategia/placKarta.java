package PlacenieStrategia;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class placKarta implements PlacenieStrategia, Serializable {
    private static final long serialVersionUID = -9214117519616244433L;
    String numer;
    String dataWygasniecia;
    String cvv;
    String imie;
    String nazwisko;

    @Override
    public boolean plac() {
        if (numer.length() == 16 && numer.matches("[0-9]+")) {
            if (dataWygasniecia.length() == 4 && dataWygasniecia.matches("[0-9]+")) {
                Date data = new Date();
                
                SimpleDateFormat dataFormat = new SimpleDateFormat("yy");
                String aktualnyRok = dataFormat.format(data);
                int aktualnyRokInt = Integer.parseInt(aktualnyRok);
                
                dataFormat = new SimpleDateFormat("MM");
                String aktualnyMiesiac = dataFormat.format(data);
                int aktualnyMiesiacInt = Integer.parseInt(aktualnyMiesiac);
                
                String miesiac = dataWygasniecia.substring(0, 2);
                int miesiacInt = Integer.parseInt(miesiac);
                String rok = dataWygasniecia.substring(2, 4);
                int rokInt = Integer.parseInt(rok);
                
                if (rokInt > aktualnyRokInt || (rokInt == aktualnyRokInt && miesiacInt >= aktualnyMiesiacInt && miesiacInt <= 12)) {
                    if (cvv.length() == 3 && cvv.matches("[0-9]+")) return true;
                    else return false;
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public void wprowadzDane(JPanel panelPlacenie) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Uzupełnianie danych karty płatniczej:");
        System.out.println("Wprowadź numer karty:");
        numer = scan.nextLine();
        System.out.println("Wprowadź datę ważności (MMYY):");
        dataWygasniecia = scan.nextLine();
        System.out.println("Wprowadź kod CVV:");
        cvv = scan.nextLine();
        System.out.println("Wprowadź imię:");
        imie = scan.nextLine();
        System.out.println("Wprowadź nazwisko:");
        nazwisko = scan.nextLine();
    }
}
