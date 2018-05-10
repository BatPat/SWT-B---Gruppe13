package Datenhaltung;
import java.util.List;
import Fachlogik.Fahrstunde;;

public interface FahrstundeDao {
	
	public List<Fahrstunde> getAlleFahrstunden();
	
	public void addFahrstunde(Fahrstunde fahrstunde);
	
	public void updateFahrstunde(Fahrstunde fahrstunde);
	
	public void deleteFahrstunde(Fahrstunde fahrstunde);
}
