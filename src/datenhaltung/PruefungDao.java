package datenhaltung;
import java.util.List;

import fachlogik.PruefungDTO;

public interface PruefungDao {
	
	public List<PruefungDTO> getAllePruefungen();
	
	public void addPruefung(PruefungDTO pruefung);
	
	public void updatePruefung(PruefungDTO pruefung);
	
	public void deletePruefung(PruefungDTO pruefung);
	
	public PruefungDTO getPruefung(int pruefungid);
}
