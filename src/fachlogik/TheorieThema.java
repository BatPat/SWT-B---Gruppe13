package fachlogik;

public enum TheorieThema {

	VORFAHRT("Vorfahrtregeln"),
	ALKOHOL_UND_MENSCHLICHE_RISIKEN("Pers�nliche Voraussetzungen / Risikofaktor Mensch"),
	RECHTLICHES("Rechtliche Rahmenbedingungen"),
	VERKEHRSZEICHEN("Verkehrszeichen und Verkehrseinrichtungen"),
	STRASSENSYSTEM("Stra�enverkehrssystem und seine Nutzung"),
	VERKEHRSREGELUNG("Verkehrsregelungen / Bahn�berg�nge"),
	GESCHWINDIGKEIT("Geschwindigkeit, Abstand und umweltschonende Fahrweise"),
	ANDERE_TEILNEHMER("Andere Teilnehmer im Stra�enverkehr: Besonderheiten und Verhalten"),
	MANEUVER("Verkehrsverhalten bei Fahrman�vern, Verkehrsbeobachtung"),
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
