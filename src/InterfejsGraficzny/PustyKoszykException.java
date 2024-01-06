package InterfejsGraficzny;

import java.io.File;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Main.*;

public class PustyKoszykException extends Exception{

    public PustyKoszykException(String message){
        super(message);
    }

    public static void checkIfEmpty(Koszyk koszyk) throws PustyKoszykException {
        if(koszyk.getListaProduktow().isEmpty()){
            throw new PustyKoszykException("Koszyk jest pusty");
        }
    }
}
