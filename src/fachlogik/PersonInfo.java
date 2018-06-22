package fachlogik;

public class PersonInfo {
	
	private int id;
	private PersonType personType;
	private String name;
	private String plz;	
	private String wohnort;	
	private String strasse;	
	private String hausnummer;	
	private String telefonnummer;	
	private String geburtsdatum;
	private String fuehrerscheinklasse;


	public PersonInfo(PersonType personType, String name, String plz, String wohnort, String strasse, String hausnummer, String telefonnummer,
			String geburtsdatum, String fuehrerscheinklasse) {
		super();
		this.personType = personType;
		this.name = name;
		this.plz = plz;
		this.wohnort = wohnort;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.telefonnummer = telefonnummer;
		this.geburtsdatum = geburtsdatum;
		this.fuehrerscheinklasse = fuehrerscheinklasse;
	}
	
	
	public PersonInfo() {
		
	}


	public PersonType getPersonType() {
		return personType;
	}
	
	
	public void setPersonType(PersonType personType) {
		this.personType = personType;
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
	public String getTelefonnummer() {
		return telefonnummer;
	}
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
	public String getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public String getFuehrerscheinklasse() {
		return fuehrerscheinklasse;
	}
	public void setFuehrerscheinklasse(String fuehrerscheinklasse) {
		this.fuehrerscheinklasse = fuehrerscheinklasse;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
