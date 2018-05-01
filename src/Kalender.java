import java.util.*;

public class Kalender {
	
	private Set<Termin> alleTermine; 
	
	public Kalender() {
		super();
		alleTermine = new HashSet<Termin>();
	}

	public void addTermin(Termin t) {
		// TODO Auto-generated method stub
		alleTermine.add(t);
	}

	public List<Termin> getTerminListe() {
		// TODO Auto-generated method stub
		ArrayList<Termin> liste = new ArrayList<Termin>();
		liste.addAll(alleTermine);
		return liste;
	}

	public void removeTermin(Termin t) {
		// TODO Auto-generated method stub
		alleTermine.remove(t);
	}
	
	

}
