package fachlogik;

public class PersonFactory implements AbstractPersonFactory {

	@Override
	public Person createPerson(PersonType typ, String name, String plz, String wohnort, String strasse, String hausnummer) {

		Person person = null;
		
		switch (typ) {
		case FAHRLEHRER:
			person = new FahrlehrerDTO(name, plz, wohnort, strasse, hausnummer);			
			break;
	
		case FAHRSCHUELER:
			person = new FahrschuelerDTO(name, plz, wohnort, strasse, hausnummer);
			break;
		default:
			
			break;
		}
		return person;
	}

}
