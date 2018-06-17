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
		liste = session.createQuery("from FahrlehrerDTO").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addFahrlehrer(FahrlehrerDTO fahrlehrer) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.save(fahrlehrer);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrlehrer(FahrlehrerDTO fahrlehrer) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(fahrlehrer);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrlehrer(FahrlehrerDTO fahrlehrer) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.delete(fahrlehrer);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public FahrlehrerDTO getFahrlehrer(int fahrlehrerid) {
		FahrlehrerDTO fahrlehrer = null;
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		fahrlehrer = (FahrlehrerDTO) session.get(FahrlehrerDTO.class, fahrlehrerid);
		session.getTransaction().commit();
		session.close();
		return fahrlehrer;
	}

}
