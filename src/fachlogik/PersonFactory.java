package fachlogik;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;

public class PersonFactory implements AbstractPersonFactory {

	@Override
	public Person createPerson(PersonType typ, String name, String plz, String wohnort, String strasse, String hausnummer, String telefonnummer, String geburtsdatum, String fuehrerscheinklasse) {

		Person person = null;
		
		switch (typ) {
		case FAHRLEHRER:
			FahrlehrerDTO fLehrer = new FahrlehrerDTO(name, plz, wohnort, strasse, hausnummer, telefonnummer, geburtsdatum, fuehrerscheinklasse);
			FahrlehrerDaoImpl.getInstance().addFahrlehrer(fLehrer);
			person = fLehrer;		
			break;
	
		case FAHRSCHUELER:
			FahrschuelerDTO fSchueler = new FahrschuelerDTO(name, plz, wohnort, strasse, hausnummer, telefonnummer, geburtsdatum, fuehrerscheinklasse);
			FahrschuelerDaoImpl.getInstance().addFahrschueler(fSchueler);
			person = fSchueler;
			break;
		default:
			
			break;
		}
		return person;
	}

}
