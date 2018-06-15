package fachlogik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import datenhaltung.TheorieStundeDaoImpl;

@Entity
@Table(name = "fahrschueler")
@SuppressWarnings("serial")
public class FahrschuelerDTO implements Person, Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@OneToMany(mappedBy = "fahrschueler", cascade = CascadeType.ALL, targetEntity = PruefungDTO.class, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<PruefungDTO> pruefungen;

	@OneToMany(mappedBy = "schueler", cascade = CascadeType.ALL, targetEntity = FahrstundeDTO.class, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<FahrstundeDTO> fahrstunden;

	@ManyToMany(mappedBy = "fahrschueler", cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}, targetEntity = TheoriestundeDTO.class, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
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
		this.fahrstunden = new ArrayList<FahrstundeDTO>();
		this.theoriestunden = new ArrayList<TheoriestundeDTO>();
		this.pruefungen = new ArrayList<PruefungDTO>();
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

	public void addTheoriestunde(TheoriestundeDTO theostd) {
		this.theoriestunden.add(theostd);
		theostd.getFahrschueler().add(this);
	}

}