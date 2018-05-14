package Datenhaltung;
import java.util.List;
import Fachlogik.Theoriestunde;

public interface TheoriestundeDao {
	
	public List<Theoriestunde> getAlleTheoriestunden();
	
	public void addTheoriestunde(Theoriestunde theoriestunde);
	
	public void updateTheoriestunde(Theoriestunde theoriestunde);
	
	public void deleteTheoriestunde(Theoriestunde theoriestunde);
}
