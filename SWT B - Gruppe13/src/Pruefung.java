import java.util.List;

public class Pruefung implements Termin {
	
	private long genid;
	private static long counter = 0;

	//TODO sinnvoller Konstruktor
	public Pruefung() {
		this.genid = counter++;
	}	

	@Override
	public List<Person> getBeteiligtePersonen() {
		// TODO sinnvolle implementierung
		return null;
	}
	
	public long getGenid() {
		return genid;
	}
	//TODO Attribute für Status und Art der Prüfung 

}
