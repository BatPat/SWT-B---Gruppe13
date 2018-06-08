package fachlogik;

public class Main {

	public static void main(String[] args) {
//    	 new Controller();
		
//		Funktion save Fahrlehrer
	    FahrlehrerDTO f = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3");
		HibernateUtil h = new HibernateUtil();
		h.saveFahrlehrer(f);
		
		
//		Funnktion mapping one to many zwischen Fahrlehrer und Stunden		
//		TheoriestundeDTO fs1 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit, datum, ort);
//		TheoriestundeDTO fs2 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit, datum, ort);
//		TheoriestundeDTO fs3 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit, datum, ort);
//		f.getTheoriestunden().add(fs1);
//		f.getTheoriestunden().add(fs2);
//		f.getTheoriestunden().add(fs3);
//		
//		TheoriestundeDTO fs1 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit, datum, ort);
//		TheoriestundeDTO fs2 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit, datum, ort);
//		TheoriestundeDTO fs3 = new TheoriestundeDTO(art, lehrer, schueler, uhrzeit, datum, ort);
//		f.getTheoriestunden().add(fs1);
//		f.getTheoriestunden().add(fs2);
//		f.getTheoriestunden().add(fs3);
//		
//		h.saveFahrlehrer(f);
		
		
//		Funktion update
//     	f.setName("hans");
//    	h.updateFahrlehrer(f);
		
		
//		Funktion delete
//		h.deleteFahrlehrer(f);
		
		
//		Funktion get alle Fahrlehrer
//		h.getAllFahrlehrer();
		
		
//		Funktion get Fahrlehrer by Id
//		FahrlehrerDTO f2 = h.getFahrlehrerById("Stefan Terlau");
	}
}
