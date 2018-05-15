package fachlogik;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;

public class Main {

	public static void main(String[] args) {
		FahrlehrerDaoImpl fahrlehrer = new FahrlehrerDaoImpl();
		FahrschuelerDaoImpl fahrschueler = new FahrschuelerDaoImpl();
		fahrlehrer.addFahrlehrer(new Fahrlehrer("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3"));
		fahrschueler.addFahrschueler(new Fahrschueler("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51"));
		Controller controller = new Controller();
	}
}
