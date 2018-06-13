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
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		liste = session.createQuery("from theoriestunde").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addTheoriestunde(TheoriestundeDTO theoriestunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.save(theoriestunde);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateTheoriestunde(TheoriestundeDTO theoriestunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.update(theoriestunde);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteTheoriestunde(TheoriestundeDTO theoriestunde) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.delete(theoriestunde);
		session.getTransaction().commit();
		session.close();
	}

}
