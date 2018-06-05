package datenhaltung;
import java.util.List;

import fachlogik.TheoriestundeDTO;

public interface TheoriestundeDao {
	
	public List<TheoriestundeDTO> getAlleTheoriestunden();
	
	public void addTheoriestunde(TheoriestundeDTO theoriestunde);
	
	public void updateTheoriestunde(TheoriestundeDTO theoriestunde);
	
	public void deleteTheoriestunde(TheoriestundeDTO theoriestunde);
}
