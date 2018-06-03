package fachlogik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "fahrschueler")
@SuppressWarnings("serial")
public class Fahrschueler implements Person, Serializable {
	@Id
	@Column(nullable = false, name = "idfahrschueler")
	private long id;
	@Column(nullable = false, name = "namefahrschueler")
	private String name;
	@Column(nullable = false, name = "plzfahrschueler")
	private String plz;
	@Column(nullable = false, name = "wohnortfahrschueler")
	private String wohnort;
	@Column(nullable = false, name = "strassefahrschueler")
	private String strasse;
	@Column(nullable = false, name = "hausnummerfahrschueler")
	private String hausnummer;

//	@OneToMany(cascade = CascadeType.ALL)
	@OneToMany(cascade=CascadeType.ALL, targetEntity=Pruefung.class)
	@JoinColumn(name="id")
	private List<Pruefung> pruefungen;
//	@OneToMany(cascade = CascadeType.ALL)
	@OneToMany(cascade=CascadeType.ALL, targetEntity=Fahrstunde.class)
	@JoinColumn(name="id")
	private List<Fahrstunde> fahrstunden;
//	@OneToMany(cascade = CascadeType.ALL)
	@ManyToOne(cascade=CascadeType.ALL, targetEntity=Theoriestunde.class)
	@JoinColumn(name="id")
	private List<Theoriestunde> theoriestunden;

	public Fahrschueler() {
		super();
	}

	public Fahrschueler(String name, String plz, String wohnort, String strasse, String hausnummer) {
		super();
		this.name = name;
		this.plz = plz;
		this.wohnort = wohnort;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.fahrstunden = new ArrayList<>();
		this.theoriestunden = new ArrayList<>();
		this.pruefungen = new ArrayList<>();
	}

	@Override
	public List<Termin> getTermine() {
		ArrayList<Termin> termine = new ArrayList<>();
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public void setPruefungen(ArrayList<Pruefung> pruefungen) {
		this.pruefungen = pruefungen;
	}

	public void setFahrstunden(ArrayList<Fahrstunde> fahrstunden) {
		this.fahrstunden = fahrstunden;
	}

	public void setTheoriestunden(ArrayList<Theoriestunde> theoriestunden) {
		this.theoriestunden = theoriestunden;
	}
}