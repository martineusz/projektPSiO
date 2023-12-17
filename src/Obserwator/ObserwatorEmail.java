package Obserwator;

import java.io.Serializable;

public class ObserwatorEmail implements Obserwator, Serializable {
	String adresEmailKlienta;
	private static final long serialVersionUID = 1760235752643757863L;

	public ObserwatorEmail(String adresEmailKlienta) {
		this.adresEmailKlienta = adresEmailKlienta;
	}

	@Override
	public void powiadom(String nazwa) {
		//wyslanie maila do klienta
		System.out.println(this.adresEmailKlienta + " otrzymal email: " + nazwa);
	}

}
