package InterfejsGraficzny;

import java.util.Objects;

public class DaneProduktuException extends Exception{
    public DaneProduktuException(String message){
        super(message);
    }
    public static void pusteException(String str) throws DaneProduktuException{
        if(Objects.equals(str, "")){
            throw new DaneProduktuException("Pole nie może być puste");
        }
    }
    public static void notNumericException(String str) throws DaneProduktuException{
        if(!str.matches("[0-9]+")){
            throw new DaneProduktuException("Musi być liczbą");
        }
    }

}
