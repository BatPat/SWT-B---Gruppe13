package datenhaltung;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import fachlogik.FahrschuelerDTO;
import fachlogik.HibernateUtil;
import fachlogik.TheoriestundeDTO;

public class FahrschuelerDaoImpl implements FahrschuelerDao {
	
	private static FahrschuelerDaoImpl instance;
	private Session session;

	private FahrschuelerDaoImpl() {
		
	}
	
	public static FahrschuelerDaoImpl getInstance() {
		if(instance == null) {
			instance = new FahrschuelerDaoImpl();
		}
		return instance;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<FahrschuelerDTO> getAlleFahrschueler() {
		List<FahrschuelerDTO> liste = new ArrayList<>();
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		liste = session.createQuery("from FahrschuelerDTO").list();
		session.getTransaction().commit();
		session.close();
		return liste;
	}

	@Override
	public void addFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.save(fahrschueler);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		session.update(fahrschueler);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteFahrschueler(FahrschuelerDTO fahrschueler) {
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createNativeQuery("Delete from theoriestunden_fahrschueler where idfahrschueler=" + fahrschueler.getId());
		q.executeUpdate();
		session.delete(fahrschueler);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public FahrschuelerDTO getFahrschueler(int fahrschuelerId) {
		FahrschuelerDTO fahrschueler = null;
		session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		fahrschueler = (FahrschuelerDTO) session.get(FahrschuelerDTO.class, fahrschuelerId);
		session.getTransaction().commit();
		session.close();
		return fahrschueler;
	}

	public void addTheoriestunde(FahrschuelerDTO fahrschueler, TheoriestundeDTO theostd1) {
		Session session = HibernateUtil.createSessionFactory().openSession();
		session.beginTransaction();
		FahrschuelerDTO fahrschuelerDTO = session.get(FahrschuelerDTO.class, fahrschueler.getId());
		fahrschuelerDTO.addTheoriestunde(theostd1);
		session.getTransaction().commit();
		session.close();
	}

}
