package Datenhaltung;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import Fachlogik.Fahrschueler;

public class FahrschuelerDaoImpl implements FahrschuelerDao {

	private File generateFile(Fahrschueler fahrschueler) {
		String home = System.getProperty("user.home");
		File dir = new File(home + "/Downloads/Fahrschule/Fahrschueler" + fahrschueler.getName() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}
	
	@Override
	public List<Fahrschueler> getAlleFahrschueler() {
		return null;
	}

	@Override
	public void addFahrschueler(Fahrschueler fahrschueler) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(fahrschueler));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			try {
				oos.writeObject(fahrschueler);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updateFahrschueler(Fahrschueler fahrschueler) {
		File td = generateFile(fahrschueler);
		td.delete();
		try (FileOutputStream fos = new FileOutputStream(generateFile(fahrschueler));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			try {
				oos.writeObject(fahrschueler);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void deleteFahrschueler(Fahrschueler fahrschueler) {
		File td = generateFile(fahrschueler);
		td.delete();
	}

}
