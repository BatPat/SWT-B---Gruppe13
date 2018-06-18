package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fachlogik.FahrstundeDTO;
import fachlogik.HibernateUtil;

public class FahrstundeDaoImpl implements FahrstundeDao {

	private static FahrstundeDaoImpl instance;
	private static SessionFactory sessionfactory = HibernateUtil.createSessionFactory();
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
		session = sessionfactory.openSession();
		session.beginTransaction();
		liste = session.createQuery("from FahrstundeDTO").list();
		session.flush();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addFahrstunde(FahrstundeDTO fahrstunde) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.save(fahrstunde);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrstunde(FahrstundeDTO fahrstunde) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.update(fahrstunde);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrstunde(FahrstundeDTO fahrstunde) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.delete(fahrstunde);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public FahrstundeDTO getFahrstunde(int fahrstundeId) {
		FahrstundeDTO fahrstunde = null;
		session = sessionfactory.openSession();
		session.beginTransaction();
		fahrstunde = (FahrstundeDTO) session.get(FahrstundeDTO.class, fahrstundeId);
		session.flush();
		session.getTransaction().commit();
		session.close();
		return fahrstunde;
	}
}
