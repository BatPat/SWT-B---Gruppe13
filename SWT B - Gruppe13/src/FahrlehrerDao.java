import java.util.List;

public interface FahrlehrerDao {
	
	public List<Fahrlehrer> getAlleFahrlehrer();
	
	public void addFahrlehrer(Fahrlehrer fahrlehrer);
	
	public void updateFahrlehrer(Fahrlehrer fahrlehrer);
	
	public void deleteFahrlehrer(Fahrlehrer fahrlehrer);
}
