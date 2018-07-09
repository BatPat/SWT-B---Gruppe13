package datenhaltung;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fachlogik.FahrstundeDTO;
import fachlogik.HibernateUtil;
import fachlogik.MyLoggerUtil;
/**
 * Klasse die die Datenbankzugriffe auf die Fahrstunde-Tabelle ausführt.
 *
 */
public class FahrstundeDaoImpl implements FahrstundeDao {

	private static FahrstundeDaoImpl instance;
	private static SessionFactory sessionfactory = HibernateUtil.createSessionFactory();
	private Session session;
	private static Logger log = MyLoggerUtil.createLogger();

	private FahrstundeDaoImpl() {

	}

	//Singleton-Pattern das die Anzahl der FahrstundeDaoImpl auf eins begrenzt und so verhindert das die Datenbank mit vielen Anfragen blockiert wird.
	public static FahrstundeDaoImpl getInstance() {
		if (instance == null) {
			instance = new FahrstundeDaoImpl();
		}
		log.fine(" Singleton-Instanz von Fahrstunde wurde erzeugt. ");
		return instance;
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Abfrage gegen die Datenbank läuft und als Ergebnismenge alle 
	//Fahrstunden zurückliefert, welche dann zurückgegeben werden.
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
		log.info(" Liste der Fahrstunden wurde geladen. ");
		return liste;
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Fahrstunde in der Datenbanktabelle gespeichert wird.
	@Override
	public void addFahrstunde(FahrstundeDTO fahrstunde) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.save(fahrstunde);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Fahrstunde : Für Fahrlschueler : " + fahrstunde.getSchueler() + "bei dem Fahrlehrer: "
				+ fahrstunde.getLehrer() + " wurde hinzugefügt. ");
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Fahrstunde in der Datenbanktabelle verändert wird.
	@Override
	public void updateFahrstunde(FahrstundeDTO fahrstunde) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.update(fahrstunde);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Fahrstunde : Für Fahrlschueler : " + fahrstunde.getSchueler() + "bei dem Fahrlehrer: "
				+ fahrstunde.getLehrer() + " wurde verändert. ");
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Fahrstunde in der Datenbanktabelle gelöscht wird.
	@Override
	public void deleteFahrstunde(FahrstundeDTO fahrstunde) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.delete(fahrstunde);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Fahrstunde : Für Fahrlschueler : " + fahrstunde.getSchueler() + "bei dem Fahrlehrer: "
				+ fahrstunde.getLehrer() + " wurde gelöscht. ");
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Fahrstunde aus der Datenbanktabelle geladen wird.
	@Override
	public FahrstundeDTO getFahrstunde(int fahrstundeId) {
		FahrstundeDTO fahrstunde = null;
		session = sessionfactory.openSession();
		session.beginTransaction();
		fahrstunde = (FahrstundeDTO) session.get(FahrstundeDTO.class, fahrstundeId);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Fahrstunde : Für Fahrlschueler : " + fahrstunde.getSchueler() + "bei dem Fahrlehrer: "
				+ fahrstunde.getLehrer() + " wurde geladen. ");
		return fahrstunde;
	}
}
