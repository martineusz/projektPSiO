package Obserwator;

import java.util.ArrayList;

public class Promocja implements Podmiot {
	
	String nazwa;
	ArrayList<Obserwator> obserwatorzy = new ArrayList<Obserwator>();
	
	

	public Promocja(String nazwa) {
		this.nazwa = nazwa;
	}

	
	public void ustawPromocjeNaProdukty(ArrayList<Produkt> produkty, float obnizka) {
		for (Produkt produkt : produkty) {
			produkt.setCena(produkt.getCena()*(1-obnizka));
		}
		powiadomObserwatorow();
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
	public void powiadomObserwatorow() {
		for (Obserwator obserwator : obserwatorzy) {
			obserwator.powiadom(nazwa);
		}
		
	}
	
	
	
}
