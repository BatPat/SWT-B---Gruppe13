package datenhaltung;
import java.util.List;

import fachlogik.FahrschuelerDTO;
/**
 * Interface, welches die verfügbaren Methoden für die Datenbankzugriffe festlegt.
 *
 */
public interface FahrschuelerDao {
	
	public List<FahrschuelerDTO> getAlleFahrschueler();
	
	public void addFahrschueler(FahrschuelerDTO fahrschueler);
	
	public void updateFahrschueler(FahrschuelerDTO fahrschueler);
	
	public void deleteFahrschueler(FahrschuelerDTO fahrschueler);
	
	public FahrschuelerDTO getFahrschueler(int fahrschuelerid);
}
