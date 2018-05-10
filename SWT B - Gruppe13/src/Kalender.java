import java.util.*;

public class Kalender {
	
	private TheoriestundeDao theoriestundeDao;
	private FahrstundeDao fahrstundeDao;
	private PruefungDao pruefungDao;
	
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
	

	/**
	 * @return the theoriestundeDao
	 */
	public TheoriestundeDao getTheoriestundeDao() {
		return theoriestundeDao;
	}

	/**
	 * @return the fahrstundeDao
	 */
	public FahrstundeDao getFahrstundeDao() {
		return fahrstundeDao;
	}

	/**
	 * @return the pruefungDao
	 */
	public PruefungDao getPruefungDao() {
		return pruefungDao;
	}


}
