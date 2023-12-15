import com.sun.security.auth.module.KeyStoreLoginModule;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Sklep sklep = new Sklep(new ArrayList<>(), null, false, new ArrayList<>());

        sklep.wczytajListeProduktow();
        sklep.wczytajListeKlientow();
        sklep.zarejestruj();

        for (int i=0; i<sklep.getListaKlientow().size(); i++){
            System.out.println(sklep.getListaKlientow().get(i).toString());
        }
        for(Produkt i: sklep.getListaProduktow()){
            System.out.println(((Obuwie) i).toString());
        }

        sklep.zapiszListeKlientow();
        sklep.zapiszListeProduktow();

    }
}