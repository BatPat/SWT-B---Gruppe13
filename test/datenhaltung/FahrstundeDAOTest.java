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
import fachlogik.FahrstundeDTO;
import fachlogik.Fahrstundenart;
import fachlogik.HibernateUtil;

public class FahrstundeDAOTest {
	private static final FahrstundeDaoImpl FAHRSTUNDE_MANAGER = FahrstundeDaoImpl.getInstance();
	private static final FahrlehrerDaoImpl FAHRLEHRER_MANAGER = FahrlehrerDaoImpl.getInstance();
	private static final FahrschuelerDaoImpl FAHRSCHUELER_MANAGER = FahrschuelerDaoImpl.getInstance();
	private Session session;
	
	public FahrstundeDAOTest() {
	}

	@BeforeEach
	public void init() {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createNativeQuery("DROP TABLE fahrschule.theoriestunden_fahrschueler");
		Query q2 = session.createNativeQuery("DROP TABLE fahrschule.theoriestunde");
		Query q3 = session.createNativeQuery("DROP TABLE fahrschule.pruefung");
		Query q4 = session.createNativeQuery("DROP TABLE fahrschule.fahrstunde");
		Query q5 = session.createNativeQuery("DROP TABLE fahrschule.fahrschueler");
		Query q6 = session.createNativeQuery("DROP TABLE fahrschule.fahrlehrer");
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
	public void test_GetAll_Fahrstunde_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler1);
		FahrstundeDTO fahrstunde = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer1, fahrschueler1,
				LocalTime.now(), LocalDate.now(), "Recklinghausen");
		FahrstundeDTO fahrstunde2 = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer1, fahrschueler1,
				LocalTime.now(), LocalDate.now(), "Recklinghausen");
		List<FahrstundeDTO> fahrstundeliste = new ArrayList<FahrstundeDTO>();
		fahrstundeliste.add(fahrstunde);
		fahrstundeliste.add(fahrstunde2);
		FAHRSTUNDE_MANAGER.addFahrstunde(fahrstunde);
		FAHRSTUNDE_MANAGER.addFahrstunde(fahrstunde2);
		assertThat(fahrstundeliste.size()).isEqualTo(FAHRSTUNDE_MANAGER.getAlleFahrstunden().size());
	}
	
	@Test
	public void test_Save_fahrstunde_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler1);
		FahrstundeDTO fahrstunde = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer1, fahrschueler1,
				LocalTime.now(), LocalDate.now(), "Recklinghausen");
		FAHRSTUNDE_MANAGER.addFahrstunde(fahrstunde);
		assertThat("Stefan Terlau").isEqualTo(fahrstunde.getLehrer().getName());
	}
	
	@Test
	public void test_Update_fahrstunde_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler1);
		FahrstundeDTO fahrstunde = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer1, fahrschueler1,
				LocalTime.now(), LocalDate.now(), "Recklinghausen");
		FAHRSTUNDE_MANAGER.addFahrstunde(fahrstunde);
		fahrstunde.setOrt("Reckling");
		FAHRSTUNDE_MANAGER.updateFahrstunde(fahrstunde);
		assertThat("Reckling").isEqualTo(fahrstunde.getOrt());
	}
	
//	@Test
//	public void test_Delete_fahrstunde_andDontGetOk() {
//		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
//		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
//		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer1);
//		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler1);
//		FahrstundeDTO fahrstunde = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer1, fahrschueler1,
//				LocalTime.now(), LocalDate.now(), "Recklinghausen");
//		FAHRSTUNDE_MANAGER.addFahrstunde(fahrstunde);
//		FAHRSTUNDE_MANAGER.deleteFahrstunde(fahrstunde);
//		assertNull(FAHRSTUNDE_MANAGER.getFahrstunde((int) fahrstunde.getId()));
//	}
}
