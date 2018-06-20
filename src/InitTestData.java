import java.time.LocalDate;
import java.time.LocalTime;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;
import datenhaltung.FahrstundeDaoImpl;
import datenhaltung.PruefungDaoImpl;
import datenhaltung.TheorieStundeDaoImpl;
import fachlogik.FahrlehrerDTO;
import fachlogik.FahrschuelerDTO;
import fachlogik.FahrstundeDTO;
import fachlogik.Fahrstundenart;
import fachlogik.PruefungDTO;
import fachlogik.TheorieThema;
import fachlogik.TheoriestundeDTO;

public class InitTestData {
	private FahrlehrerDaoImpl fahrlehrerManager = FahrlehrerDaoImpl.getInstance();
	private FahrschuelerDaoImpl fahrschuelerManager = FahrschuelerDaoImpl.getInstance();
	private FahrstundeDaoImpl fahrstundemanager = FahrstundeDaoImpl.getInstance();
	private TheorieStundeDaoImpl theoriestundemanager = TheorieStundeDaoImpl.getInstance();
	private PruefungDaoImpl pruefungmanager = PruefungDaoImpl.getInstance();

	public InitTestData() {
		if(fahrlehrerManager.getAlleFahrlehrer().size() == 0)
			createTestData();
	}
	
	public void createTestData(){
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrlehrerDTO fahrlehrer2 = new FahrlehrerDTO("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10","0321-573447","03.06.1999","B");
		FahrlehrerDTO fahrlehrer3 = new FahrlehrerDTO("Elke Oltor", "43623", "Unna", "Frogeldamm", "33","0321-2537347","15.02.1996","B");

		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FahrschuelerDTO fahrschueler2 = new FahrschuelerDTO("Julius Blanke", "51123", "Hagen", "Runhweg", "32","0321-4589347","04.12.1995","B");
		FahrschuelerDTO fahrschueler3 = new FahrschuelerDTO("Maria Chimea", "41243", "Dortmund", "Temmstraße", "41","0221-4589347","15.02.1996","B");

		fahrlehrerManager.addFahrlehrer(fahrlehrer1);
		fahrlehrerManager.addFahrlehrer(fahrlehrer2);
		fahrlehrerManager.addFahrlehrer(fahrlehrer3);
		fahrschuelerManager.addFahrschueler(fahrschueler1);
		fahrschuelerManager.addFahrschueler(fahrschueler2);
		fahrschuelerManager.addFahrschueler(fahrschueler3);

		TheoriestundeDTO theostd1 = new TheoriestundeDTO(TheorieThema.ANDERE_TEILNEHMER, fahrlehrer1, LocalDate.now(),
				LocalTime.of(12, 0), "Recklinghausen");
		fahrschuelerManager.addTheoriestunde(fahrschueler1, theostd1);
		fahrschuelerManager.addTheoriestunde(fahrschueler2, theostd1);
		fahrschuelerManager.addTheoriestunde(fahrschueler3, theostd1);

		TheoriestundeDTO theostd2 = new TheoriestundeDTO(TheorieThema.MANEUVER, fahrlehrer2, LocalDate.now(),
				LocalTime.of(17, 00), "Recklinghausen");
		fahrschuelerManager.addTheoriestunde(fahrschueler1, theostd2);
		fahrschuelerManager.addTheoriestunde(fahrschueler2, theostd2);

		TheoriestundeDTO theostd3 = new TheoriestundeDTO(TheorieThema.VORFAHRT, fahrlehrer1, LocalDate.now(),
				LocalTime.of(15, 0), "Recklinghausen");
		fahrschuelerManager.addTheoriestunde(fahrschueler3, theostd3);

		theoriestundemanager.addTheoriestunde(theostd1);
		theoriestundemanager.addTheoriestunde(theostd2);
		theoriestundemanager.addTheoriestunde(theostd3);

		PruefungDTO pruef1 = new PruefungDTO(fahrlehrer1, fahrschueler1, LocalDate.now(), LocalTime.of(20, 0), "Reckling");
		PruefungDTO pruef2 = new PruefungDTO(fahrlehrer1, fahrschueler2, LocalDate.now(), LocalTime.of(19, 0), "Reckling");
		PruefungDTO pruef3 = new PruefungDTO(fahrlehrer3, fahrschueler3, LocalDate.now(), LocalTime.of(10, 0), "Reckling");

		FahrstundeDTO fahrstd1 = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer1, fahrschueler1,
				LocalTime.of(14, 0), LocalDate.now(), "Recklinghausen");
		FahrstundeDTO fahrstd2 = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer2, fahrschueler3,
				LocalTime.of(18, 0), LocalDate.now(), "Recklinghausen");
		FahrstundeDTO fahrstd3 = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer3, fahrschueler2,
				LocalTime.of(15, 0), LocalDate.now(), "Recklinghausen");

		fahrstundemanager.addFahrstunde(fahrstd1);
		fahrstundemanager.addFahrstunde(fahrstd2);
		fahrstundemanager.addFahrstunde(fahrstd3);

		pruefungmanager.addPruefung(pruef1);
		pruefungmanager.addPruefung(pruef2);
		pruefungmanager.addPruefung(pruef3);
	}

}
