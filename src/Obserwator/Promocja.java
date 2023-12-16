package Obserwator;

import Produkt.*;

import java.io.*;
import java.util.ArrayList;

public class Promocja implements Podmiot, Serializable {
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

	public void wczytajObserwatorowPromocji(){
		try (ObjectInputStream odczyt = new ObjectInputStream(new FileInputStream("PromocjaObserwatorzy.ser"))){
			Object obj = null;
			while (true) {
				try {
					obj = odczyt.readObject();
					if(obj instanceof ObserwatorEmail) {
						dodajObserwatora((ObserwatorEmail) obj);
					}
					else if(obj instanceof ObserwatorSMS){
						dodajObserwatora((ObserwatorSMS) obj);
					}
				} catch (EOFException e) {
					break;
				}
			}

		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void zapiszObserwatorowPromocji(){
		try (ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream("PromocjaObserwatorzy.ser"))){
			for(int i=0; i<obserwatorzy.size(); i++){
				zapis.writeObject(obserwatorzy.get(i));
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
