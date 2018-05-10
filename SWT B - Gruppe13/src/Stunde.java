<<<<<<< HEAD
import java.util.List;

public abstract class Stunde implements Termin {

	@Override
	public List<Person> getBeteiligtePersonen() {
		// TODO rückgabe von Schülern und Lehrern in einer Liste
		return null;
	}
	
	//TODO Attribute für beteilligte Schüler und Lehrer

=======

import java.util.List;

public abstract class Stunde implements Termin {
	
	@Override
	public abstract List<Person> getBeteiligtePersonen();
>>>>>>> featureBackEnd01
}
