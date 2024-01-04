package Obserwator;

import Produkt.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Promocja implements Podmiot, Serializable {
	ArrayList<Obserwator> obserwatorzy = new ArrayList<Obserwator>();

	public void ustawPromocjeNaProdukt(Produkt produkt, float obnizka, String nazwa) {


		System.out.print("Podaj obnizke (0 - 1): ");
		if(obnizka > 1 || obnizka < 0) {
			System.out.println("bledna obnizka");
			return;
		}

		System.out.print("Podaj nazwe promocji: ");

		produkt.setCena(Math.round(produkt.getCena()*(1-obnizka)*100)/100);

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

	public void wczytajObserwatorowPromocji() {
		try (ObjectInputStream odczyt = new ObjectInputStream(new FileInputStream("PromocjaObserwatorzy.ser"))) {
			Object obj = null;
			while ((obj = odczyt.readObject()) != null) {
				if (obj instanceof ObserwatorEmail) {
					dodajObserwatora((ObserwatorEmail) obj);
				} else if (obj instanceof ObserwatorSMS) {
					dodajObserwatora((ObserwatorSMS) obj);
				}
			}
		} catch (EOFException ignored) {
			// koniec pliku - czyli zakonczyc program
		} catch (IOException | ClassNotFoundException e) {
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
