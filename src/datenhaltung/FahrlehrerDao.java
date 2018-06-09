package datenhaltung;
import java.util.List;

import fachlogik.FahrlehrerDTO;

public interface FahrlehrerDao {
	
	public List<FahrlehrerDTO> getAlleFahrlehrer();
	
	public void addFahrlehrer(FahrlehrerDTO fahrlehrer);
	
	public void updateFahrlehrer(FahrlehrerDTO fahrlehrer);
	
	public void deleteFahrlehrer(FahrlehrerDTO fahrlehrer);
	
	public FahrlehrerDTO getFahrlehrer(int fahrlehrerid);
}
