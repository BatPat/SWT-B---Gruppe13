import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class FahrschuelerDaoImpl implements FahrschuelerDao {

	private File generateFile(Fahrschueler fahrschueler) {
		String home = System.getProperty("user.home");
		File dir = new File(home + "/Downloads/Fahrschule/Fahrschueler");
		dir.mkdir();
		return new File(dir.getAbsolutePath() + fahrschueler.getName() + ".ser");
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
