package datenhaltung;
import java.util.List;

import fachlogik.Fahrlehrer;

public interface FahrlehrerDao {
	
	public List<Fahrlehrer> getAlleFahrlehrer();
	
	public void addFahrlehrer(Fahrlehrer fahrlehrer);
	
	public void updateFahrlehrer(Fahrlehrer fahrlehrer);
	
	public void deleteFahrlehrer(Fahrlehrer fahrlehrer);
	
	public Fahrlehrer getFahrlehrer(String fahrlehrerName);
}
