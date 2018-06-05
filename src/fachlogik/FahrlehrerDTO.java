package fachlogik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "fahrlehrer")
@SuppressWarnings("serial")
public class FahrlehrerDTO implements Person, Serializable {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(nullable = false, name = "namefahrlehrer")
	private String name;
	@Column(nullable = false, name = "plzfahrlehrer")
	private String plz;
	@Column(nullable = false, name = "wohnortfahrlehrer")
	private String wohnort;
	@Column(nullable = false, name = "strassefahrlehrer")
	private String strasse;
	@Column(nullable = false, name = "hausnummerfahrlehrer")
	private String hausnummer;

//	@OneToMany(cascade=CascadeType.ALL, targetEntity=FahrstundeDTO.class)
//	@JoinColumn(name="id")
	@Transient
	private List<FahrstundeDTO> fahrstunden;
//	@OneToMany(cascade=CascadeType.ALL, targetEntity=TheoriestundeDTO.class)
//	@JoinColumn(name="id")
	@Transient
	private List<TheoriestundeDTO> theoriestunden;

	public FahrlehrerDTO() {
		super();
	}

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
	public List<Termin> getTermine() {
		ArrayList<Termin> termine = new ArrayList<>();
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

	public void setFahrstunden(ArrayList<FahrstundeDTO> fahrstunden) {
		this.fahrstunden = fahrstunden;
	}

	public void setTheoriestunden(ArrayList<TheoriestundeDTO> theoriestunden) {
		this.theoriestunden = theoriestunden;
	}
}
