package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

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
		liste = session.createQuery("from FahrstundeDTO").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addFahrstunde(FahrstundeDTO fahrstunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.save(fahrstunde);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrstunde(FahrstundeDTO fahrstunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.update(fahrstunde);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrstunde(FahrstundeDTO fahrstunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.delete(fahrstunde);
		session.getTransaction().commit();
		session.close();
	}

}
