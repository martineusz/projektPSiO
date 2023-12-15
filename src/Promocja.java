import java.util.ArrayList;
import java.util.List;

public class Promocja implements Podmiot{

    List<Klient> obserwatorzy = new ArrayList<>();
    void ustawPromocjeNaProdukty(){
        powiadomKlienta();
    }
    @Override
    public void dodajKlienta(Klient klient) {
        if(!obserwatorzy.contains(klient))
            obserwatorzy.add(klient);
    }

    @Override
    public void usunKlienta(Klient klient) {
        if(obserwatorzy.contains(klient))
           obserwatorzy.remove(klient);
    }

    @Override
    public void powiadomKlienta() {
        for(Klient i:obserwatorzy){
            i.powiadom();
        }
    }
}
