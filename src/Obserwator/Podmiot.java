package Obserwator;

public interface Podmiot {
	public abstract void dodajObserwatora(Obserwator obserwator);
	public abstract void usunObserwatora(Obserwator obserwator);
	public abstract void powiadomObserwatorow();
}
