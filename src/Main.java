import java.io.FileInputStream;
import java.util.Properties;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;
import fachlogik.FahrlehrerDTO;
import fachlogik.FahrschuelerDTO;
import oberflaeche.Controller;

public class Main {

	private static Properties fahrschulProperties;
	private static Properties languageProperties;

	public static void main(String[] args) {
		fahrschulProperties = new Properties();
		try {
			fahrschulProperties.load(new FileInputStream("resources/fahrschule.properties"));
			String lang = fahrschulProperties.getProperty("lang");
			String tax = fahrschulProperties.getProperty("tax");
			languageProperties = new Properties();
			languageProperties.load(new FileInputStream("resources/" + lang + ".properties"));
			// TODO verwenden der properties

//			new InitTestData();
			FahrlehrerDaoImpl fahrlehrer = new FahrlehrerDaoImpl(); 
		    FahrschuelerDaoImpl fahrschueler = new FahrschuelerDaoImpl(); 
		    fahrlehrer.addFahrlehrer(new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3", tax, tax, tax)); 
		    fahrlehrer.addFahrlehrer(new FahrlehrerDTO("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10", tax, tax, tax)); 
		    fahrlehrer.addFahrlehrer(new FahrlehrerDTO("Elke Oltor", "43623", "Unna", "Frogeldamm", "33", tax, tax, tax)); 
		    fahrschueler.addFahrschueler(new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51", tax, tax, tax)); 
		    fahrschueler.addFahrschueler(new FahrschuelerDTO("Julius Blanke", "51123", "Hagen", "Runhweg", "32", tax, tax, tax)); 
		    fahrschueler.addFahrschueler(new FahrschuelerDTO("Maria Chimea", "41243", "Dortmund", "Temmstraße", "41", tax, tax, tax)); 
			new Controller(languageProperties);
		} catch (Exception e) {
		}
	}
}
