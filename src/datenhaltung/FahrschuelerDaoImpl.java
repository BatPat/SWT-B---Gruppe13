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

import fachlogik.Fahrschueler;

public class FahrschuelerDaoImpl implements FahrschuelerDao {
	
	private static String javadir = System.getProperty("user.dir");

	private File generateFile(Fahrschueler fahrschueler) {
		File dir = new File(javadir + "/Fahrschule/Fahrschueler/" + fahrschueler.getName() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public List<Fahrschueler> getAlleFahrschueler() {
		File dir = new File(javadir + "/Fahrschule/Fahrschueler/");
		File[] fahrschuelerdateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.endsWith(".ser");
			}

		});
		List<Fahrschueler> liste = new ArrayList<Fahrschueler>();
		Fahrschueler fahrschueler = null;
		for (int i = 0; i < fahrschuelerdateien.length; i++) {
			File file = fahrschuelerdateien[i];
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				fahrschueler = (Fahrschueler) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			liste.add(fahrschueler);
		}
		return liste;
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

	@Override
	public Fahrschueler getFahrschueler(String fahrschuelerName) {
		Fahrschueler fahrschueler = null;
		try (FileInputStream fis = new FileInputStream(
				javadir + "/Fahrschule/Fahrschueler/" + fahrschuelerName + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			fahrschueler = (Fahrschueler) ois.readObject();
			assert (fahrschueler.getName().equals(fahrschuelerName));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return fahrschueler;
	}

}
