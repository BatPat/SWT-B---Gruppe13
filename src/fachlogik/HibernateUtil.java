package fachlogik;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
//	private static final Map<String, String> klassen_zu_tabellennamen;
//	static {
//		Map<String, String> aMap = new HashMap<String, String>();
//		aMap.put("FahrlehrerDTO","fahrlehrer");
//		aMap.put("FahrschuelerDTO","fahrschueler");
//		aMap.put("FahrstundeDTO","fahrstunde");
//		aMap.put("PruefungDTO","pruefung");		
//		klassen_zu_tabellennamen = Collections.unmodifiableMap(aMap);
//	}
	private static SessionFactory sessionFactory;

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
}
