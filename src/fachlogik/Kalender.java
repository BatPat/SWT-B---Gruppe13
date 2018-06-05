package fachlogik;
import java.util.*;

import datenhaltung.FahrstundeDao;
import datenhaltung.PruefungDao;
import datenhaltung.TheoriestundeDao;

public class Kalender {
	
	private TheoriestundeDao theoriestundeDao;
	private FahrstundeDao fahrstundeDao;
	private PruefungDao pruefungDao;
	
	public Kalender() {
		super();
	}

	public Kalender(TheoriestundeDao theoriestundeDao, FahrstundeDao fahrstundeDao, PruefungDao pruefungDao) {
		super();
		this.theoriestundeDao = theoriestundeDao;
		this.fahrstundeDao = fahrstundeDao;
		this.pruefungDao = pruefungDao;
	}
	
	public List<Termin> getAlleTermine(){
		List<Termin> liste = new ArrayList<>();
		liste.addAll(theoriestundeDao.getAlleTheoriestunden());
		liste.addAll(fahrstundeDao.getAlleFahrstunden());
		liste.addAll(pruefungDao.getAllePruefungen());
		return liste;
	}
	

	public List<TheoriestundeDTO> getAlleTheoriestunden(){
		return theoriestundeDao.getAlleTheoriestunden();
	}
	
	public void addTheoriestunde(TheoriestundeDTO theoriestunde) {
		theoriestundeDao.addTheoriestunde(theoriestunde);
	}
	
	public void updateTheoriestunde(TheoriestundeDTO theoriestunde) {
		theoriestundeDao.updateTheoriestunde(theoriestunde);
	}
	
	public void deleteTheoriestunde(TheoriestundeDTO theoriestunde) {
		theoriestundeDao.deleteTheoriestunde(theoriestunde);
	}
	
	public List<FahrstundeDTO> getAlleFahrstunden(){
		return fahrstundeDao.getAlleFahrstunden();
	}
	
	public void addFahrstunde(FahrstundeDTO fahrstunde) {
		fahrstundeDao.addFahrstunde(fahrstunde);
	}
	
	public void updateFahrstunde(FahrstundeDTO fahrstunde) {
		fahrstundeDao.updateFahrstunde(fahrstunde);
	}
	
	public void deleteFahrstunde(FahrstundeDTO fahrstunde) {
		fahrstundeDao.deleteFahrstunde(fahrstunde);
	}

	public List<PruefungDTO> getAllePruefungen(){
		return pruefungDao.getAllePruefungen();
	}
	
	public void addPruefung(PruefungDTO pruefung) {
		pruefungDao.addPruefung(pruefung);
	}
	
	public void updatePruefung(PruefungDTO pruefung) {
		pruefungDao.updatePruefung(pruefung);
	}
	
	public void deletePruefung(PruefungDTO pruefung) {
		pruefungDao.deletePruefung(pruefung);
	}


}
