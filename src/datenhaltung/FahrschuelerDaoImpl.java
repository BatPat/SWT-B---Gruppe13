package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fachlogik.FahrschuelerDTO;
import fachlogik.HibernateUtil;
import fachlogik.TheoriestundeDTO;

public class FahrschuelerDaoImpl implements FahrschuelerDao {
	
	private static FahrschuelerDaoImpl instance;
	private static SessionFactory sessionfactory = HibernateUtil.createSessionFactory();
	private Session session;

	private FahrschuelerDaoImpl() {
		
	}
	
	public static FahrschuelerDaoImpl getInstance() {
		if(instance == null) {
			instance = new FahrschuelerDaoImpl();
		}
		return instance;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<FahrschuelerDTO> getAlleFahrschueler() {
		List<FahrschuelerDTO> liste = new ArrayList<>();
		session = sessionfactory.openSession();
		session.beginTransaction();
		liste = session.createQuery("from FahrschuelerDTO").list();
		session.flush();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addFahrschueler(FahrschuelerDTO fahrschueler) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.save(fahrschueler);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrschueler(FahrschuelerDTO fahrschueler) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.update(fahrschueler);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrschueler(FahrschuelerDTO fahrschueler) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		Query q = session.createNativeQuery("Delete from theoriestunden_fahrschueler where idfahrschueler=" + fahrschueler.getId());
		q.executeUpdate();
		session.delete(fahrschueler);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public FahrschuelerDTO getFahrschueler(int fahrschuelerId) {
		FahrschuelerDTO fahrschueler = null;
		session = sessionfactory.openSession();
		session.beginTransaction();
		fahrschueler = (FahrschuelerDTO) session.get(FahrschuelerDTO.class, fahrschuelerId);
		session.flush();
		session.getTransaction().commit();
		session.close();
		return fahrschueler;
	}

	public void addTheoriestunde(FahrschuelerDTO fahrschueler, TheoriestundeDTO theostd1) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		FahrschuelerDTO fahrschuelerDTO = session.get(FahrschuelerDTO.class, fahrschueler.getId());
		fahrschuelerDTO.addTheoriestunde(theostd1);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
