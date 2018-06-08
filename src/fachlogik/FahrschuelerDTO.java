package fachlogik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "fahrschueler")
@SuppressWarnings("serial")
public class FahrschuelerDTO implements Person, Serializable {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
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

	@OneToMany
	private List<PruefungDTO> pruefungen;
	@OneToMany
	private List<FahrstundeDTO> fahrstunden;
	@OneToMany
	private List<TheoriestundeDTO> theoriestunden;

	public FahrschuelerDTO() {
		super();
	}

	public FahrschuelerDTO(String name, String plz, String wohnort, String strasse, String hausnummer) {
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
	public List<PruefungDTO> getPruefungen() {
		return pruefungen;
	}

	/**
	 * @return the fahrstunden
	 */
	public List<FahrstundeDTO> getFahrstunden() {
		return fahrstunden;
	}

	/**
	 * @return the theoriestunden
	 */
	public List<TheoriestundeDTO> getTheoriestunden() {
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

	public void setPruefungen(ArrayList<PruefungDTO> pruefungen) {
		this.pruefungen = pruefungen;
	}

	public void setFahrstunden(ArrayList<FahrstundeDTO> fahrstunden) {
		this.fahrstunden = fahrstunden;
	}

	public void setTheoriestunden(ArrayList<TheoriestundeDTO> theoriestunden) {
		this.theoriestunden = theoriestunden;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}