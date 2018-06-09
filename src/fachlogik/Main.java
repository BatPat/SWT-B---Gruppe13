package fachlogik;

import java.time.LocalDate;
import java.time.LocalTime;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;

public class Main {

	public static void main(String[] args) {
//    	 new Controller();
		
//		Funktion save Fahrlehrer ---- Check
		FahrlehrerDaoImpl fahrlehrer = FahrlehrerDaoImpl.getInstance();
		FahrschuelerDaoImpl fahrschueler = FahrschuelerDaoImpl.getInstance();
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3");
		FahrlehrerDTO fahrlehrer2 = new FahrlehrerDTO("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10");
		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51");
//		fahrlehrer.addFahrlehrer(fahrlehrer1);
//		fahrlehrer.addFahrlehrer(fahrlehrer2);
//		fahrschueler.addFahrschueler(fahrschueler1);		
		
//		Funktion update  ---- Check
//     	f.setHausnummer("4");
//    	fahrlehrer.updateFahrlehrer(f);
		
//		Funnktion mapping one to many zwischen Fahrlehrer und Stunden		
		TheoriestundeDTO fs1 = new TheoriestundeDTO(TheorieThema.ANDERE_TEILNEHMER, fahrlehrer1, LocalDate.now(), LocalTime.now(),"Recklinghausen");
		TheoriestundeDTO fs2 = new TheoriestundeDTO(TheorieThema.MANEUVER, fahrlehrer1, LocalDate.now(), LocalTime.now(),"Recklinghausen");
		TheoriestundeDTO fs3 = new TheoriestundeDTO(TheorieThema.VORFAHRT, fahrlehrer1, LocalDate.now(), LocalTime.now(),"Recklinghausen");
		fahrschueler1.getTheoriestunden().add(fs1);
		fahrschueler1.getTheoriestunden().add(fs2);
		fahrschueler1.getTheoriestunden().add(fs3);
		
		fahrlehrer.addFahrlehrer(fahrlehrer1);
		fahrlehrer.addFahrlehrer(fahrlehrer2);
		fahrschueler.addFahrschueler(fahrschueler1);	
//		
//		TheoriestundeDTO fs1 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit, datum, ort);
//		TheoriestundeDTO fs2 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit, datum, ort);
//		TheoriestundeDTO fs3 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit, datum, ort);
//		f.getTheoriestunden().add(fs1);
//		f.getTheoriestunden().add(fs2);
//		f.getTheoriestunden().add(fs3);
//		
//		h.saveFahrlehrer(f);
		
		

		
		
//		Funktion delete
//		h.deleteFahrlehrer(f);
		
		
//		Funktion get alle Fahrlehrer
//		h.getAllFahrlehrer();
		
		
//		Funktion get Fahrlehrer by Id
//		FahrlehrerDTO f2 = h.getFahrlehrerById("Stefan Terlau");
	}
}
