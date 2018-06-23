package datenhaltung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachlogik.FahrlehrerDTO;
import fachlogik.HibernateUtil;
import fachlogik.TheorieThema;
import fachlogik.TheoriestundeDTO;

public class TheoriestundeDAOTest {
	private static final TheorieStundeDaoImpl THEORIESTUNDE_MANAGER = TheorieStundeDaoImpl.getInstance();
	private static final FahrlehrerDaoImpl FAHRLEHRER_MANAGER = FahrlehrerDaoImpl.getInstance();
	private Session session;

	public TheoriestundeDAOTest() {
	}
	
	@BeforeEach
	public void init() {
		session = HibernateUtil.createSessionFactory().openSession();
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
	
	@Test
	public void test_GetAll_Theoriestunden_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
		TheoriestundeDTO theoriestunde = new TheoriestundeDTO(TheorieThema.PARKEN, fahrlehrer1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		TheoriestundeDTO theoriestunde2 = new TheoriestundeDTO(TheorieThema.RECHTLICHES, fahrlehrer1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		List<TheoriestundeDTO> theoriestundenliste = new ArrayList<TheoriestundeDTO>();
		theoriestundenliste.add(theoriestunde);
		theoriestundenliste.add(theoriestunde2);
		THEORIESTUNDE_MANAGER.addTheoriestunde(theoriestunde);
		THEORIESTUNDE_MANAGER.addTheoriestunde(theoriestunde2);
		assertThat(theoriestundenliste.size()).isEqualTo(THEORIESTUNDE_MANAGER.getAlleTheoriestunden().size());
	}
	
	@Test
	public void test_Save_pruefung_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
		TheoriestundeDTO theoriestunde = new TheoriestundeDTO(TheorieThema.PARKEN, fahrlehrer1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		THEORIESTUNDE_MANAGER.addTheoriestunde(theoriestunde);
		assertThat("Stefan Terlau").isEqualTo(theoriestunde.getFahrlehrer().getName());
	}
	
	@Test
	public void test_Update_pruefung_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
		TheoriestundeDTO theoriestunde = new TheoriestundeDTO(TheorieThema.PARKEN, fahrlehrer1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		THEORIESTUNDE_MANAGER.addTheoriestunde(theoriestunde);
		theoriestunde.setOrt("Reckling");
		THEORIESTUNDE_MANAGER.updateTheoriestunde(theoriestunde);
		assertThat("Reckling").isEqualTo(theoriestunde.getOrt());
	}
	
//	@Test
//	public void test_Delete_pruefung_andDontGetOk() {
//		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
//		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
//		TheoriestundeDTO theoriestunde = new TheoriestundeDTO(TheorieThema.PARKEN, fahrlehrer1,
//				LocalDate.now(), LocalTime.now(), "Recklinghausen");
//		THEORIESTUNDE_MANAGER.addTheoriestunde(theoriestunde);
//		THEORIESTUNDE_MANAGER.deleteTheoriestunde(theoriestunde);
//		assertNull(THEORIESTUNDE_MANAGER.getTheoriestunde((int) theoriestunde.getId()));
//	}
}
