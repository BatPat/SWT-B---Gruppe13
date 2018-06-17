package fachlogik;

public class PersonFactory implements AbstractPersonFactory {

	@Override
	public Person createPerson(PersonType typ, String name, String plz, String wohnort, String strasse, String hausnummer, String telefonnummer, String geburtsdatum, String fuehrerscheinklasse) {

		Person person = null;
		
		switch (typ) {
		case FAHRLEHRER:
			person = new FahrlehrerDTO(name, plz, wohnort, strasse, hausnummer, telefonnummer, geburtsdatum, fuehrerscheinklasse);			
			break;
	
		case FAHRSCHUELER:
			person = new FahrschuelerDTO(name, plz, wohnort, strasse, hausnummer, telefonnummer, geburtsdatum, fuehrerscheinklasse);
			break;
		default:
			
			break;
		}
		return person;
	}

}
