
public enum Fahrstundenart {

	NACHTFAHRT (40.0),
	AUTOBAHNFAHRT (40.0),
	STANDARDFAHRT (30.0),
	UEBERLANDFAHRT (35.0);
	
	private final double preis;
	
	Fahrstundenart(double preis){
		this.preis = preis;
	}
	
	public double getPreis(){
		return this.preis;
	}
	
	@Override
	public String toString(){
		return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase(); 
	}
	
}
