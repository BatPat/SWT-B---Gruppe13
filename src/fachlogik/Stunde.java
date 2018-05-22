package fachlogik;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@SuppressWarnings("serial")
public abstract class Stunde implements Termin, Serializable {

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

	public void setUhrzeit(LocalTime uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

	public void setDauer(Duration dauer) {
		this.dauer = dauer;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

}
