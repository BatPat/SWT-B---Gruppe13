package datenhaltung;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import fachlogik.Fahrstunde;;

public class FahrstundeDaoImpl implements FahrstundeDao {

	@Override
	public List<Fahrstunde> getAlleFahrstunden() {
		return null;
	}

	private File generateFile(Fahrstunde fahrstunde) {
		String home = System.getProperty("user.home");
		File dir = new File(home + "/Downloads/Fahrschule/Fahrstunden"+"Fahrstunde"+fahrstunde.getGenid()+".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}
	
	@Override
	public void addFahrstunde(Fahrstunde fahrstunde) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(fahrstunde));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			try {
				oos.writeObject(fahrstunde);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updateFahrstunde(Fahrstunde fahrstunde) {
		File td = generateFile(fahrstunde);
		td.delete();
		try (FileOutputStream fos = new FileOutputStream(generateFile(fahrstunde));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			try {
				oos.writeObject(fahrstunde);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void deleteFahrstunde(Fahrstunde fahrstunde) {
		File td = generateFile(fahrstunde);
		td.delete();
	}

}
