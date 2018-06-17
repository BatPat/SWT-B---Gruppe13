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
import org.junit.jupiter.api.Test;

import fachlogik.FahrlehrerDTO;
import fachlogik.FahrschuelerDTO;
import fachlogik.FahrstundeDTO;
import fachlogik.Fahrstundenart;
import fachlogik.HibernateUtil;

public class FahrstundeDAOTest {
	private static final FahrstundeDaoImpl FAHRSTUNDE_MANAGER = FahrstundeDaoImpl.getInstance();
	private Session session;
	
	public FahrstundeDAOTest() {
	}

	@Before
	public void init() {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createNativeQuery("DROP TABLE fahrschule.fahrstunde");
		q.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	
	@Test
	public void test_Save_fahrstunde_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FahrstundeDTO fahrstunde = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer1, fahrschueler1,
				LocalTime.now(), LocalDate.now(), "Recklinghausen");
		FAHRSTUNDE_MANAGER.addFahrstunde(fahrstunde);
		assertThat("Stefan Terlau").isEqualTo(fahrstunde.getLehrer().getName());
	}
	
	@Test
	public void test_Update_fahrstunde_andGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FahrstundeDTO fahrstunde = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer1, fahrschueler1,
				LocalTime.now(), LocalDate.now(), "Recklinghausen");
		FAHRSTUNDE_MANAGER.addFahrstunde(fahrstunde);
		fahrstunde.setOrt("Reckling");
		FAHRSTUNDE_MANAGER.updateFahrstunde(fahrstunde);
		assertThat("Reckling").isEqualTo(fahrstunde.getOrt());
	}
	
	@Test
	public void test_Delete_fahrstunde_andDontGetOk() {
		FahrlehrerDTO fahrlehrer1 = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrschuelerDTO fahrschueler1 = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FahrstundeDTO fahrstunde = new FahrstundeDTO(Fahrstundenart.B_STANDARDFAHRT, fahrlehrer1, fahrschueler1,
				LocalTime.now(), LocalDate.now(), "Recklinghausen");
		FAHRSTUNDE_MANAGER.addFahrstunde(fahrstunde);
		FAHRSTUNDE_MANAGER.deleteFahrstunde(fahrstunde);
//		TODO need to be implemented
		assertNull(FAHRSTUNDE_MANAGER.getFahrstunde(fahrstunde.getId()));
	}
	
}
