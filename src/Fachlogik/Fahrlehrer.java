package Fachlogik;

import java.util.ArrayList;
import java.util.List;

public class Fahrlehrer implements Person{


	private String name;
	private String plz;
	private String wohnort;
	private String strasse;
	private String hausnummer;

	private ArrayList<Fahrstunde> fahrstunden;
	private ArrayList<Theoriestunde> theoriestunden;
	
	public Fahrlehrer() {
		super();
	}

	public Fahrlehrer(String name, String plz, String wohnort, String strasse, String hausnummer) {
		super();
		this.name = name;
		this.plz = plz;
		this.wohnort = wohnort;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
	}
	

	/**
	 * @return the fahrstunden
	 */
	public ArrayList<Fahrstunde> getFahrstunden() {
		return fahrstunden;
	}

	/**
	 * @return the theoriestunden
	 */
	public ArrayList<Theoriestunde> getTheoriestunden() {
		return theoriestunden;
	}
	
	@Override
	public List<Termin> getTermine() {
		ArrayList<Termin> termine = new ArrayList<Termin>();
		termine.addAll(fahrstunden);
		termine.addAll(theoriestunden);
		return termine;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPlz() {
		return plz;
	}

	@Override
	public String getWohnort() {
		return wohnort;
	}

	@Override
	public String getStrasse() {
		return strasse;
	}

	@Override
	public String getHausnummer() {
		return hausnummer;
	}

}
