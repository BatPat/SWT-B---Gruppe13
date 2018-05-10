package Datenhaltung;
import java.util.List;
import Fachlogik.Fahrschueler;

public interface FahrschuelerDao {
	
	public List<Fahrschueler> getAlleFahrschueler();
	
	public void addFahrschueler(Fahrschueler fahrschueler);
	
	public void updateFahrschueler(Fahrschueler fahrschueler);
	
	public void deleteFahrschueler(Fahrschueler fahrschueler);
	
}
