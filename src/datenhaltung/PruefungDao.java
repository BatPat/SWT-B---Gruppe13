package datenhaltung;
import java.util.List;

import fachlogik.Pruefung;

public interface PruefungDao {
	
	public List<Pruefung> getAllePruefungen();
	
	public void addPruefung(Pruefung pruefung);
	
	public void updatePruefung(Pruefung pruefung);
	
	public void deletePruefung(Pruefung pruefung);
}
