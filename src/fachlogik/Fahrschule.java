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

	public void updateFahrschueler(FahrschuelerDTO s) {
		schuelerDao.updateFahrschueler(s);
	}
	
	public void addFahrschueler(FahrschuelerDTO s) {
		schuelerDao.addFahrschueler(s);
	}


	public List<FahrschuelerDTO> getFahrschuelerListe() {
		return schuelerDao.getAlleFahrschueler();
	}

	public void removeFahrschueler(FahrschuelerDTO s) {
		schuelerDao.deleteFahrschueler(s);
	}

	public void updateFahrlehrer(FahrlehrerDTO l) {
		lehrerDao.updateFahrlehrer(l);
	}
	
	public void addFahrlehrer(FahrlehrerDTO l) {
		lehrerDao.addFahrlehrer(l);
	}

	public List<FahrlehrerDTO> getFahrlehrerListe() {
		return lehrerDao.getAlleFahrlehrer();
	}

	public void removeFahrlehrer(FahrlehrerDTO l) {
		lehrerDao.deleteFahrlehrer(l);
	}

	public FahrschuelerDTO getFahrschueler(int fahrschuelerId) {
		return schuelerDao.getFahrschueler(fahrschuelerId);
	}

	public FahrlehrerDTO getFahrlehrer(int fahlehrerId) {
		return lehrerDao.getFahrlehrer(fahlehrerId);
	}

	public List<Fuehrerscheinklasse> getAngeboteneKlassen() {
		return angeboteneKlassen;
	}

	public void setAngeboteneKlassen(List<Fuehrerscheinklasse> angeboteneKlassen) {
		this.angeboteneKlassen = angeboteneKlassen;
	}

}
