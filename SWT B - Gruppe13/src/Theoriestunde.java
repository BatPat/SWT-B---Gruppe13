import java.util.ArrayList;
import java.util.List;

public class Theoriestunde extends Stunde {
	
	private TheorieThema thema;
	private Fahrlehrer lehrer;
	private ArrayList<Fahrschueler> schueler;

	public Theoriestunde(TheorieThema thema, Fahrlehrer lehrer, ArrayList<Fahrschueler> schueler) {
		super();
		this.thema = thema;
		this.lehrer = lehrer;
		this.schueler = schueler;
	}

	@Override
	public List<Person> getBeteiligtePersonen() {
		List<Person> personen = new ArrayList<Person>();
		personen.add(lehrer);
		personen.addAll(schueler);
		return personen;
	}

	/**
	 * @return the thema
	 */
	public TheorieThema getThema() {
		return thema;
	}

	/**
	 * @return the lehrer
	 */
	public Fahrlehrer getLehrer() {
		return lehrer;
	}

	/**
	 * @return the schueler
	 */
	public ArrayList<Fahrschueler> getSchueler() {
		return schueler;
	}
	
}
