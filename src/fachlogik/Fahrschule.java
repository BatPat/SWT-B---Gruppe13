package fachlogik;
import java.util.*;

import datenhaltung.FahrlehrerDao;
import datenhaltung.FahrschuelerDao;

public class Fahrschule {
	
	private FahrschuelerDao schuelerDao;
	private FahrlehrerDao lehrerDao;
	private List<Fuehrerscheinklasse> angeboteneKlassen;
	
	
	public Fahrschule(FahrschuelerDao schuelerDao, FahrlehrerDao lehrerDao, List<Fuehrerscheinklasse> angeboteneKlassen) {
		super();
		this.schuelerDao = schuelerDao;
		this.lehrerDao = lehrerDao;
		this.setAngeboteneKlassen(angeboteneKlassen);
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

	public Fahrschueler getFahrschueler(String fahrschuelername) {
		return schuelerDao.getFahrschueler(fahrschuelername);
	}

	public Fahrlehrer getFahrlehrer(String fahlehrername) {
		return lehrerDao.getFahrlehrer(fahlehrername);
	}

	public List<Fuehrerscheinklasse> getAngeboteneKlassen() {
		return angeboteneKlassen;
	}

	public void setAngeboteneKlassen(List<Fuehrerscheinklasse> angeboteneKlassen) {
		this.angeboteneKlassen = angeboteneKlassen;
	}

}
