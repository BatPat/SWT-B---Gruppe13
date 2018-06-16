package datenhaltung;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import fachlogik.FahrschuelerDTO;

public class FahrschuelerDAOTest {
	private static final FahrschuelerDaoImpl FAHRSCHUELER_MANAGER = FahrschuelerDaoImpl.getInstance();

	public FahrschuelerDAOTest() {
	}

	@Test
	public void saveFahrschueler() {
		FahrschuelerDTO fahrschueler = new FahrschuelerDTO("Peter Jung", "41743", "Dortmund", "Perss-Alle", "51");
		FAHRSCHUELER_MANAGER.addFahrschueler(fahrschueler);
		FahrschuelerDTO fahrschueler2 = FAHRSCHUELER_MANAGER.getFahrschueler(fahrschueler.getId());
		assertThat("Peter Jung").isEqualTo(fahrschueler2.getName());
	}

}
