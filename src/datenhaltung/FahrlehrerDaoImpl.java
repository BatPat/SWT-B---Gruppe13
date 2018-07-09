package datenhaltung;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fachlogik.FahrlehrerDTO;
import fachlogik.HibernateUtil;
import fachlogik.MyLoggerUtil;
/**
 * Klasse die die Datenbankzugriffe auf die Fahrlehrer-Tabelle ausführt.
 *
 */
public class FahrlehrerDaoImpl implements FahrlehrerDao {

	private static FahrlehrerDaoImpl instance;
	private static SessionFactory sessionfactory = HibernateUtil.createSessionFactory();
	private Session session;
	private static Logger log = MyLoggerUtil.createLogger();

	private FahrlehrerDaoImpl() {

	}

	//Singleton-Pattern das die Anzahl der FahrlehrerDaoImpl auf eins begrenzt und so verhindert das die Datenbank mit vielen Anfragen blockiert wird.
	public static FahrlehrerDaoImpl getInstance() {
		if (instance == null) {
			instance = new FahrlehrerDaoImpl();
		}
		log.fine(" Singleton-Instanz von Fahrlehrer wurde erzeugt. ");
		return instance;
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Abfrage gegen die Datenbank läuft und als Ergebnismenge alle 
	//Fahrlehrer zurückliefert, welche dann zurückgegeben werden.
	@SuppressWarnings("unchecked")
	@Override
	public List<FahrlehrerDTO> getAlleFahrlehrer() {
		List<FahrlehrerDTO> liste = new ArrayList<>();
		session = sessionfactory.openSession();
		session.beginTransaction();
		liste = session.createQuery("from FahrlehrerDTO").list();
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Liste der Fahrlehrer wurde geladen. ");
		return liste;
	}

	//Über eine Session wird eine Transaktion begonnen, in der ein Fahrlehrer in der Datenbanktabelle gespeichert wird.
	@Override
	public void addFahrlehrer(FahrlehrerDTO fahrlehrer) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.save(fahrlehrer);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Fahrlehrer : " + fahrlehrer.getName() + " wurde hinzugefügt. ");
	}

	//Über eine Session wird eine Transaktion begonnen, in der ein Fahrlehrer in der Datenbanktabelle überarbeitet wird.
	@Override
	public void updateFahrlehrer(FahrlehrerDTO fahrlehrer) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(fahrlehrer);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Fahrlehrer : " + fahrlehrer.getName() + " wurde verändert. ");
	}

	//Über eine Session wird eine Transaktion begonnen, in der ein Fahrlehrer in der Datenbanktabelle gelöscht wird.
	@Override
	public void deleteFahrlehrer(FahrlehrerDTO fahrlehrer) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.delete(fahrlehrer);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Fahrlehrer : " + fahrlehrer.getName() + " wurde gelöscht. ");
	}

	//Über eine Session wird eine Transaktion begonnen, in der ein Fahrlehrer aus der Datenbanktabelle geladen wird.
	@Override
	public FahrlehrerDTO getFahrlehrer(int fahrlehrerid) {
		FahrlehrerDTO fahrlehrer = null;
		session = sessionfactory.openSession();
		session.beginTransaction();
		fahrlehrer = (FahrlehrerDTO) session.get(FahrlehrerDTO.class, fahrlehrerid);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Fahrlehrer : " + fahrlehrer.getName() + " wurde geladen. ");
		return fahrlehrer;
	}

}
