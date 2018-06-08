package fachlogik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;
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
	private Session session;

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
	
	public void saveFahrlehrer(FahrlehrerDTO f) {
		session = createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.save("fahrlehrer",f);
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateFahrlehrer(FahrlehrerDTO f) {
		session = createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.update("fahrlehrer",f);
		session.getTransaction().commit();
		session.close();
	}
	
	public void deleteFahrlehrer(FahrlehrerDTO f) {
		session = createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.delete("fahrlehrer", f);;
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<FahrlehrerDTO> getAllFahrlehrer() {
		List<FahrlehrerDTO> listeallefahrlehrer = new ArrayList<>();
		session = createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		listeallefahrlehrer = session.createQuery("from fahrlehrer").list();
		session.getTransaction().commit();
		session.close();
		return listeallefahrlehrer;
	}
	
	public FahrlehrerDTO getFahrlehrerById(String fahrlehrerName) {
        session = null;
        FahrlehrerDTO fahrlehrer = null;
        try {
            session = HibernateUtil.createSessionFactory().openSession();
            fahrlehrer =  (FahrlehrerDTO) session.get(FahrlehrerDTO.class.getName().substring(0, FahrlehrerDTO.class.getName().length() - 3).toLowerCase(), fahrlehrerName);
            Hibernate.initialize(fahrlehrer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return fahrlehrer;
    }
}
