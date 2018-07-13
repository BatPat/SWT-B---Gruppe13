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

//			new InitTestData();
			FahrlehrerDaoImpl fahrlehrer = new FahrlehrerDaoImpl(); 
		    FahrschuelerDaoImpl fahrschueler = new FahrschuelerDaoImpl(); 
		    fahrlehrer.addFahrlehrer(new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3", tax, tax, tax));
		    fahrschueler.addFahrschueler(new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51", tax, tax, tax));
			new Controller(languageProperties);
		} catch (Exception e) {
		}
	}
}
