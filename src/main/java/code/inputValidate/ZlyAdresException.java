package code.inputValidate;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZlyAdresException extends Exception{

    public ZlyAdresException(String message){
        super(message);
    }

    public static void checkIfEmpty(String imie, String nazwisko, String kraj, String numerTelefonu, String ulica, String numerDomu, String email, String kodPocztowy, String miejscowosc) throws ZlyAdresException {

        if(Objects.equals(imie, "") || Objects.equals(nazwisko, "") || Objects.equals(ulica, "") || Objects.equals(numerDomu, "") || Objects.equals(miejscowosc, "") || Objects.equals(kraj, "")){
            throw new ZlyAdresException("Pola nie moga byc puste");
        }
    }

    public static void checkPhoneNumber(String numer) throws ZlyAdresException{
        if(numer.length()!=9||!numer.matches("[0-9]+")) throw new ZlyAdresException("Niepoprawny numer telefonu");
    }
    public static void checkEmail(String mail) throws ZlyAdresException{
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail);
        if(!matcher.matches()) throw new ZlyAdresException("Niepoprawny email");
    }
    public static void checkKodPocztowy(String kod) throws ZlyAdresException{
        String regex = "^\\d{2}-\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(kod);
        if(!matcher.matches()) throw new ZlyAdresException("Niepoprawny kod pocztowy");
    }
}
