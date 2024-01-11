package code.PlacenieStrategia;

import code.inputValidate.PlatnoscException;

import java.io.Serializable;
import javax.swing.*;

public class placKarta implements PlacenieStrategia, Serializable {
    private static final long serialVersionUID = -9214117519616244433L;
    String numer;
    String dataWygasniecia;
    String cvv;
    String imie;
    String nazwisko;

    @Override
    public boolean plac() {
        try{
            PlatnoscException.checkKarta(numer, dataWygasniecia, cvv);
            return true;
        }catch (PlatnoscException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Błędny dane", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public void wprowadzDane(String numerKarty, String dataWygasniecia,String cvv, String imie, String nazwisko) {
        this.numer = numerKarty;
        this.dataWygasniecia = dataWygasniecia;
        this.cvv = cvv;
        this.imie = imie;
        this. nazwisko = nazwisko;
    }


    //USELESS
    @Override
    public void wprowadzDane(String kodBlik) {

    }
}
