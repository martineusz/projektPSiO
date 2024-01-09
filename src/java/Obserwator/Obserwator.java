package Obserwator;

import java.util.ArrayList;

public interface Obserwator {
	public abstract void powiadom(String nazwa);
	ArrayList<String> getPowiadomienia();
}
