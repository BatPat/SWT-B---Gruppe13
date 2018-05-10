package Fachlogik;

import java.util.List;

public class Theoriestunde extends Stunde {
	
//TODO Methode und Attribute für beteiligte Personen und Methode für Thema
	
	private TheorieThema thema;

	private long genid;
	public long getGenid() {
		return genid;
	}

	private static long counter = 0;

	
	@Override
	public List<Person> getBeteiligtePersonen() {
		return null;
	}
	
	public Theoriestunde() {
		this.genid = counter++;
	}
	
}
