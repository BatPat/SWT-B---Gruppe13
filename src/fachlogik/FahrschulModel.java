package fachlogik;

import java.time.LocalDate;
import java.time.LocalTime;

public class FahrschulModel {

	private Fahrschule fahrschule;
	private Kalender kalender;
	private Fahrstunde fahrstunde;

	public FahrschulModel() {
		fahrschule = new Fahrschule();
		kalender = new Kalender();
		fahrstunde = new Fahrstunde(null, new Fahrlehrer(), new Fahrschueler(), null, null, "Fahrschule Terlau");
	}

	public Fahrlehrer getFahrlehrer() {
		return fahrstunde.getLehrer();
	}

	public void setFahrlehrer(Fahrlehrer fahrlehrer) {
		this.fahrstunde.setLehrer(fahrlehrer);
	}

	public Fahrschueler getFahrschueler() {
		return fahrstunde.getSchueler();
	}

	public void setFahrschueler(Fahrschueler fahrschueler) {
		this.fahrstunde.setSchueler(fahrschueler);
	}

	public Fahrschule getFahrschule() {
		return fahrschule;
	}

	public void setFahrschule(Fahrschule fahrschule) {
		this.fahrschule = fahrschule;
	}

	public Kalender getKalender() {
		return kalender;
	}

	public void setKalender(Kalender kalender) {
		this.kalender = kalender;
	}

	public Fahrstunde getFahrstunde() {
		return fahrstunde;
	}

	public void setFahrstunde(Fahrstunde fahrstunde) {
		this.fahrstunde = fahrstunde;
	}

	public LocalTime getUhrzeit() {
		return fahrstunde.getUhrzeit();
	}

	public void setUhrzeit(LocalTime uhrzeit) {
		this.fahrstunde.setUhrzeit(uhrzeit);
	}

	public LocalDate getDatum() {
		return fahrstunde.getDatum();
	}

	public void setDatum(LocalDate datum) {
		this.fahrstunde.setDatum(datum);
	}

	public Fahrstundenart getArt() {
		return fahrstunde.getArt();
	}

	public void setArt(Fahrstundenart art) {
		this.fahrstunde.setArt(art);
	}
}
