import java.util.List;

public class Theoriestunde extends Stunde {
	
	private TheorieThema thema;
	private long genid;
	public long getGenid() {
		return genid;
	}

	private static long counter = 0;

	
	@Override
	public List<Person> getBeteiligtePersonen() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Theoriestunde() {
		this.genid = counter++;
	}
	
}
