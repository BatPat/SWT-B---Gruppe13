package datenhaltung;
import java.util.List;

import fachlogik.FahrlehrerDTO;
/**
 * Interface, welches die verfügbaren Methoden für die Datenbankzugriffe festlegt.
 *
 */
public interface FahrlehrerDao {
	
	public List<FahrlehrerDTO> getAlleFahrlehrer();
	
	public void addFahrlehrer(FahrlehrerDTO fahrlehrer);
	
	public void updateFahrlehrer(FahrlehrerDTO fahrlehrer);
	
	public void deleteFahrlehrer(FahrlehrerDTO fahrlehrer);
	
	public FahrlehrerDTO getFahrlehrer(int fahrlehrerid);
}
