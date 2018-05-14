package Fachlogik;
import java.util.*;

import Datenhaltung.FahrstundeDao;
import Datenhaltung.PruefungDao;
import Datenhaltung.TheoriestundeDao;

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
		List<Termin> liste = new ArrayList<Termin>();
		liste.addAll(theoriestundeDao.getAlleTheoriestunden());
		liste.addAll(fahrstundeDao.getAlleFahrstunden());
		liste.addAll(pruefungDao.getAllePruefungen());
		return liste;
	}
	

	public List<Theoriestunde> getAlleTheoriestunden(){
		return theoriestundeDao.getAlleTheoriestunden();
	}
	
	public void addTheoriestunde(Theoriestunde theoriestunde) {
		theoriestundeDao.addTheoriestunde(theoriestunde);
	}
	
	public void updateTheoriestunde(Theoriestunde theoriestunde) {
		theoriestundeDao.updateTheoriestunde(theoriestunde);
	}
	
	public void deleteTheoriestunde(Theoriestunde theoriestunde) {
		theoriestundeDao.deleteTheoriestunde(theoriestunde);
	}
	
	public List<Fahrstunde> getAlleFahrstunden(){
		return fahrstundeDao.getAlleFahrstunden();
	}
	
	public void addFahrstunde(Fahrstunde fahrstunde) {
		fahrstundeDao.addFahrstunde(fahrstunde);
	}
	
	public void updateFahrstunde(Fahrstunde fahrstunde) {
		fahrstundeDao.updateFahrstunde(fahrstunde);
	}
	
	public void deleteFahrstunde(Fahrstunde fahrstunde) {
		fahrstundeDao.deleteFahrstunde(fahrstunde);
	}

	public List<Pruefung> getAllePruefungen(){
		return pruefungDao.getAllePruefungen();
	}
	
	public void addPruefung(Pruefung pruefung) {
		pruefungDao.addPruefung(pruefung);
	}
	
	public void updatePruefung(Pruefung pruefung) {
		pruefungDao.updatePruefung(pruefung);
	}
	
	public void deletePruefung(Pruefung pruefung) {
		pruefungDao.deletePruefung(pruefung);
	}


}
