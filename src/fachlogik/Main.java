package fachlogik;

import java.time.LocalDate;
import java.time.LocalTime;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;
import datenhaltung.FahrstundeDaoImpl;
import datenhaltung.PruefungDaoImpl;
import datenhaltung.TheorieStundeDaoImpl;

public class Main {

	public static void main(String[] args) {
		// new Controller();

		// Funktion save Fahrlehrer ---- Check
		FahrlehrerDaoImpl fahrlehrer = FahrlehrerDaoImpl.getInstance();
		FahrschuelerDaoImpl fahrschueler = FahrschuelerDaoImpl.getInstance();
		FahrstundeDaoImpl fahrstunde = FahrstundeDaoImpl.getInstance();
		TheorieStundeDaoImpl theoriestunde = TheorieStundeDaoImpl.getInstance();
		PruefungDaoImpl pruefung = PruefungDaoImpl.getInstance();

		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3");
		FahrlehrerDTO fahrlehrer2 = new FahrlehrerDTO("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10");
		FahrlehrerDTO fahrlehrer3 = new FahrlehrerDTO("Elke Oltor", "43623", "Unna", "Frogeldamm", "33");

		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51");
		FahrschuelerDTO fahrschueler2 = new FahrschuelerDTO("Julius Blanke", "51123", "Hagen", "Runhweg", "32");
		FahrschuelerDTO fahrschueler3 = new FahrschuelerDTO("Maria Chimea", "41243", "Dortmund", "Temmstraße", "41");

		// fahrlehrer.addFahrlehrer(fahrlehrer1);
		// fahrlehrer.addFahrlehrer(fahrlehrer2);
		// fahrschueler.addFahrschueler(fahrschueler1);

		// Funktion update ---- Check
		// f.setHausnummer("4");
		// fahrlehrer.updateFahrlehrer(f);

		// Funnktion mapping one to many zwischen Fahrlehrer und Stunden
		TheoriestundeDTO theostd1 = new TheoriestundeDTO(TheorieThema.ANDERE_TEILNEHMER, fahrlehrer1, LocalDate.now(),
				LocalTime.now(), "Recklinghausen");
		TheoriestundeDTO theostd2 = new TheoriestundeDTO(TheorieThema.MANEUVER, fahrlehrer2, LocalDate.now(),
				LocalTime.now(), "Recklinghausen");
		TheoriestundeDTO theostd3 = new TheoriestundeDTO(TheorieThema.VORFAHRT, fahrlehrer1, LocalDate.now(),
				LocalTime.now(), "Recklinghausen");
		
		fahrlehrer.addFahrlehrer(fahrlehrer1);
		fahrlehrer.addFahrlehrer(fahrlehrer2);
		fahrlehrer.addFahrlehrer(fahrlehrer3);
		fahrschueler.addFahrschueler(fahrschueler1);
		fahrschueler.addFahrschueler(fahrschueler2);
		fahrschueler.addFahrschueler(fahrschueler3);

		fahrschueler1.getTheoriestunden().add(theostd1);
		fahrschueler1.getTheoriestunden().add(theostd2);
		fahrschueler1.getTheoriestunden().add(theostd3);
		fahrschueler2.getTheoriestunden().add(theostd1);
		fahrschueler2.getTheoriestunden().add(theostd2);
		fahrschueler2.getTheoriestunden().add(theostd3);
		fahrschueler3.getTheoriestunden().add(theostd1);
		fahrschueler3.getTheoriestunden().add(theostd2);
		fahrschueler3.getTheoriestunden().add(theostd3);
		
		PruefungDTO pruef1 = new PruefungDTO(fahrlehrer1, fahrschueler1, LocalDate.now(), LocalTime.now(), "Recklinghausen");
		PruefungDTO pruef2 = new PruefungDTO(fahrlehrer1, fahrschueler2, LocalDate.now(), LocalTime.now(), "Recklinghausen");
		PruefungDTO pruef3 = new PruefungDTO(fahrlehrer3, fahrschueler3, LocalDate.now(), LocalTime.now(), "Recklinghausen");

		FahrstundeDTO fahrstd1 = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer1, fahrschueler1,
				LocalTime.now(), LocalDate.now(), "Recklinghausen");
		FahrstundeDTO fahrstd2 = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer2, fahrschueler3,
				LocalTime.now(), LocalDate.now(), "Recklinghausen");
		FahrstundeDTO fahrstd3 = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer3, fahrschueler2,
				LocalTime.now(), LocalDate.now(), "Recklinghausen");

		fahrstunde.addFahrstunde(fahrstd1);
		fahrstunde.addFahrstunde(fahrstd2);
		fahrstunde.addFahrstunde(fahrstd3);
		
		pruefung.addPruefung(pruef1);
		pruefung.addPruefung(pruef2);
		pruefung.addPruefung(pruef3);


		// TheoriestundeDTO fs1 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit,
		// datum, ort);
		// TheoriestundeDTO fs2 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit,
		// datum, ort);
		// TheoriestundeDTO fs3 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit,
		// datum, ort);
		// f.getTheoriestunden().add(fs1);
		// f.getTheoriestunden().add(fs2);
		// f.getTheoriestunden().add(fs3);
		//
		// h.saveFahrlehrer(f);

		// Funktion delete
		// h.deleteFahrlehrer(f);

		// Funktion get alle Fahrlehrer
		// h.getAllFahrlehrer();

		// Funktion get Fahrlehrer by Id
		// FahrlehrerDTO f2 = h.getFahrlehrerById("Stefan Terlau");
	}
}
