
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import oberflaeche.Controller;

public class Main {

	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("resources/fahrschule.properties"));
			String lang = properties.getProperty("lang");
			String tax = properties.getProperty("tax");
			//TODO verwenden der properties
			
			new InitTestData();
			new Controller();
		} catch (Exception e) {
		}
	}
}
