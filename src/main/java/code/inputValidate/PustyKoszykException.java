package code.inputValidate;


import code.Main.Koszyk;

public class PustyKoszykException extends Exception{

    public PustyKoszykException(String message){
        super(message);
    }

    public static void checkIfEmpty(Koszyk koszyk) throws PustyKoszykException {
        if(koszyk.getProduktyWKoszyku().isEmpty()){
            throw new PustyKoszykException("Koszyk jest pusty");
        }
    }
}
