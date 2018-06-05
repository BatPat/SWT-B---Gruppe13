package fachlogik;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

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

	public void saveObject(Object o) {
		session = createSessionFactory().openSession();
		session.beginTransaction();
		// Hibernate.initialize(); entweder so oder statt lazy loading eager loading
		session.save(o);
		session.getTransaction().commit();
		session.close();
	}

	public Object getObject(Object o) {
		session = createSessionFactory().openSession();
		session.beginTransaction();
		o = session.get(FahrlehrerDTO.class, 0);
		return null;
	}
	
	public FahrlehrerDTO getUserById(Long idfahrlehrer) {
        session = null;
        FahrlehrerDTO fahrlehrer = null;
        try {
            session = HibernateUtil.createSessionFactory().openSession();
            fahrlehrer =  (FahrlehrerDTO) session.get(FahrlehrerDTO.class, idfahrlehrer);
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
