<<<<<<< HEAD
import java.util.List;

public abstract class Stunde implements Termin {

	@Override
	public List<Person> getBeteiligtePersonen() {
		// TODO r�ckgabe von Sch�lern und Lehrern in einer Liste
		return null;
	}
	
	//TODO Attribute f�r beteilligte Sch�ler und Lehrer

=======

import java.util.List;

public abstract class Stunde implements Termin {
	
	@Override
	public abstract List<Person> getBeteiligtePersonen();
>>>>>>> featureBackEnd01
}
