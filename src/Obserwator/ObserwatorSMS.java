package Obserwator;

import java.io.Serializable;

public class ObserwatorSMS implements Obserwator, Serializable {
	String numerTelefonuKlienta;
	private static final long serialVersionUID = 4105273726510795698L;

	public ObserwatorSMS(String numerTelefonuKlienta) {
		this.numerTelefonuKlienta = numerTelefonuKlienta;
	}

	@Override
	public void powiadom(String nazwa) {
		//wyslanie smsa do klienta
		System.out.println(this.numerTelefonuKlienta + " otrzymal SMS: " + nazwa);
	}

}
