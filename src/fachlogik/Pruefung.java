package fachlogik;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Pruefung implements Termin, Serializable {

	private long genid;
	private Fahrlehrer fahrlehrer;
	private static long counter = 0;
	private boolean bestanden;
	private Fahrschueler fahrschueler;
	private LocalDate datum;
	private LocalTime uhrzeit;
	private Duration dauer;
	private String ort;

	public Pruefung() {
		super();
	}

	public Pruefung(Fahrlehrer fahrlehrer, Fahrschueler fahrschueler, LocalDate datum, LocalTime uhrzeit,
			Duration dauer, String ort) {
		this.genid = counter++;
		this.fahrlehrer = fahrlehrer;
		this.fahrschueler = fahrschueler;
		this.datum = datum;
		this.uhrzeit = uhrzeit;
		this.dauer = dauer;
		this.ort = ort;
	}

	public Pruefung(Fahrschueler fahrschueler, LocalDate datum, LocalTime uhrzeit, Duration dauer, String ort) {
		this(null, fahrschueler, datum, uhrzeit, dauer, ort);
	}

	@Override
	public List<Person> getBeteiligtePersonen() {
		List<Person> personen = new ArrayList<>();
		personen.add(fahrlehrer);
		personen.add(fahrschueler);
		return personen;
	}

	@Override
	public LocalDate getDatum() {
		return datum;
	}

	@Override
	public LocalTime getUhrzeit() {
		return uhrzeit;
	}

	@Override
	public Duration getDauer() {
		return dauer;
	}

	@Override
	public String getOrt() {
		return ort;
	}

	public long getGenid() {
		return genid;
	}

	public boolean isTheoriepruefung() {
		return this.fahrlehrer == null;
	}

	public Fahrlehrer getFahrlehrer() {
		return fahrlehrer;
	}

	public Fahrschueler getFahrschueler() {
		return fahrschueler;
	}

	public boolean isBestanden() {
		return bestanden;
	}

	public void setBestanden(boolean bestanden) {
		this.bestanden = bestanden;
	}

}
