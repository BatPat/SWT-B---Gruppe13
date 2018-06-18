package datenhaltung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import fachlogik.FahrlehrerDTO;
import fachlogik.HibernateUtil;
import fachlogik.TheorieThema;
import fachlogik.TheoriestundeDTO;

public class TheoriestundeDAOTest {
	private static final TheorieStundeDaoImpl THEORIESTUNDE_MANAGER = TheorieStundeDaoImpl.getInstance();
	private Session session;

	public TheoriestundeDAOTest() {
	}
	
	@Before
	public void init() {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createNativeQuery("DROP TABLE fahrschule.theoriestunde");
		q.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void test_GetAll_Theoriestunden_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		TheoriestundeDTO theoriestunde = new TheoriestundeDTO(TheorieThema.PARKEN, fahrlehrer1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		TheoriestundeDTO theoriestunde2 = new TheoriestundeDTO(TheorieThema.RECHTLICHES, fahrlehrer1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		List<TheoriestundeDTO> pruefungliste = new ArrayList<TheoriestundeDTO>();
		pruefungliste.add(theoriestunde);
		pruefungliste.add(theoriestunde2);
		THEORIESTUNDE_MANAGER.addTheoriestunde(theoriestunde);
		THEORIESTUNDE_MANAGER.addTheoriestunde(theoriestunde2);
		assertThat(pruefungliste.size()).isEqualTo(THEORIESTUNDE_MANAGER.getAlleTheoriestunden().size());
	}
	
	@Test
	public void test_Save_pruefung_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		TheoriestundeDTO theoriestunde = new TheoriestundeDTO(TheorieThema.PARKEN, fahrlehrer1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		THEORIESTUNDE_MANAGER.addTheoriestunde(theoriestunde);
		assertThat("Stefan Terlau").isEqualTo(theoriestunde.getFahrlehrer().getName());
	}
	
	@Test
	public void test_Update_pruefung_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		TheoriestundeDTO theoriestunde = new TheoriestundeDTO(TheorieThema.PARKEN, fahrlehrer1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		THEORIESTUNDE_MANAGER.addTheoriestunde(theoriestunde);
		theoriestunde.setOrt("Reckling");
		THEORIESTUNDE_MANAGER.updateTheoriestunde(theoriestunde);
		assertThat("Reckling").isEqualTo(theoriestunde.getOrt());
	}
	
	@Test
	public void test_Delete_pruefung_andDontGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		TheoriestundeDTO theoriestunde = new TheoriestundeDTO(TheorieThema.PARKEN, fahrlehrer1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		THEORIESTUNDE_MANAGER.addTheoriestunde(theoriestunde);
		THEORIESTUNDE_MANAGER.deleteTheoriestunde(theoriestunde);
		assertNull(THEORIESTUNDE_MANAGER.getTheoriestunde((int) theoriestunde.getId()));
	}
}
