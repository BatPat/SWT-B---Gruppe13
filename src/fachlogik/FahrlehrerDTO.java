package fachlogik;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "fahrlehrer")

public class FahrlehrerDTO implements Person{
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private int id = 0;
	@Id 
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

//	@Fetch(FetchMode.JOIN)
//	@JoinTable(name="fahrlehrer_fahrstunden",joinColumns=@JoinColumn(name="namefahrlehrer"),inverseJoinColumns=@JoinColumn(name="idfahrstunde"))
	@OneToMany(mappedBy="lehrer",cascade=CascadeType.ALL,targetEntity=FahrstundeDTO.class,fetch=FetchType.EAGER)  
	private List<FahrstundeDTO> fahrstunden;
	
//	@Fetch(FetchMode.JOIN)
//	@JoinTable(name="fahrlehrer_theoriestunden",joinColumns=@JoinColumn(name="namefahrlehrer"),inverseJoinColumns=@JoinColumn(name="idtheoriestunde"))
	@OneToMany(mappedBy="fahrlehrer",cascade=CascadeType.ALL,targetEntity=TheoriestundeDTO.class,fetch=FetchType.EAGER)  
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
		this.fahrstunden = new ArrayList<FahrstundeDTO>();
		this.theoriestunden = new ArrayList<TheoriestundeDTO>();
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

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public void setFahrstunden(List<FahrstundeDTO> fahrstunden) {
		this.fahrstunden = fahrstunden;
	}

	public void setTheoriestunden(List<TheoriestundeDTO> theoriestunden) {
		this.theoriestunden = theoriestunden;
	}


}
