package inputValidate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlatnoscException extends Exception{

    public PlatnoscException(String message){
        super(message);
    }
    public static void checkBlik(String kod) throws PlatnoscException {


        if(!(kod.length() == 6 && kod.matches("[0-9]+"))){
            throw new PlatnoscException("Nieprawidlowy kod BLIK");
        }
    }
    public static void checkKarta(String numer, String dataWygasniecia, String cvv) throws PlatnoscException {
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
                    if (cvv.length() == 3 && cvv.matches("[0-9]+"));
                    else throw new PlatnoscException("Nieprawidlowe dane");
                }
                else throw new PlatnoscException("Nieprawidlowe dane");
            }
            else throw new PlatnoscException("Nieprawidlowe dane");
        }
        else throw new PlatnoscException("Nieprawidlowe dane");
    }
}
