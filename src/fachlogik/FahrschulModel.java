package fachlogik;

import java.time.LocalDate;
import java.time.LocalTime;

public class FahrschulModel {

	private int fahrlehrerId;
	private int fahrschuelerId;
	private LocalDate datum;
	private LocalTime uhrzeit;
	private Fahrstundenart art;

	public FahrschulModel() {
		this.datum = LocalDate.now();
	}

	public int getFahrlehrerId() {
		return fahrlehrerId;
	}

	public void setFahrlehrerId(int fahrlehrerId) {
		this.fahrlehrerId = fahrlehrerId;
	}

	public int getFahrschuelerId() {
		return fahrschuelerId;
	}

	public void setFahrschuelerId(int fahschuelerId) {
		this.fahrschuelerId = fahschuelerId;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public LocalTime getUhrzeit() {
		return uhrzeit;
	}

	public void setUhrzeit(LocalTime uhrzeit) {
		this.uhrzeit = uhrzeit;
	}

	public Fahrstundenart getArt() {
		return art;
	}

	public void setArt(Fahrstundenart art) {
		this.art = art;
	}

	
}
