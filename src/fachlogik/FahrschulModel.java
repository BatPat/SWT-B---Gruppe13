package fachlogik;

import java.time.LocalDate;
import java.time.LocalTime;

public class FahrschulModel {

	private Kalender kalender;
	private FahrstundeDTO fahrstunde;

	public FahrschulModel() {
		kalender = new Kalender();
		fahrstunde = new FahrstundeDTO(null, new FahrlehrerDTO(), new FahrschuelerDTO(), null, null, "Fahrschule Terlau");
	}

	public boolean isAlleFelderAusgefuellt() {
		boolean result = true;
		
		result = getFahrlehrer().getName() != null;
		result = result && getFahrschueler().getName() != null;
		result = result && getDatum() != null;
		result = result && getUhrzeit() != null;
		result = result && getArt() != null;

		return result;
	}

	public FahrlehrerDTO getFahrlehrer() {
		return fahrstunde.getLehrer();
	}

	public void setFahrlehrer(FahrlehrerDTO fahrlehrer) {
		this.fahrstunde.setLehrer(fahrlehrer);
	}

	public FahrschuelerDTO getFahrschueler() {
		return fahrstunde.getSchueler();
	}

	public void setFahrschueler(FahrschuelerDTO fahrschueler) {
		this.fahrstunde.setSchueler(fahrschueler);
	}

	public Kalender getKalender() {
		return kalender;
	}

	public void setKalender(Kalender kalender) {
		this.kalender = kalender;
	}

	public FahrstundeDTO getFahrstunde() {
		return fahrstunde;
	}

	public void setFahrstunde(FahrstundeDTO fahrstunde) {
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
