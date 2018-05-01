import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

/*
 * Testcases:
 * 
 * alle Termine -> Liste mit Terminen
 * neuen Termin -> Termin wird aufgenommen
 * Termin entfernen -> Termin wird nicht mehr verwaltet
 * 
 * 
 */

public class KalenderTest {
	
	private Kalender k;
	private Termin t;
	
	@Before
	public void setUp() {
		k = new Kalender();
		t = mock(Termin.class);
	}
	
	@Test
	public void alleTermine() {
		//Given
		List<Termin> liste;
		
		//When
		liste = k.getTerminListe();
		
		//Then
		assertThat(liste).isNotNull();
	}
	
	@Test
	public void neuerTermin(){
		//Given;
		
		//When
		k.addTermin(t);
		
		//Then
		assertThat(k.getTerminListe().contains(t)).isTrue();
	}
	
	@Test
	public void entferneTermin() {
		//Given
		k.addTermin(t);
		
		//When
		k.removeTermin(t);
		
		//Then
		assertThat(k.getTerminListe().contains(t)).isFalse();
	}
}
