package fachlogik;

public interface AbstractPersonFactory {
	public Person createPerson(PersonType typ, String name, String plz, String wohnort, String strasse, String hausnummer, String telefonnummer, String geburtsdatum, String fuehrerscheinklasse);
}
