package Fachlogik;

import java.util.List;

import java.util.*;

public class Fahrschueler implements Person {

	private String name;
	private String plz;
	private String wohnort;
	private String strasse;
	private String hausnummer;
	
	private ArrayList<Pruefung> pruefungen;
	private ArrayList<Fahrstunde> fahrstunden;
	private ArrayList<Theoriestunde> theoriestunden;


	public Fahrschueler(String name, String plz, String wohnort, String strasse, String hausnummer) {
		super();
		this.name = name;
		this.plz = plz;
		this.wohnort = wohnort;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
	}
	
	@Override
	public List<Termin> getTermine() {
		ArrayList<Termin> termine = new ArrayList<Termin>();
		termine.addAll(fahrstunden);
		termine.addAll(pruefungen);
		termine.addAll(theoriestunden);
		return termine;
	}
	
	

	/**
	 * @return the pruefungen
	 */
	public List<Pruefung> getPruefungen() {
		return pruefungen;
	}

	/**
	 * @return the fahrstunden
	 */
	public List<Fahrstunde> getFahrstunden() {
		return fahrstunden;
	}

	/**
	 * @return the theoriestunden
	 */
	public List<Theoriestunde> getTheoriestunden() {
		return theoriestunden;
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
