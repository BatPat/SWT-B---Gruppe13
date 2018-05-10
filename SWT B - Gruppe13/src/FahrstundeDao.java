import java.util.List;

public interface FahrstundeDao {
	
	public List<Fahrstunde> getAlleFahrstunden();
	
	public void addFahrstunde(Fahrstunde fahrstunde);
	
	public void updateFahrstunde(Fahrstunde fahrstunde);
	
	public void deleteFahrstunde(Fahrstunde fahrstunde);
}
