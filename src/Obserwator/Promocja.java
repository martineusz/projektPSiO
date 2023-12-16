package Obserwator;

import Produkt.*;
import java.util.ArrayList;

public class Promocja implements Podmiot {
	ArrayList<Obserwator> obserwatorzy = new ArrayList<Obserwator>();


	
	public void ustawPromocjeNaProdukty(ArrayList<Produkt> produkty, float obnizka, String nazwa) {
		for (Produkt produkt : produkty) {
			produkt.setCena(produkt.getCena()*(1-obnizka));
		}
		powiadomObserwatorow(nazwa);
	}
	
	@Override
	public void dodajObserwatora(Obserwator obserwator) {
		obserwatorzy.add(obserwator);
	}

	@Override
	public void usunObserwatora(Obserwator obserwator) {
		obserwatorzy.remove(obserwator);
	}

	@Override
	public void powiadomObserwatorow(String nazwa) {
		for (Obserwator obserwator : obserwatorzy) {
			obserwator.powiadom(nazwa);
		}
		
	}
	
	
	
}
