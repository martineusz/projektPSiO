package Obserwator;

public class ObserwatorEmail implements Obserwator {
	String adresEmailKlienta;

	public ObserwatorEmail(String adresEmailKlienta) {
		this.adresEmailKlienta = adresEmailKlienta;
	}

	@Override
	public void powiadom(String nazwa) {
		//wyslanie maila do klienta
		System.out.println(this.adresEmailKlienta + "otrzymal email:" + nazwa);
	}
}
