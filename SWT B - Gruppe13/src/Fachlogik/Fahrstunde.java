package Fachlogik;

import java.util.ArrayList;
import java.util.List;

public class Fahrstunde extends Stunde {

	private Fahrstundenart art;
	private Fahrlehrer lehrer;
	private Fahrschueler schueler;
	private long genid;




	public Fahrstunde(Fahrstundenart art, Fahrlehrer lehrer, Fahrschueler schueler) {
		super();
		this.art = art;
		this.lehrer = lehrer;
		this.schueler = schueler;
		this.genid = genid++;
	}

	/**
	 * @return the art
	 */
	public Fahrstundenart getArt() {
		return art;
	}

	/**
	 * @return the lehrer
	 */
	public Fahrlehrer getLehrer() {
		return lehrer;
	}

	/**
	 * @return the schueler
	 */
	public Fahrschueler getSchueler() {
		return schueler;
	}

	@Override
	public List<Person> getBeteiligtePersonen() {
		List<Person> personen = new ArrayList<Person>();
		personen.add(lehrer);
		personen.add(schueler);
		return personen;
	}
	
	public long getGenid() {
		return genid;
	}

}
