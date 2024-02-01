package code.PlacenieStrategia;

import javax.swing.*;
import java.awt.*;

public interface PlacenieStrategia {
    boolean plac();

    void wprowadzDane(String kodBlik);
    void wprowadzDane(String numerKarty, String dataWygasniecia,String cvv, String imie, String nazwisko);
}
