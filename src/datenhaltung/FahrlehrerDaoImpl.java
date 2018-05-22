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

import fachlogik.Fahrlehrer;

public class FahrlehrerDaoImpl implements FahrlehrerDao {
	
	private static final String FAHRLEHRER_PATH = "/Fahrschule/Fahrlehrer/";

	private static final String JAVADIR = System.getProperty("user.dir");


	@Override
	public List<Fahrlehrer> getAlleFahrlehrer() {
		File dir = new File(JAVADIR + FAHRLEHRER_PATH);
		File[] fahrlehrerdateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.endsWith(".ser");
			}

		});
		List<Fahrlehrer> liste = new ArrayList<>();
		Fahrlehrer fahrlehrer = null;
		for (int i = 0; i < fahrlehrerdateien.length; i++) {
			File file = fahrlehrerdateien[i];
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				fahrlehrer = (Fahrlehrer) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			liste.add(fahrlehrer);
		}
		return liste;
	}

	private File generateFile(Fahrlehrer fahrlehrer) {
		File dir = new File(JAVADIR + FAHRLEHRER_PATH + fahrlehrer.getName() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public void addFahrlehrer(Fahrlehrer fahrlehrer) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(fahrlehrer));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(fahrlehrer);			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updateFahrlehrer(Fahrlehrer fahrlehrer) {
		File td = generateFile(fahrlehrer);
		td.delete();
		addFahrlehrer(fahrlehrer);
	}

	@Override
	public void deleteFahrlehrer(Fahrlehrer fahrlehrer) {
		File td = generateFile(fahrlehrer);
		td.delete();
	}

	@Override
	public Fahrlehrer getFahrlehrer(String fahrlehrerName) {
		Fahrlehrer fahrlehrer = null;
		try (FileInputStream fis = new FileInputStream(
				JAVADIR + FAHRLEHRER_PATH + fahrlehrerName + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			fahrlehrer = (Fahrlehrer) ois.readObject();
			assert (fahrlehrer.getName().equals(fahrlehrerName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fahrlehrer;
	}

}
