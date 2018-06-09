package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import fachlogik.FahrlehrerDTO;
import fachlogik.HibernateUtil;

public class FahrlehrerDaoImpl implements FahrlehrerDao {

	private static FahrlehrerDaoImpl instance;
	private Session session;

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
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		liste = session.createQuery("from fahrlehrer").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addFahrlehrer(FahrlehrerDTO fahrlehrer) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.persist("fahrlehrer", fahrlehrer.getName());
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrlehrer(FahrlehrerDTO fahrlehrer) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.saveOrUpdate("fahrlehrer", fahrlehrer.getName());
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrlehrer(FahrlehrerDTO fahrlehrer) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.delete("fahrlehrer", fahrlehrer.getName());
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public FahrlehrerDTO getFahrlehrer(String fahrlehrerName) {
		// Session session = HibernateUtil.createSessionFactory().openSession();
		// String hql = "FROM fahrlehrer WHERE fahrlehrer.id = :fahrlehrer_id";
		// org.hibernate.Query query = session.createQuery(hql);
		// query.setParameter("fahrlehrer_id",fahrlehrerName);
		// FahrlehrerDTO fahrlehrer = (FahrlehrerDTO) ((Query) query).getSingleResult();

		FahrlehrerDTO fahrlehrer = null;
		session = null;
		try {
			session = HibernateUtil.createSessionFactory().openSession();
			fahrlehrer = (FahrlehrerDTO) session.get(FahrlehrerDTO.class.getName().substring(0, FahrlehrerDTO.class.getName().length() - 3).toLowerCase(), fahrlehrerName);
			Hibernate.initialize(fahrlehrer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return fahrlehrer;
		// return fahrlehrer;
	}

}
