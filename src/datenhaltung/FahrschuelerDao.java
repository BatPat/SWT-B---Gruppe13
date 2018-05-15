package datenhaltung;
import java.util.List;

import fachlogik.Fahrschueler;

public interface FahrschuelerDao {
	
	public List<Fahrschueler> getAlleFahrschueler();
	
	public void addFahrschueler(Fahrschueler fahrschueler);
	
	public void updateFahrschueler(Fahrschueler fahrschueler);
	
	public void deleteFahrschueler(Fahrschueler fahrschueler);
	
	public Fahrschueler getFahrschueler(String fahrschuelerName);
}
