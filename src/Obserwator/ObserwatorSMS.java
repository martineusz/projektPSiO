package Obserwator;

import java.io.Serializable;

public class ObserwatorSMS implements Obserwator, Serializable {
	String numerTelefonuKlienta;

	public ObserwatorSMS(String numerTelefonuKlienta) {
		this.numerTelefonuKlienta = numerTelefonuKlienta;
	}

	@Override
	public void powiadom(String nazwa) {
		//wyslanie smsa do klienta
		System.out.println(this.numerTelefonuKlienta + "otrzymal SMS: " + nazwa);
	}
}
