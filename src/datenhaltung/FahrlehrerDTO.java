package datenhaltung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fachlogik.Fahrstunde;
import fachlogik.Theoriestunde;

@SuppressWarnings("serial")
public class FahrlehrerDTO implements Serializable{

	private long id;
	private String name;
	private String plz;
	private String wohnort;
	private String strasse;
	private String hausnummer;
	private List<Fahrstunde> fahrstunden;
	private List<Theoriestunde> theoriestunden;

	public FahrlehrerDTO(String name, String plz, String wohnort, String strasse, String hausnummer) {
		super();
		this.name = name;
		this.plz = plz;
		this.wohnort = wohnort;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.fahrstunden = new ArrayList<>();
		this.theoriestunden = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getWohnort() {
		return wohnort;
	}

	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public List<Fahrstunde> getFahrstunden() {
		return fahrstunden;
	}

	public void setFahrstunden(List<Fahrstunde> fahrstunden) {
		this.fahrstunden = fahrstunden;
	}

	public List<Theoriestunde> getTheoriestunden() {
		return theoriestunden;
	}

	public void setTheoriestunden(List<Theoriestunde> theoriestunden) {
		this.theoriestunden = theoriestunden;
	}
}