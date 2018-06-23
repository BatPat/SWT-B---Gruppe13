
import oberflaeche.Controller;

public class Main {

	public static void main(String[] args) {
		try {
			new InitTestData();
			new Controller();
		} catch (Exception e) {
		}
	}
}
