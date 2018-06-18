package datenhaltung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachlogik.FahrlehrerDTO;
import fachlogik.HibernateUtil;

public class FahrlehrerDAOTest {
	private static final FahrlehrerDaoImpl FAHRLEHRER_MANAGER = FahrlehrerDaoImpl.getInstance();
	private Session session;

	public FahrlehrerDAOTest() {
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
	public void test_GetAll_Fahrlehrer_andGetOk() {
		FahrlehrerDTO fahrlehrer = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3","0321-4589347","15.07.2000","B");
		FahrlehrerDTO fahrlehrer2 = new FahrlehrerDTO("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10","0321-573447","03.06.1999","B");
		List<FahrlehrerDTO> fahrlehreriste = new ArrayList<FahrlehrerDTO>();
		fahrlehreriste.add(fahrlehrer);
		fahrlehreriste.add(fahrlehrer2);
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer);
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer2);
		assertThat(fahrlehreriste.size()).isEqualTo(FAHRLEHRER_MANAGER.getAlleFahrlehrer().size());
	}

	@Test
	public void test_Save_Fahrlehrer_andGetOk() {
		FahrlehrerDTO fahrlehrer = new FahrlehrerDTO("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10","0321-573447","03.06.1999","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer);
		FahrlehrerDTO fahrlehrer2 = FAHRLEHRER_MANAGER.getFahrlehrer(fahrlehrer.getId());
		assertThat("Lukas Schmidt").isEqualTo(fahrlehrer2.getName());
	}
	
	@Test
	public void test_Update_Fahrlehrer_andGetOk() {
		FahrlehrerDTO fahrlehrer = new FahrlehrerDTO("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10","0321-573447","03.06.1999","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer);
		fahrlehrer.setStrasse("Test-Straße");
		fahrlehrer.setHausnummer("7");
		fahrlehrer.setPlz("45234");
		FAHRLEHRER_MANAGER.updateFahrlehrer(fahrlehrer);
		FahrlehrerDTO fahrlehrer2 = FAHRLEHRER_MANAGER.getFahrlehrer(fahrlehrer.getId());
		assertThat("Test-Straße").isEqualTo(fahrlehrer2.getStrasse());
	}
	
	@Test
	public void test_Delete_Fahrlehrer_andDontGetOk() {
		FahrlehrerDTO fahrlehrer = new FahrlehrerDTO("Lukas Schmidt", "45231", "Bochum", "Marienweg", "10","0321-573447","03.06.1999","B");
		FAHRLEHRER_MANAGER.addFahrlehrer(fahrlehrer);
		FahrlehrerDTO fahrlehrer2 = FAHRLEHRER_MANAGER.getFahrlehrer(fahrlehrer.getId());
		assertThat("Lukas Schmidt").isEqualTo(fahrlehrer2.getName());
		FAHRLEHRER_MANAGER.deleteFahrlehrer(fahrlehrer);
		assertNull(FAHRLEHRER_MANAGER.getFahrlehrer(fahrlehrer.getId()));
	}
}