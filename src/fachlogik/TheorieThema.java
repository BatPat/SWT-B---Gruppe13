package fachlogik;

public enum TheorieThema {

	VORFAHRT("Vorfahrtregeln"),
	ALKOHOL_UND_MENSCHLICHE_RISIKEN("Persönliche Voraussetzungen / Risikofaktor Mensch"),
	RECHTLICHES("Rechtliche Rahmenbedingungen"),
	VERKEHRSZEICHEN("Verkehrszeichen und Verkehrseinrichtungen"),
	STRASSENSYSTEM("Straßenverkehrssystem und seine Nutzung"),
	VERKEHRSREGELUNG("Verkehrsregelungen / Bahnübergänge"),
	GESCHWINDIGKEIT("Geschwindigkeit, Abstand und umweltschonende Fahrweise"),
	ANDERE_TEILNEHMER("Andere Teilnehmer im Straßenverkehr: Besonderheiten und Verhalten"),
	MANEUVER("Verkehrsverhalten bei Fahrmanövern, Verkehrsbeobachtung"),
	PARKEN("Ruhender Verkehr");
	;
	
	private final String beschreibung;
	
	TheorieThema(String beschreibung){
		this.beschreibung = beschreibung;
	}
	
	public String getBeschreibung(){
		return this.beschreibung;
	}
	
	@Override
	public String toString(){
		return this.beschreibung;
	}

}
