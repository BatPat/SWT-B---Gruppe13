package datenhaltung;
import java.util.List;

import fachlogik.FahrstundeDTO;

public interface FahrstundeDao {
	
	public List<FahrstundeDTO> getAlleFahrstunden();
	
	public void addFahrstunde(FahrstundeDTO fahrstunde);
	
	public void updateFahrstunde(FahrstundeDTO fahrstunde);
	
	public void deleteFahrstunde(FahrstundeDTO fahrstunde);
}
