package datenhaltung;
import java.util.List;

import fachlogik.FahrstundeDTO;
/**
 * Interface, welches die verfügbaren Methoden für die Datenbankzugriffe festlegt.
 *
 */
public interface FahrstundeDao {
	
	public List<FahrstundeDTO> getAlleFahrstunden();
	
	public void addFahrstunde(FahrstundeDTO fahrstunde);
	
	public void updateFahrstunde(FahrstundeDTO fahrstunde);
	
	public void deleteFahrstunde(FahrstundeDTO fahrstunde);
	
	public FahrstundeDTO getFahrstunde(int fahrstundeid);
}
