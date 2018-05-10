import java.util.List;

public interface FahrschuelerDao {
	
	public List<Fahrschueler> getAlleFahrschueler();
	
	public void addFahrschueler(Fahrschueler fahrschueler);
	
	public void updateFahrschueler(Fahrschueler fahrschueler);
	
	public void deleteFahrschueler(Fahrschueler fahrschueler);
	
}
