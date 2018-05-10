import java.util.List;

public class Pruefung implements Termin {
	
<<<<<<< HEAD
	//TODO sinnvoller Konstruktor
	
=======
	private long genid;
	private static long counter = 0;

	//TODO sinnvoller Konstruktor
	public Pruefung() {
		this.genid = counter++;
	}	

>>>>>>> featureBackEnd01
	@Override
	public List<Person> getBeteiligtePersonen() {
		// TODO sinnvolle implementierung
		return null;
	}
	
<<<<<<< HEAD
=======
	public long getGenid() {
		return genid;
	}
>>>>>>> featureBackEnd01
	//TODO Attribute für Status und Art der Prüfung 

}
