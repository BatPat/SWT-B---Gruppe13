
public enum Fahrstundenart {

<<<<<<< HEAD
	//TODO werte erstellen um für nachtfahrt, autobahn etc preise berechnen zu können.
	
	//TODO toString bereitstellen um die art in der GUI darzustellen
=======
	B_NACHTFAHRT (40.0, "Nachtfahrt"),
	B_AUTOBAHNFAHRT (40.0, "Autobahnfahrt"),
	B_STANDARDFAHRT (30.0, "Standardfahrt"),
	B_UEBERLANDFAHRT (35.0, "Überlandfahrt");
	
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
		return this.beschreibung + ": " + this.preis + "€"; 
	}
	
>>>>>>> featureBackEnd01
}
