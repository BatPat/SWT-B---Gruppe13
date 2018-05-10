package Datenhaltung;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import Fachlogik.Pruefung;

public class PruefungDaoImpl implements PruefungDao {

	@Override
	public List<Pruefung> getAllePruefungen() {
		return null;
	}

	private File generateFile(Pruefung pruefung) {
		String home = System.getProperty("user.home");
		File dir = new File(home + "/Downloads/Fahrschule/Pruefung"+"Pruefung" + pruefung.getGenid() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public void addPruefung(Pruefung pruefung) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(pruefung));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			try {
				oos.writeObject(pruefung);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updatePruefung(Pruefung pruefung) {
		File td = generateFile(pruefung);
		td.delete();
		try (FileOutputStream fos = new FileOutputStream(generateFile(pruefung));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			try {
				oos.writeObject(pruefung);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void deletePruefung(Pruefung pruefung) {
		File td = generateFile(pruefung);
		td.delete();
	}

}
