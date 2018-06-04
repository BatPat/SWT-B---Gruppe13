package fachlogik;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;

public class PersonenErstellen {

	public static void main(String[] args) {
		FahrlehrerDaoImpl fahrlehrer = FahrlehrerDaoImpl.getInstance();
		FahrschuelerDaoImpl fahrschueler = FahrschuelerDaoImpl.getInstance();
		fahrlehrer.addFahrlehrer(new Fahrlehrer("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3"));
		fahrlehrer.addFahrlehrer(new Fahrlehrer("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10"));
		fahrlehrer.addFahrlehrer(new Fahrlehrer("Elke Oltor", "43623", "Unna", "Frogeldamm", "33"));
		fahrschueler.addFahrschueler(new Fahrschueler("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51"));
		fahrschueler.addFahrschueler(new Fahrschueler("Julius Blanke", "51123", "Hagen", "Runhweg", "32"));
		fahrschueler.addFahrschueler(new Fahrschueler("Maria Chimea", "41243", "Dortmund", "Temmstraße", "41"));
	}

}
