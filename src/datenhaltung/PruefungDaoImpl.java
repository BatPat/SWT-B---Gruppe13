package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fachlogik.HibernateUtil;
import fachlogik.PruefungDTO;

public class PruefungDaoImpl implements PruefungDao {

	private static PruefungDaoImpl instance;
	private static SessionFactory sessionfactory = HibernateUtil.createSessionFactory();
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
		session = sessionfactory.openSession();
		session.beginTransaction();
		liste = session.createQuery("from PruefungDTO").list();
		session.flush();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addPruefung(PruefungDTO pruefung) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.save(pruefung);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updatePruefung(PruefungDTO pruefung) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.update(pruefung);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deletePruefung(PruefungDTO pruefung) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.delete(pruefung);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public PruefungDTO getPruefung(int pruefungId) {
		PruefungDTO pruefung = null;
		session = sessionfactory.openSession();
		session.beginTransaction();
		pruefung = (PruefungDTO) session.get(PruefungDTO.class, pruefungId);
		session.flush();
		session.getTransaction().commit();
		session.close();
		return pruefung;
	}
}
