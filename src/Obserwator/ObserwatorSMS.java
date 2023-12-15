package Obserwator;

public class ObserwatorSMS implements Obserwator {
	String numerTelefonuKlienta;

	public ObserwatorSMS(String numerTelefonuKlienta) {
		this.numerTelefonuKlienta = numerTelefonuKlienta;
	}

	@Override
	public void powiadom(String nazwa) {
		//wyslanie smsa do klienta
		System.out.println(this.numerTelefonuKlienta + "otrzymal SMS o promocji:" + nazwa);
	}
}
