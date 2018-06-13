package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import fachlogik.FahrlehrerDTO;
import fachlogik.HibernateUtil;

public class FahrlehrerDaoImpl implements FahrlehrerDao {

	private static FahrlehrerDaoImpl instance;
	private final Session session = HibernateUtil.createSessionFactory().openSession();
	private static final String TABELLENNAME = "fahrlehrer";

	private FahrlehrerDaoImpl() {

	}

	public static FahrlehrerDaoImpl getInstance() {
		if (instance == null) {
			instance = new FahrlehrerDaoImpl();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FahrlehrerDTO> getAlleFahrlehrer() {
		List<FahrlehrerDTO> liste = new ArrayList<>();
//		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		liste = session.createQuery("from fahrlehrer").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addFahrlehrer(FahrlehrerDTO fahrlehrer) {
//		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.save(fahrlehrer);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrlehrer(FahrlehrerDTO fahrlehrer) {
//		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.saveOrUpdate(fahrlehrer);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrlehrer(FahrlehrerDTO fahrlehrer) {
//		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.delete(fahrlehrer);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public FahrlehrerDTO getFahrlehrer(int fahrlehrerid) {
		FahrlehrerDTO fahrlehrer = null;
//		session = null;
		try {
//			session = HibernateUtil.createSessionFactory().openSession();
			fahrlehrer = (FahrlehrerDTO) session.get(TABELLENNAME, fahrlehrerid);
			Hibernate.initialize(fahrlehrer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return fahrlehrer;
	}

}
