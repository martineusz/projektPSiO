package Obserwator;

import java.io.Serializable;
import java.util.ArrayList;

public class ObserwatorEmail implements Obserwator, Serializable {
	String adresEmailKlienta;
	ArrayList<String> powiadomienia;
	private static final long serialVersionUID = 1760235752643757863L;

	public ObserwatorEmail(String adresEmailKlienta) {
		this.powiadomienia = new ArrayList<String>();
		this.adresEmailKlienta = adresEmailKlienta;
	}

	@Override
	public void powiadom(String nazwa) {
		//wyslanie maila do klienta
		powiadomienia.add(this.adresEmailKlienta + " otrzymal email: " + nazwa);
		System.out.println(this.adresEmailKlienta + " otrzymal email: " + nazwa);
	}

	public String getAdresEmailKlienta() {
		return adresEmailKlienta;
	}

	public void setAdresEmailKlienta(String adresEmailKlienta) {
		this.adresEmailKlienta = adresEmailKlienta;
	}

	public ArrayList<String> getPowiadomienia() {
		return powiadomienia;
	}

	public void setPowiadomienia(ArrayList<String> powiadomienia) {
		this.powiadomienia = powiadomienia;
	}
}
