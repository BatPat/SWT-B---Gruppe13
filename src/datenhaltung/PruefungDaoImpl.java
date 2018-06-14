package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import fachlogik.HibernateUtil;
import fachlogik.PruefungDTO;

public class PruefungDaoImpl implements PruefungDao {

	private static PruefungDaoImpl instance;
	private Session session;

	private PruefungDaoImpl() {
		
	}
	
	public static PruefungDaoImpl getInstance() {
		if(instance == null) {
			instance = new PruefungDaoImpl();
		}
		return instance;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<PruefungDTO> getAllePruefungen() {
		List<PruefungDTO> liste = new ArrayList<>();
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		liste = session.createQuery("from PruefungDTO").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addPruefung(PruefungDTO pruefung) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.save(pruefung);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updatePruefung(PruefungDTO pruefung) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.update(pruefung);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deletePruefung(PruefungDTO pruefung) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.delete(pruefung);
		session.getTransaction().commit();
		session.close();
	}

}
