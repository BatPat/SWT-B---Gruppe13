package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import fachlogik.FahrlehrerDTO;
import fachlogik.FahrschuelerDTO;
import fachlogik.HibernateUtil;

public class FahrschuelerDaoImpl implements FahrschuelerDao {
	
	private static FahrschuelerDaoImpl instance;
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
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		liste = session.createQuery("from FahrschuelerDTO").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.save(fahrschueler);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.update(fahrschueler);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.delete(fahrschueler);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public FahrschuelerDTO getFahrschueler(int fahrschuelerId) {
		FahrschuelerDTO fahrschueler = null;
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		fahrschueler = (FahrschuelerDTO) session.get(FahrschuelerDTO.class, fahrschuelerId);
		session.getTransaction().commit();
		session.close();
		return fahrschueler;
	}

}
