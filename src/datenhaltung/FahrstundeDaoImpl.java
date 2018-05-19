package datenhaltung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import fachlogik.Fahrstunde;;

public class FahrstundeDaoImpl implements FahrstundeDao {
	
	private static final String FAHRSTUNDEN_PATH = "/Fahrschule/Fahrstunden/";
	private static final String JAVADIR = System.getProperty("user.dir");

	@Override
	public List<Fahrstunde> getAlleFahrstunden() {
		File dir = new File(JAVADIR + FAHRSTUNDEN_PATH);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
			liste.add(fahrstunde);
		}
		return liste;
	}

	private File generateFile(Fahrstunde fahrstunde) {
		File dir = new File(
				JAVADIR + FAHRSTUNDEN_PATH + "Fahrstunde" + fahrstunde.getGenid() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public void addFahrstunde(Fahrstunde fahrstunde) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(fahrstunde));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(fahrstunde);
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
				oos.writeObject(fahrstunde);
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
