package Fachlogik;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public abstract class Stunde implements Termin {
	
	private LocalDate datum;
	private LocalTime uhrzeit;
	private Duration dauer;
	private String ort;

	public Stunde(LocalDate datum, LocalTime uhrzeit, Duration dauer, String ort) {
		super();
		this.datum = datum;
		this.uhrzeit = uhrzeit;
		this.dauer = dauer;
		this.ort = ort;
	}

	@Override
	public abstract List<Person> getBeteiligtePersonen();

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

}
