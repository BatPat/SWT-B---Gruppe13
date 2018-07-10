
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
			//TODO verwenden der properties
			
			new InitTestData();
			new Controller(fahrschulProperties, languageProperties);
		} catch (Exception e) {
		}
	}
}
