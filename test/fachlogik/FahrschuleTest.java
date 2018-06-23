package fachlogik;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import static org.assertj.core.api.Assertions.*;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;
import datenhaltung.FahrstundeDaoImpl;
import datenhaltung.TheorieStundeDaoImpl;

class FahrschuleTest {
	
	public static Fahrschule fahrschule;
	public static FahrlehrerDTO fahrlehrer;
	public static FahrschuelerDTO fahrschueler;

	@AfterAll
	public static void resetData() {
		Session session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createNativeQuery("DELETE FROM theoriestunden_fahrschueler");
		Query q2 = session.createNativeQuery("DELETE FROM theoriestunde");
		Query q3 = session.createNativeQuery("DELETE FROM pruefung");
		Query q4 = session.createNativeQuery("DELETE FROM fahrstunde");
		Query q5 = session.createNativeQuery("DELETE FROM fahrschueler");
		Query q6 = session.createNativeQuery("DELETE FROM fahrlehrer");
		q.executeUpdate();
		q2.executeUpdate();
		q3.executeUpdate();
		q4.executeUpdate();
		q5.executeUpdate();
		q6.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	@BeforeAll
	public static void init() {
		resetData();
		
		//Given
		fahrschule = new Fahrschule();
		fahrlehrer = new FahrlehrerDTO("test", "test", "test", "test", "test", "test", "test", "test");
		fahrschueler = new FahrschuelerDTO("test", "test", "test", "test", "test", "test", "test", "test");
		fahrschule.addFahrlehrer(fahrlehrer);
		fahrschule.addFahrschueler(fahrschueler);
	}
	
	@Test
	public void getTermineTest(){
		//Given
		int anzTermine = fahrschule.getTermine(fahrlehrer.getId()).size();
		LocalTime zeit = LocalTime.of(10, 0);
		LocalDate datum = LocalDate.now();
		LocalDateTime termin = LocalDateTime.of(datum, zeit);
		
		//When
		new FahrstundeDTO(Fahrstundenart.B_SONDERFAHRT, fahrlehrer, fahrschueler, zeit, datum, "test");
		fahrschule.updateFahrlehrer(fahrlehrer);
		
		//Then
		assertThat(fahrschule.getTermine(fahrlehrer.getId()).get(anzTermine)).isEqualTo(termin);
		
	}
	
	@Test
	public void getAnzTheoriestundenTest(){
		//Given
		int anzOld = fahrschule.getAnzTheoriestunden(fahrschueler.getId());
		TheoriestundeDTO t = new TheoriestundeDTO(TheorieThema.ANDERE_TEILNEHMER, fahrlehrer, LocalDate.now(), LocalTime.of(10, 0), "test");
		
		//When
		t.getFahrschueler().add(fahrschueler);
		TheorieStundeDaoImpl.getInstance().addTheoriestunde(t);
		
		//Then
		assertThat(fahrschule.getAnzTheoriestunden(fahrschueler.getId())).isEqualTo(anzOld + 1);
		
	}
	
	@Test
	public void getAnzSonderfahrtenTest(){
		//Given
		int anzOld= fahrschule.getAnzSonderfahrten(fahrschueler.getId());
				
		//When
		FahrstundeDTO fahrstunde = new FahrstundeDTO(Fahrstundenart.B_SONDERFAHRT, fahrlehrer, fahrschueler, LocalTime.of(10, 0), LocalDate.now(), "test");
		fahrschule.updateFahrschueler(fahrschueler);
		
		//Then
		assertThat(fahrschule.getAnzSonderfahrten(fahrschueler.getId())).isEqualTo(anzOld + 1);
		
	}
	

	
	@Test
	public void getAnzStandardfahrtenTest(){
		//Given
		int anzOld= fahrschule.getAnzStandardfahrten(fahrschueler.getId());
				
		//When
		FahrstundeDTO fahrstunde = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer, fahrschueler, LocalTime.of(10, 0), LocalDate.now(), "test");
		fahrschule.updateFahrschueler(fahrschueler);
		
		//Then
		assertThat(fahrschule.getAnzStandardfahrten(fahrschueler.getId())).isEqualTo(anzOld + 1);

	}
	
	@Test
	public void getAnzSonderAndStandardFahrtenTest(){
		//Given 
		int[] anzOld = fahrschule.getAnzSonderAndStandardFahrten(fahrschueler.getId());
		
		//When
		FahrstundeDTO fahrstunde = new FahrstundeDTO(Fahrstundenart.B_SONDERFAHRT, fahrlehrer, fahrschueler, LocalTime.of(10, 0), LocalDate.now(), "test");
		fahrstunde = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer, fahrschueler, LocalTime.of(10, 0), LocalDate.now(), "test");
		fahrschule.updateFahrschueler(fahrschueler);
		
		//Then
		assertThat(fahrschule.getAnzSonderAndStandardFahrten(fahrschueler.getId())).containsExactly(anzOld[0] + 1, anzOld[1] + 1);
		
	}
}
