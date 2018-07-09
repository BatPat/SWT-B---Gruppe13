package datenhaltung;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fachlogik.HibernateUtil;
import fachlogik.MyLoggerUtil;
import fachlogik.PruefungDTO;
/**
 * Klasse die die Datenbankzugriffe auf die Prüfung-Tabelle ausführt.
 *
 */
public class PruefungDaoImpl implements PruefungDao {

	private static PruefungDaoImpl instance;
	private static SessionFactory sessionfactory = HibernateUtil.createSessionFactory();
	private Session session;
	private static Logger log = MyLoggerUtil.createLogger();

	private PruefungDaoImpl() {

	}

	//Singleton-Pattern das die Anzahl der PruefungDaoImpl auf eins begrenzt und so verhindert das die Datenbank mit vielen Anfragen blockiert wird.
	public static PruefungDaoImpl getInstance() {
		if (instance == null) {
			instance = new PruefungDaoImpl();
		}
		log.fine(" Singleton-Instanz von Pruefung wurde erzeugt. ");
		return instance;
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Abfrage gegen die Datenbank läuft und als Ergebnismenge alle 
	//Pruefungen zurückliefert, welche dann zurückgegeben werden.
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
		log.info(" Liste der Pruefungen wurde geladen. ");
		return liste;
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Pruefung in der Datenbanktabelle gespeichert wird.
	@Override
	public void addPruefung(PruefungDTO pruefung) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.save(pruefung);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Pruefung : Für Fahrlschueler : " + pruefung.getFahrschueler() + "bei dem Fahrlehrer: "
				+ pruefung.getFahrlehrer() + " wurde hinzugefügt. ");
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Pruefung in der Datenbanktabelle verändert wird.
	@Override
	public void updatePruefung(PruefungDTO pruefung) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.update(pruefung);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Pruefung : Für Fahrlschueler : " + pruefung.getFahrschueler() + "bei dem Fahrlehrer: "
				+ pruefung.getFahrlehrer() + " wurde verändert. ");
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Pruefung in der Datenbanktabelle gelöscht wird.
	@Override
	public void deletePruefung(PruefungDTO pruefung) {
		session = sessionfactory.openSession();
		session.beginTransaction();
		session.delete(pruefung);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Pruefung : Für Fahrlschueler : " + pruefung.getFahrschueler() + "bei dem Fahrlehrer: "
				+ pruefung.getFahrlehrer() + " wurde gelöscht. ");
	}

	//Über eine Session wird eine Transaktion begonnen, in der eine Pruefung aus der Datenbanktabelle gesladen wird.
	@Override
	public PruefungDTO getPruefung(int pruefungId) {
		PruefungDTO pruefung = null;
		session = sessionfactory.openSession();
		session.beginTransaction();
		pruefung = (PruefungDTO) session.get(PruefungDTO.class, pruefungId);
		session.flush();
		session.getTransaction().commit();
		session.close();
		log.info(" Pruefung : Für Fahrlschueler : " + pruefung.getFahrschueler() + "bei dem Fahrlehrer: "
				+ pruefung.getFahrlehrer() + " wurde geladen. ");
		return pruefung;
	}
}
