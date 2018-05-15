package fachlogik;

public enum Fahrstundenart {


	B_STANDARDFAHRT (34.0, "Standardfahrt"),
	B_SONDERFAHRT (44.0, "Sonderfahrt");
	
	private final double preis;
	private final String beschreibung;
	
	Fahrstundenart(double preis, String beschreibung){
		this.preis = preis;
		this.beschreibung = beschreibung;
	}
	
	public double getPreis(){
		return this.preis;
	}
	
	public String getBeschreibung(){
		return this.beschreibung;
	}
	
	@Override
	public String toString(){
		return this.beschreibung + ": " + this.preis + "�"; 
	}

}
