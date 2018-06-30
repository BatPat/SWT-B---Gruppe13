package datenhaltung;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fachlogik.HibernateUtil;
import fachlogik.MyLoggerUtil;
import fachlogik.TheoriestundeDTO;

public class TheorieStundeDaoImpl implements TheoriestundeDao {

	private static TheorieStundeDaoImpl instance;
	private static SessionFactory sessionfactory = HibernateUtil.createSessionFactory();
	private Session session;
	private static Logger log = MyLoggerUtil.createLogger();

	private TheorieStundeDaoImpl() {

	}

	public static TheorieStundeDaoImpl getInstance() {
		if (instance == null) {
			instance = new TheorieStundeDaoImpl();
		}
		log.fine(" Singleton-Instanz von Theoriestunde wurde erzeugt. ");
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TheoriestundeDTO> getAlleTheoriestunden() {
		List<TheoriestundeDTO> liste = new ArrayList<>();
		session = sessionfactory.openSession();
		session.beginTransaction();
		liste = session.createQuery("from TheoriestundeDTO").list();
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Liste der Theoriestunden wurde geladen. ");
		return liste;
	}

	@Override
	public void addTheoriestunde(TheoriestundeDTO theoriestunde) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.save(theoriestunde);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Theoriestunde : über " + theoriestunde.getThema() + "bei dem Fahrlehrer: "
				+ theoriestunde.getFahrlehrer() + " wurde hinzugefügt. ");
	}

	@Override
	public void updateTheoriestunde(TheoriestundeDTO theoriestunde) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.update(theoriestunde);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Theoriestunde : über " + theoriestunde.getThema() + "bei dem Fahrlehrer: "
				+ theoriestunde.getFahrlehrer() + " wurde verändert. ");
	}

	@Override
	public void deleteTheoriestunde(TheoriestundeDTO theoriestunde) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.delete(theoriestunde);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Theoriestunde : über " + theoriestunde.getThema() + "bei dem Fahrlehrer: "
				+ theoriestunde.getFahrlehrer() + " wurde gelöscht. ");
	}

	@Override
	public TheoriestundeDTO getTheoriestunde(int theoriestundeId) {
		TheoriestundeDTO theoriestunde = null;
		session = sessionfactory.openSession();
		session.beginTransaction();
		theoriestunde = (TheoriestundeDTO) session.get(TheoriestundeDTO.class, theoriestunde);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Theoriestunde : über " + theoriestunde.getThema() + "bei dem Fahrlehrer: "
				+ theoriestunde.getFahrlehrer() + " wurde geladen. ");
		return theoriestunde;
	}
}
