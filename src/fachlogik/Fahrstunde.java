package fachlogik;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Fahrstunde extends Stunde {

	private Fahrstundenart art;
	private Fahrlehrer lehrer;
	private Fahrschueler schueler;
	private long genid;

	public Fahrstunde(LocalDate datum, LocalTime uhrzeit, Duration dauer, String ort) {
		super(datum, uhrzeit, dauer, ort);
	}

	public Fahrstunde(Fahrstundenart art, Fahrlehrer lehrer, Fahrschueler schueler, LocalTime uhrzeit, LocalDate datum,
			String ort) {
		super(datum, uhrzeit, Duration.ofHours(1), ort);
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

	public void setArt(Fahrstundenart art) {
		this.art = art;
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

	public void setLehrer(Fahrlehrer lehrer) {
		this.lehrer = lehrer;
	}

	public void setSchueler(Fahrschueler schueler) {
		this.schueler = schueler;
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
