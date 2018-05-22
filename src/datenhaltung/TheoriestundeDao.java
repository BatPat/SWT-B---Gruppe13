package datenhaltung;
import java.util.List;

import fachlogik.Theoriestunde;

public interface TheoriestundeDao {
	
	public List<Theoriestunde> getAlleTheoriestunden();
	
	public void addTheoriestunde(Theoriestunde theoriestunde);
	
	public void updateTheoriestunde(Theoriestunde theoriestunde);
	
	public void deleteTheoriestunde(Theoriestunde theoriestunde);
}
