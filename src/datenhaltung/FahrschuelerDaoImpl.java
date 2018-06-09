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
		session.persist("fahrschueler", fahrschueler.getName());
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.update("fahrschueler", fahrschueler.getName());
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.delete("fahrschueler", fahrschueler.getName());
		;
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public FahrschuelerDTO getFahrschueler(String fahrschuelerName) {
		FahrschuelerDTO fahrschueler = null;
		session = null;
		try {
			session = HibernateUtil.createSessionFactory().openSession();
			fahrschueler = (FahrschuelerDTO) session.get(FahrschuelerDTO.class.getName().substring(0, FahrschuelerDTO.class.getName().length() - 3).toLowerCase(), fahrschuelerName);
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
