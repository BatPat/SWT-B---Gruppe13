package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import fachlogik.HibernateUtil;
import fachlogik.TheoriestundeDTO;

public class TheorieStundeDaoImpl implements TheoriestundeDao {

	private static TheorieStundeDaoImpl instance;
	private Session session;

	private TheorieStundeDaoImpl() {
		
	}
	
	public static TheorieStundeDaoImpl getInstance() {
		if(instance == null) {
			instance = new TheorieStundeDaoImpl();
		}
		return instance;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<TheoriestundeDTO> getAlleTheoriestunden() {
		List<TheoriestundeDTO> liste = new ArrayList<>();
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		liste = session.createQuery("from TheoriestundeDTO").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addTheoriestunde(TheoriestundeDTO theoriestunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.persist(theoriestunde);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateTheoriestunde(TheoriestundeDTO theoriestunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.update(theoriestunde);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteTheoriestunde(TheoriestundeDTO theoriestunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.delete(theoriestunde);
		session.getTransaction().commit();
		session.close();
	}

}
