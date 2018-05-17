package datenhaltung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import fachlogik.Fahrstunde;;

public class FahrstundeDaoImpl implements FahrstundeDao {

	@Override
	public List<Fahrstunde> getAlleFahrstunden() {
		String home = System.getProperty("user.home");
		File dir = new File(home + "/Downloads/Fahrschule/Fahrstunden/");
		File[] fahrstundendateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.startsWith("Fahrstunde") && arg1.endsWith(".ser");
			}

		});
		List<Fahrstunde> liste = new ArrayList<Fahrstunde>();
		Fahrstunde fahrstunde = null;
		for (int i = 0; i < fahrstundendateien.length; i++) {
			File file = fahrstundendateien[i];
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				fahrstunde = (Fahrstunde) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			liste.add(fahrstunde);
		}
		return liste;
	}

	private File generateFile(Fahrstunde fahrstunde) {
		String home = System.getProperty("user.home");
		File dir = new File(
				home + "/Downloads/Fahrschule/Fahrstunden/" + "Fahrstunde" + fahrstunde.getGenid() + ".ser");
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
