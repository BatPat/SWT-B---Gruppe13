import java.util.*;

public class Fahrschule {

	private Set<Fahrschueler> alleSchueler;
	private Set<Fahrlehrer> alleLehrer;

	public Fahrschule() {
		super();
		alleSchueler = new HashSet<Fahrschueler>();
		alleLehrer = new HashSet<Fahrlehrer>();
	}
	
	public void addFahrschueler(Fahrschueler s) {
		// TODO Auto-generated method stub
		alleSchueler.add(s);
		
	}


	public List<Fahrschueler> getFahrschuelerListe() {
		// TODO Auto-generated method stub
		List<Fahrschueler> liste = new ArrayList<Fahrschueler>(); 
		liste.addAll(alleSchueler);
		return liste;
	}

	public void removeFahrschueler(Fahrschueler s) {
		// TODO Auto-generated method stub
		alleSchueler.remove(s);
	}

	public void addFahrlehrer(Fahrlehrer l) {
		// TODO Auto-generated method stub
		alleLehrer.add(l);
	}

	public List<Fahrlehrer> getFahrlehrerListe() {
		// TODO Auto-generated method stub
		List<Fahrlehrer> liste = new ArrayList<Fahrlehrer>(); 
		liste.addAll(alleLehrer);
		return liste;
	}

	public void removeFahrlehrer(Fahrlehrer l) {
		// TODO Auto-generated method stub
		alleLehrer.remove(l);
	}

}
