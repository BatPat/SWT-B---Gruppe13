package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import fachlogik.FahrlehrerDTO;
import fachlogik.HibernateUtil;

public class FahrlehrerDaoImpl implements FahrlehrerDao {
	
	private static FahrlehrerDaoImpl instance;
	private static HibernateUtil h;

	private FahrlehrerDaoImpl() {
		
	}
	
	public static FahrlehrerDaoImpl getInstance() {
		if(instance == null) {
			instance = new FahrlehrerDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<FahrlehrerDTO> getAlleFahrlehrer() {
		List<FahrlehrerDTO> liste = new ArrayList<>();
		//TODO
		return liste;
	}

	@Override
	public void addFahrlehrer(FahrlehrerDTO fahrlehrer) {
		h = new HibernateUtil();
		h.saveFahrlehrer(fahrlehrer);
	}

	@Override
	public void updateFahrlehrer(FahrlehrerDTO fahrlehrer) {
		//TODO
	}

	@Override
	public void deleteFahrlehrer(FahrlehrerDTO fahrlehrer) {
		//TODO
	}

	//TODO FahrlehrerDTO fahrlehrerid als übergabe bei get Fahrlehrer
	@Override
	public FahrlehrerDTO getFahrlehrer(String fahrlehrerName) {
		h = new HibernateUtil();
		return null; //TODO h.getObject(fahrlehrer.getID);
	}

}
