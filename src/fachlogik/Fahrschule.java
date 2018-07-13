package fachlogik;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import datenhaltung.FahrlehrerDao;
import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDao;
import datenhaltung.FahrschuelerDaoImpl;
import datenhaltung.FahrstundeDao;
import datenhaltung.FahrstundeDaoImpl;

public class Fahrschule {
	
	private FahrschuelerDao schuelerDao;
	private FahrlehrerDao lehrerDao;
	private FahrstundeDao fahrStundeDao;
	private List<Fuehrerscheinklasse> angeboteneKlassen;
	
	
	public Fahrschule() {
		super();
		this.schuelerDao = FahrschuelerDaoImpl.getInstance();
		this.lehrerDao = FahrlehrerDaoImpl.getInstance();
		this.fahrStundeDao = FahrstundeDaoImpl.getInstance();
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
	
	public int[] getAnzSonderAndStandardFahrten(int fahrschuelerId) {
		// momentan werden immer beide werte auf einmal benoetigt, deshalb diese methode mit nur einem datenbankzugriff und einer schleife
		List<FahrstundeDTO> fstunden = schuelerDao.getFahrschueler(fahrschuelerId).getFahrstunden();
		int[] anzFahrten = new int[2];
		for (FahrstundeDTO fahrstundeDTO : fstunden) {
			if(fahrstundeDTO.getArt() == Fahrstundenart.B_SONDERFAHRT) {
				anzFahrten[0]++;
			}
			if(fahrstundeDTO.getArt() == Fahrstundenart.B_STANDARDFAHRT) {
				anzFahrten[1]++;
			}
		}
		return anzFahrten;
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

	public void addPerson(PersonInfo p) {
		PersonFactory pFactory = new PersonFactory();
		pFactory.createPerson(p.getPersonType(), p.getName(), p.getPlz(), p.getWohnort(), p.getStrasse(), p.getHausnummer(), p.getTelefonnummer(), p.getGeburtsdatum(), p.getFuehrerscheinklasse());
	}

	public List<PersonInfo> getFahrlehrerInfoListe() {
		return extractPersonInfos(getFahrlehrerListe());
	}
	
	public List<PersonInfo> getFahrschuelerInfoListe() {
		return extractPersonInfos(getFahrschuelerListe());
	}

	private List<PersonInfo> extractPersonInfos(List<? extends Person> pListe) {
		List<PersonInfo> infoListe = new ArrayList<>();
		for (Person person : pListe) {
			infoListe.add(extractPersonInfo(person));
		}
		return infoListe;
	}
	
	public PersonInfo getFahrschuelerInfo(int fahrschuelerId) {
		return extractPersonInfo(getFahrschueler(fahrschuelerId));
	}
	
	public PersonInfo getFahrlehrerInfo(int fahrlehrerId) {
		return extractPersonInfo(getFahrlehrer(fahrlehrerId));
	}

	private PersonInfo extractPersonInfo(Person person) {
		PersonInfo pInfo = new PersonInfo();
		pInfo.setId(person.getId());
		pInfo.setPersonType(person.getPersonType());
		pInfo.setName(person.getName());
		pInfo.setPlz(person.getPlz());
		pInfo.setWohnort(person.getWohnort());
		pInfo.setStrasse(person.getStrasse());
		pInfo.setHausnummer(person.getHausnummer());
		pInfo.setTelefonnummer(person.getTelefonnummer());
		pInfo.setGeburtsdatum(person.getGeburtsdatum());
		pInfo.setFuehrerscheinklasse(person.getFuehrerscheinklasse());
		return pInfo;
	}

	public void druckeRechnungPdf(int fahrschuelerId, Properties fahrschulproperties) {
		try {
			new PdfDocumentBill().createPdf(getFahrschueler(fahrschuelerId), fahrschulproperties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bucheFahrstunde(Fahrstundenart art, int fSchuelerId, int fLehrerId, LocalDate datum, LocalTime zeit, String ort) {
		fahrStundeDao.addFahrstunde(new FahrstundeDTO(art, getFahrlehrer(fLehrerId), getFahrschueler(fSchuelerId), zeit, datum, ort));
	}

}
