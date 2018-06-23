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
import fachlogik.FahrschuelerDTO;
import fachlogik.HibernateUtil;
import fachlogik.PruefungDTO;

public class PruefungDAOTest {
	private static final PruefungDaoImpl PRUEFUNG_MANAGER = PruefungDaoImpl.getInstance();
	private static final FahrlehrerDaoImpl FAHRLEHRER_MANAGER = FahrlehrerDaoImpl.getInstance();
	private static final FahrschuelerDaoImpl FAHRSCHUELER_MANAGER = FahrschuelerDaoImpl.getInstance();
	private Session session;

	public PruefungDAOTest() {
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
	public void test_GetAll_Pruefung_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler1);
		PruefungDTO pruefung = new PruefungDTO(fahrlehrer1, fahrschueler1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		PruefungDTO pruefung2 = new PruefungDTO(fahrlehrer1, fahrschueler1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		List<PruefungDTO> pruefungliste = new ArrayList<PruefungDTO>();
		pruefungliste.add(pruefung);
		pruefungliste.add(pruefung2);
		PRUEFUNG_MANAGER.addPruefung(pruefung);
		PRUEFUNG_MANAGER.addPruefung(pruefung2);
		assertThat(pruefungliste.size()).isEqualTo(PRUEFUNG_MANAGER.getAllePruefungen().size());
	}
	
	@Test
	public void test_Save_pruefung_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler1);
		PruefungDTO pruefung = new PruefungDTO(fahrlehrer1, fahrschueler1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		PRUEFUNG_MANAGER.addPruefung(pruefung);
		assertThat("Stefan Terlau").isEqualTo(pruefung.getFahrlehrer().getName());
	}
	
	@Test
	public void test_Update_pruefung_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler1);
		PruefungDTO pruefung = new PruefungDTO(fahrlehrer1, fahrschueler1,
				LocalDate.now(), LocalTime.now(), "Recklinghausen");
		PRUEFUNG_MANAGER.addPruefung(pruefung);
		pruefung.setOrt("Reckling");
		PRUEFUNG_MANAGER.updatePruefung(pruefung);
		assertThat("Reckling").isEqualTo(pruefung.getOrt());
	}
	
//	@Test
//	public void test_Delete_pruefung_andDontGetOk() {
//		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
//		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
//		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
//		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler1);
//		PruefungDTO pruefung = new PruefungDTO(fahrlehrer1, fahrschueler1,
//				LocalDate.now(), LocalTime.now(), "Recklinghausen");
//		PRUEFUNG_MANAGER.addPruefung(pruefung);
//		PRUEFUNG_MANAGER.deletePruefung(pruefung);
//		assertNull(PRUEFUNG_MANAGER.getPruefung((int) pruefung.getId()));
//	}
}
