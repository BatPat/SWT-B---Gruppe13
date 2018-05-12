package Fachlogik;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Theoriestunde extends Stunde {

	
	private TheorieThema thema;
	private long genid;
	private Fahrlehrer fahrlehrer;
	private List<Fahrschueler> fahrschueler;
	
	public long getGenid() {
		return genid;
	}

	private static long counter = 0;

	
	@Override
	public List<Person> getBeteiligtePersonen() {		
		List<Person> personen = new ArrayList<Person>();
		personen.add(fahrlehrer);
		personen.addAll(fahrschueler);
		return personen;
	}
	
	public Theoriestunde(TheorieThema thema, Fahrlehrer fahrlehrer, LocalDate datum, LocalTime uhrzeit, String ort) {
		super(datum, uhrzeit, Duration.ofHours(1), ort);
		this.genid = counter++;
		this.thema = thema;
		this.fahrlehrer = fahrlehrer;
		this.fahrschueler = new ArrayList<Fahrschueler>();
	}

	public TheorieThema getThema() {
		return thema;
	}

	public Fahrlehrer getFahrlehrer() {
		return fahrlehrer;
	}

	public void setFahrlehrer(Fahrlehrer fahrlehrer) {
		this.fahrlehrer = fahrlehrer;
	}

	public List<Fahrschueler> getFahrschueler() {
		return fahrschueler;
	}
	
}
