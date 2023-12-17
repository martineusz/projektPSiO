package PlacenieStrategia;

import java.io.Serializable;
import java.util.Scanner;

public class placBlikiem implements PlacenieStrategia, Serializable {
    private String kodBlik;
    private static final long serialVersionUID = -4547249249674211279L;

    @Override
    public boolean plac() {
        if (kodBlik.length() == 6 && kodBlik.matches("[0-9]+")) return true;
        else return false;
    }

    @Override
    public void wprowadzDane() {
        System.out.println("Wprowadź kod BLIK z aplikacji bankowej:");
        Scanner scan = new Scanner(System.in);
        kodBlik = scan.nextLine();
    }
}
