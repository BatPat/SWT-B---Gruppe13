package fachlogik;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
//	private static final Map<String, String> klassen_zu_tabellennamen;
//	static {
//		Map<String, String> aMap = new HashMap<String, String>();
//		aMap.put("FahrlehrerDTO","fahrlehrer");
//		aMap.put("FahrschuelerDTO","fahrschueler");
//		aMap.put("FahrstundeDTO","fahrstunde");
//		aMap.put("PruefungDTO","pruefung");		
//	    aMap.put("TheoriestundeDTO","theoriestunde");	
//		klassen_zu_tabellennamen = Collections.unmodifiableMap(aMap);
//	}
	//Erstellen einer SessionFactory die benutzt wird um über Sessions, Transaktion ausführen zu können, um Datenbankaktionen auszuführen.
	public static SessionFactory createSessionFactory() {			
		Configuration configuration = new Configuration();
		configuration.configure();
		final StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		configuration.addAnnotatedClass(FahrlehrerDTO.class);
		configuration.addAnnotatedClass(FahrschuelerDTO.class);
		configuration.addAnnotatedClass(FahrstundeDTO.class);
		configuration.addAnnotatedClass(PruefungDTO.class);
		configuration.addAnnotatedClass(TheoriestundeDTO.class);
		final SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
		return sessionFactory;
	}
}
