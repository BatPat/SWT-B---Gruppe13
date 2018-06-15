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

		// Test save DTO´s ---- Check
		FahrlehrerDaoImpl fahrlehrerManager = FahrlehrerDaoImpl.getInstance();
		FahrschuelerDaoImpl fahrschuelerManager = FahrschuelerDaoImpl.getInstance();
		FahrstundeDaoImpl fahrstunde = FahrstundeDaoImpl.getInstance();
		TheorieStundeDaoImpl theoriestunde = TheorieStundeDaoImpl.getInstance();
		PruefungDaoImpl pruefung = PruefungDaoImpl.getInstance();

		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3");
		FahrlehrerDTO fahrlehrer2 = new FahrlehrerDTO("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10");
		FahrlehrerDTO fahrlehrer3 = new FahrlehrerDTO("Elke Oltor", "43623", "Unna", "Frogeldamm", "33");

		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51");
		FahrschuelerDTO fahrschueler2 = new FahrschuelerDTO("Julius Blanke", "51123", "Hagen", "Runhweg", "32");
		FahrschuelerDTO fahrschueler3 = new FahrschuelerDTO("Maria Chimea", "41243", "Dortmund", "Temmstraße", "41");


		fahrlehrerManager.addFahrlehrer(fahrlehrer1);
		fahrlehrerManager.addFahrlehrer(fahrlehrer2);
		fahrlehrerManager.addFahrlehrer(fahrlehrer3);
		fahrschuelerManager.addFahrschueler(fahrschueler1);
		fahrschuelerManager.addFahrschueler(fahrschueler2);
		fahrschuelerManager.addFahrschueler(fahrschueler3);

		// Funnktion mapping one to many zwischen DTO´s ---- Check
		TheoriestundeDTO theostd1 = new TheoriestundeDTO(TheorieThema.ANDERE_TEILNEHMER, fahrlehrer1, LocalDate.now(),
				LocalTime.now(), "Recklinghausen");
		fahrschuelerManager.addTheoriestunde(fahrschueler1, theostd1);
//		fahrschuelerManager.addTheoriestunde(fahrschueler2, theostd1);
//		fahrschuelerManager.addTheoriestunde(fahrschueler3, theostd1);

		TheoriestundeDTO theostd2 = new TheoriestundeDTO(TheorieThema.MANEUVER, fahrlehrer2, LocalDate.now(),
				LocalTime.now(), "Recklinghausen");
//		fahrschuelerManager.addTheoriestunde(fahrschueler1, theostd2);
		
		TheoriestundeDTO theostd3 = new TheoriestundeDTO(TheorieThema.VORFAHRT, fahrlehrer1, LocalDate.now(),
				LocalTime.now(), "Recklinghausen");
//		fahrschuelerManager.addTheoriestunde(fahrschueler2, theostd2);
//		fahrschuelerManager.addTheoriestunde(fahrschueler3, theostd3);

		PruefungDTO pruef1 = new PruefungDTO(fahrlehrer1, fahrschueler1, LocalDate.now(), LocalTime.now(), "Reckling");
		PruefungDTO pruef2 = new PruefungDTO(fahrlehrer1, fahrschueler2, LocalDate.now(), LocalTime.now(), "Reckling");
		PruefungDTO pruef3 = new PruefungDTO(fahrlehrer3, fahrschueler3, LocalDate.now(), LocalTime.now(), "Reckling");

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

		// Test delete and Update ---- Check
		// fahrlehrer1.setHausnummer("4");
		// fahrlehrer.updateFahrlehrer(fahrlehrer1);
		//
		// fahrschueler.deleteFahrschueler(fahrschueler1);

		// Test get alle Fahrlehrer ---- Check
		// List<FahrschuelerDTO> l = fahrschueler.getAlleFahrschueler();
		// for (FahrschuelerDTO f : l) {
		// System.out.println(""+f.getName());
		// }

		// Test get Fahrlehrer by Id
		// FahrlehrerDTO fahrlehrerAusgabeTest = fahrlehrer.getFahrlehrer(1);
		// System.out.println(fahrlehrerAusgabeTest.getName());
	}
}
