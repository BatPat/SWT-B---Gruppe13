<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> featureBackEnd01
import java.util.List;

public class Fahrlehrer implements Person{

<<<<<<< HEAD
	@Override
	public List<Termin> getTermine() {
		//TODO s�mtliche Termine in einer Liste zur�ckgeben (Fahrstunden, Theoriestunden)
		return null;
	}
	
	//TODO Konstruktor mit Attributen f�r pers�nliche Daten

	
=======
	private String name;
	private String plz;
	private String wohnort;
	private String strasse;
	private String hausnummer;

	private ArrayList<Fahrstunde> fahrstunden;
	private ArrayList<Theoriestunde> theoriestunden;
	
	

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
>>>>>>> featureBackEnd01
}
