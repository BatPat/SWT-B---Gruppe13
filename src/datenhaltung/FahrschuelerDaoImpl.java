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
	private static final String TABELLENNAME = "fahrschueler";

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
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		liste = session.createQuery("from fahrschueler").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.persist(fahrschueler);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.update(fahrschueler);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.delete(fahrschueler);
		;
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public FahrschuelerDTO getFahrschueler(int fahrschuelerId) {
		FahrschuelerDTO fahrschueler = null;
		session = null;
		try {
			session = HibernateUtil.createSessionFactory().openSession();
			fahrschueler = (FahrschuelerDTO) session.get(TABELLENNAME, fahrschuelerId);
			Hibernate.initialize(fahrschueler);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return fahrschueler;
	}

}
