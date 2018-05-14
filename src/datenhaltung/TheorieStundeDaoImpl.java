package datenhaltung;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import fachlogik.Theoriestunde;

public class TheorieStundeDaoImpl implements TheoriestundeDao {

	@Override
	public List<Theoriestunde> getAlleTheoriestunden() {
		return null;
	}
	
	private File generateFile(Theoriestunde theoriestunde) {
		String home = System.getProperty("user.home");
		File dir = new File(home + "/Downloads/Fahrschule/Theoriestunde"+"Theorie" + theoriestunde.getGenid() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public void addTheoriestunde(Theoriestunde theoriestunde) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(theoriestunde));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			try {
				oos.writeObject(theoriestunde);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updateTheoriestunde(Theoriestunde theoriestunde) {
		File td = generateFile(theoriestunde);
		td.delete();
		try (FileOutputStream fos = new FileOutputStream(generateFile(theoriestunde));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			try {
				oos.writeObject(theoriestunde);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void deleteTheoriestunde(Theoriestunde theoriestunde) {
		File td = generateFile(theoriestunde);
		td.delete();
	}

}
