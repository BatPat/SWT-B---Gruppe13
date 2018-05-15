package fachlogik;
import java.util.*;

import datenhaltung.FahrlehrerDao;
import datenhaltung.FahrschuelerDao;

public class Fahrschule {
	
	private FahrschuelerDao schuelerDao;
	private FahrlehrerDao lehrerDao;
	
	public Fahrschule() {
		super();
	}

	public Fahrschule(FahrschuelerDao schuelerDao, FahrlehrerDao lehrerDao) {
		super();
		this.schuelerDao = schuelerDao;
		this.lehrerDao = lehrerDao;
	}

	public void updateFahrschueler(Fahrschueler s) {
		schuelerDao.updateFahrschueler(s);
	}
	
	public void addFahrschueler(Fahrschueler s) {
		schuelerDao.addFahrschueler(s);
	}


	public List<Fahrschueler> getFahrschuelerListe() {
		return schuelerDao.getAlleFahrschueler();
	}

	public void removeFahrschueler(Fahrschueler s) {
		schuelerDao.deleteFahrschueler(s);
	}

	public void updateFahrlehrer(Fahrlehrer l) {
		lehrerDao.updateFahrlehrer(l);
	}
	
	public void addFahrlehrer(Fahrlehrer l) {
		lehrerDao.addFahrlehrer(l);
	}

	public List<Fahrlehrer> getFahrlehrerListe() {
		return lehrerDao.getAlleFahrlehrer();
	}

	public void removeFahrlehrer(Fahrlehrer l) {
		lehrerDao.deleteFahrlehrer(l);
	}

}
