package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import fachlogik.FahrlehrerDTO;
import fachlogik.FahrstundeDTO;
import fachlogik.HibernateUtil;

public class FahrstundeDaoImpl implements FahrstundeDao {

	private static FahrstundeDaoImpl instance;
	private Session session;

	private FahrstundeDaoImpl() {
		
	}
	
	public static FahrstundeDaoImpl getInstance() {
		if(instance == null) {
			instance = new FahrstundeDaoImpl();
		}
		return instance;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<FahrstundeDTO> getAlleFahrstunden() {
		List<FahrstundeDTO> liste = new ArrayList<>();
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		liste = session.createQuery("from fahrstunde").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addFahrstunde(FahrstundeDTO fahrstunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.save(fahrstunde);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrstunde(FahrstundeDTO fahrstunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.update(fahrstunde);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrstunde(FahrstundeDTO fahrstunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.delete(fahrstunde);
		session.getTransaction().commit();
		session.close();
	}

}
