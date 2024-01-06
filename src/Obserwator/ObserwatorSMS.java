package Obserwator;

import java.io.Serializable;
import java.util.ArrayList;

public class ObserwatorSMS implements Obserwator, Serializable {
	String numerTelefonuKlienta;
	ArrayList<String> powiadomienia;
	private static final long serialVersionUID = 4105273726510795698L;

	public ObserwatorSMS(String numerTelefonuKlienta) {
		this.numerTelefonuKlienta = numerTelefonuKlienta;
		this.powiadomienia = new ArrayList<String>();
	}

	@Override
	public void powiadom(String nazwa) {
		//wyslanie smsa do klienta
		powiadomienia.add(this.numerTelefonuKlienta + " otrzymal SMS: " + nazwa);
		System.out.println(this.numerTelefonuKlienta + " otrzymal SMS: " + nazwa);
	}

	public String getNumerTelefonuKlienta() {
		return numerTelefonuKlienta;
	}

	public void setNumerTelefonuKlienta(String numerTelefonuKlienta) {
		this.numerTelefonuKlienta = numerTelefonuKlienta;
	}

	public ArrayList<String> getPowiadomienia() {
		return powiadomienia;
	}

	public void setPowiadomienia(ArrayList<String> powiadomienia) {
		this.powiadomienia = powiadomienia;
	}
}
