package code.PlacenieStrategia;

import code.inputValidate.PlatnoscException;

import java.io.Serializable;
import javax.swing.*;

public class placBlikiem implements PlacenieStrategia, Serializable {
    private String kodBlik;
    private static final long serialVersionUID = -4547249249674211279L;

    @Override
    public boolean plac() {
        try{
            PlatnoscException.checkBlik(kodBlik);
            return true;
        }catch (PlatnoscException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Błędny kod BLIK", JOptionPane.ERROR_MESSAGE);
            return false;
        }
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