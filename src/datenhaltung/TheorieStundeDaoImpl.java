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

import fachlogik.Theoriestunde;

public class TheorieStundeDaoImpl implements TheoriestundeDao {
	
	private static String javadir = System.getProperty("user.dir");

	private static TheorieStundeDaoImpl instance;

	private TheorieStundeDaoImpl() {
		
	}
	
	public static TheorieStundeDaoImpl getInstance() {
		if(instance == null) {
			instance = new TheorieStundeDaoImpl();
		}
		return instance;
	}
	

	@Override
	public List<Theoriestunde> getAlleTheoriestunden() {
		File dir = new File(javadir + "/Fahrschule/Theoriestunde/");
		File[] theoriestundedateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.startsWith("Theorie") && arg1.endsWith(".ser");
			}

		});
		List<Theoriestunde> liste = new ArrayList<>();
		Theoriestunde theoriestunde = null;
		for (int i = 0; i < theoriestundedateien.length; i++) {
			File file = theoriestundedateien[i];
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				theoriestunde = (Theoriestunde) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			liste.add(theoriestunde);
		}
		return liste;
	}

	private File generateFile(Theoriestunde theoriestunde) {
		File dir = new File(
				javadir + "/Fahrschule/Theoriestunde/" + "Theorie" + theoriestunde.getGenid() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public void addTheoriestunde(Theoriestunde theoriestunde) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(theoriestunde));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(theoriestunde);
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
				oos.writeObject(theoriestunde);
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
