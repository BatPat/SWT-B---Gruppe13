package fachlogik;
import java.time.LocalDateTime;
import java.util.*;

import datenhaltung.FahrlehrerDao;
import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDao;
import datenhaltung.FahrschuelerDaoImpl;
import datenhaltung.FahrstundeDao;

public class Fahrschule {
	
	private FahrschuelerDao schuelerDao;
	private FahrlehrerDao lehrerDao;
	private List<Fuehrerscheinklasse> angeboteneKlassen;
	
	
	public Fahrschule() {
		super();
		this.schuelerDao = FahrschuelerDaoImpl.getInstance();
		this.lehrerDao = FahrlehrerDaoImpl.getInstance();
		this.angeboteneKlassen = new ArrayList<>();
		for(Fuehrerscheinklasse f: Fuehrerscheinklasse.values()) {
			angeboteneKlassen.add(f);
		}
	}
	
	public List<LocalDateTime> getTermine(int fahrlehrerId){
		List<LocalDateTime> result = new ArrayList<>();
		List<Termin> termine = lehrerDao.getFahrlehrer(fahrlehrerId).getTermine();
		for (Termin termin : termine) {
			result.add(LocalDateTime.of(termin.getDatum(), termin.getUhrzeit()));
		}
		return result;		
	}
	
	public int getAnzTheoriestunden(int fahrschuelerId){
		return schuelerDao.getFahrschueler(fahrschuelerId).getTheoriestunden().size();
	}
	
	public int getAnzSonderfahrten(int fahrschuelerId){
		List<FahrstundeDTO> fstunden = schuelerDao.getFahrschueler(fahrschuelerId).getFahrstunden();
		int anzSonderfahrten = 0;
		for (FahrstundeDTO fahrstundeDTO : fstunden) {
			if(fahrstundeDTO.getArt() == Fahrstundenart.B_SONDERFAHRT) {
				anzSonderfahrten++;
			}
		}
		return anzSonderfahrten;
	}
	
	public int getAnzStandardfahrten(int fahrschuelerId){
		List<FahrstundeDTO> fstunden = schuelerDao.getFahrschueler(fahrschuelerId).getFahrstunden();
		int anzStandardfahrten = 0;
		for (FahrstundeDTO fahrstundeDTO : fstunden) {
			if(fahrstundeDTO.getArt() == Fahrstundenart.B_STANDARDFAHRT) {
				anzStandardfahrten++;
			}
		}
		return anzStandardfahrten;
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
