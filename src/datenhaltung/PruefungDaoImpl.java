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

import fachlogik.Pruefung;

public class PruefungDaoImpl implements PruefungDao {
	
	private static String javadir = System.getProperty("user.dir");

	private static PruefungDaoImpl instance;

	private PruefungDaoImpl() {
		
	}
	
	public static PruefungDaoImpl getInstance() {
		if(instance == null) {
			instance = new PruefungDaoImpl();
		}
		return instance;
	}
	

	@Override
	public List<Pruefung> getAllePruefungen() {
		File dir = new File(javadir + "/Fahrschule/Pruefung/");
		File[] puefungdateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.startsWith("Pruefung") && arg1.endsWith(".ser");
			}

		});
		List<Pruefung> liste = new ArrayList<>();
		Pruefung pruefung = null;
		for (int i = 0; i < puefungdateien.length; i++) {
			File file = puefungdateien[i];
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				pruefung = (Pruefung) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			liste.add(pruefung);
		}
		return liste;
	}

	private File generateFile(Pruefung pruefung) {
		File dir = new File(javadir + "/Fahrschule/Pruefung/" + "Pruefung" + pruefung.getGenid() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public void addPruefung(Pruefung pruefung) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(pruefung));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(pruefung);
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
				oos.writeObject(pruefung);
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
