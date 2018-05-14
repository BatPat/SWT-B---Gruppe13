package Fachlogik;

public class FahrschulModel {
	
	private Fahrlehrer fahrlehrer;
	private Fahrschueler fahrschueler;
	private Fahrschule fahrschule;
	private Kalender kalender;
	private Pruefung pruefung;
	private Stunde stunde;
	
	
	
	public FahrschulModel() {
		fahrlehrer = new Fahrlehrer();
		fahrschueler = new Fahrschueler();
		fahrschule = new Fahrschule();
		kalender = new Kalender();
		pruefung = new Pruefung();
		//TODO Stunde
	}
	
	public boolean isAlleFelderAusgefuellt()
	  {
	    boolean result = true;
	    //TODO anpassen
	    result = result & !getTarif().getTarifauswahl().isEmpty();
	    result = result & !getTarif().getTarifstufe().isEmpty();
	    result = result & getPerson().getGeschlecht() != null;
	    result = result & getTarif().getAnwartschaft() != null;

	    return result;
	  }
	
	public int berechneVerbliebeneStunden()
	  {
	    //berechne verbliebene Stunden
	  }
	
	public Fahrlehrer getFahrlehrer() {
		return fahrlehrer;
	}


	public void setFahrlehrer(Fahrlehrer fahrlehrer) {
		this.fahrlehrer = fahrlehrer;
	}


	public Fahrschueler getFahrschueler() {
		return fahrschueler;
	}


	public void setFahrschueler(Fahrschueler fahrschueler) {
		this.fahrschueler = fahrschueler;
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


	public Pruefung getPruefung() {
		return pruefung;
	}


	public void setPruefung(Pruefung pruefung) {
		this.pruefung = pruefung;
	}
}
