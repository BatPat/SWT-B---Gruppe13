package datenhaltung;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datenhaltung.FahrschuelerDaoImpl;
import fachlogik.FahrschuelerDTO;
import fachlogik.HibernateUtil;

public class FahrschuelerDAOTest {
	private static final FahrschuelerDaoImpl FAHRSCHUELER_MANAGER = FahrschuelerDaoImpl.getInstance();
	private Session session;

	public FahrschuelerDAOTest() {
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
	public void test_GetAll_Fahrschueler_andGetOk() {
		FahrschuelerDTO fahrschueler = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51","0321-4589347","05.12.2000","B");
		FahrschuelerDTO fahrschueler2 = new FahrschuelerDTO("Julius Blanke", "51123", "Hagen", "Runhweg", "32","0321-4589347","04.12.1995","B");
		List<FahrschuelerDTO> fahrschuelerliste = new ArrayList<FahrschuelerDTO>();
		fahrschuelerliste.add(fahrschueler);
		fahrschuelerliste.add(fahrschueler2);
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler);
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler2);
		assertThat(fahrschuelerliste.size()).isEqualTo(FAHRSCHUELER_MANAGER.getAlleFahrschueler().size());
	}

	@Test
	public void test_Save_Fahrschueler_andGetOk() {
		FahrschuelerDTO fahrschueler = new FahrschuelerDTO("Julius Blanke", "51123", "Hagen", "Runhweg", "32","0321-4589347","04.12.1995","B");
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler);
		FahrschuelerDTO fahrschueler2 = FAHRSCHUELER_MANAGER.getFahrschueler(fahrschueler.getId());
		assertThat("Julius Blanke").isEqualTo(fahrschueler2.getName());
	}
	
	@Test
	public void test_Update_Fahrschueler_andGetOk() {
		FahrschuelerDTO fahrschueler = new FahrschuelerDTO("Julius Blanke", "51123", "Hagen", "Runhweg", "32","0321-4589347","04.12.1995","B");
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler);
		fahrschueler.setStrasse("Test-Straße");
		fahrschueler.setHausnummer("7");
		fahrschueler.setPlz("45234");
		FAHRSCHUELER_MANAGER.updateFahrschueler(fahrschueler);
		FahrschuelerDTO fahrschueler2 = FAHRSCHUELER_MANAGER.getFahrschueler(fahrschueler.getId());
		assertThat("Test-Straße").isEqualTo(fahrschueler2.getStrasse());
	}
	
	@Test
	public void test_Delete_Fahrschueler_andDontGetOk() {
		FahrschuelerDTO fahrschueler = new FahrschuelerDTO("Julius Blanke", "51123", "Hagen", "Runhweg", "32","0321-4589347","04.12.1995","B");
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler);
		FahrschuelerDTO fahrschueler2 = FAHRSCHUELER_MANAGER.getFahrschueler(fahrschueler.getId());
		assertThat("Julius Blanke").isEqualTo(fahrschueler2.getName());
		FAHRSCHUELER_MANAGER.deleteFahrschueler(fahrschueler);
		assertNull(FAHRSCHUELER_MANAGER.getFahrschueler(fahrschueler.getId()));
	}

//	@Test
//	public void test_AddTheoriestunde_To_Fahrschueler_andGetOk() {
//		FahrschuelerDTO fahrschueler = new FahrschuelerDTO("Julius Blanke", "51123", "Hagen", "Runhweg", "32","0321-4589347","04.12.1995","B");
//		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler);
//		FahrschuelerDTO fahrschueler2 = FAHRSCHUELER_MANAGER.getFahrschueler(fahrschueler.getId());
//		assertThat("Peter Jung").isEqualTo(fahrschueler2.getName());
//	}

}
