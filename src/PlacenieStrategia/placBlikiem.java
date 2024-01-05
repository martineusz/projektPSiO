package PlacenieStrategia;

import java.io.Serializable;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;

public class placBlikiem implements PlacenieStrategia, Serializable {
    private String kodBlik;
    private static final long serialVersionUID = -4547249249674211279L;

    @Override
    public boolean plac() {
        if (kodBlik.length() == 6 && kodBlik.matches("[0-9]+")) return true;
        else return false;
    }

    @Override
    public void wprowadzDane(String kodBlik) {
        this.kodBlik = kodBlik;
    }

    //USELESS
    @Override
    public void wprowadzDane(String numerKarty, String dataWygasniecia, String cvv, String imie, String nazwisko) {

    }
}