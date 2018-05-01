import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;


/*
 * Testcases:
 * 
 * neuer Fahrschüler -> Fahrschüler in der Verwaltung aufgenommen
 * Fahrschüler entfernen -> Fahrschüler wird nicht mehr verwaltet
 * 
 * neuer Fahrlehrer -> Fahrlehrer in der Verwaltung aufgenommen
 * Fahrlehrer entfernen -> Fahrlehrer wird nicht mehr verwaltet
 * 
 */

public class FahrschuleTest {
	
	private Fahrschule fs;
	
	 @Before
    public void setUp() {
		 fs = new Fahrschule();
    }
	
	
	@Test
	public void neuerFahrschueler(){
		//Given
		Fahrschueler s = new Fahrschueler();
		
		//When
		fs.addFahrschueler(s);
		
		//Then
		assertThat(fs.getFahrschuelerListe().contains(s)).isTrue();
	}
	
	@Test
	public void entferneFahrschueler(){
		//Given
		Fahrschueler s = new Fahrschueler();
		fs.addFahrschueler(s);

		
		//When
		fs.removeFahrschueler(s);

		//Then
		assertThat(fs.getFahrschuelerListe().contains(s)).isFalse();

	}
	
	@Test
	public void neuerFahrlehrer(){
		//Given
		Fahrlehrer l = new Fahrlehrer();
		
		//When
		fs.addFahrlehrer(l);
		
		//Then
		assertThat(fs.getFahrlehrerListe().contains(l)).isTrue();
	}
	
	@Test
	public void entferneFahrlehrer(){
		//Given
		Fahrlehrer l = new Fahrlehrer();
		fs.addFahrlehrer(l);

		
		//When
		fs.removeFahrlehrer(l);

		//Then
		assertThat(fs.getFahrlehrerListe().contains(l)).isFalse();

	}
	
}
