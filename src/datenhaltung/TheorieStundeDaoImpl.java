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

import fachlogik.Theoriestunde;

public class TheorieStundeDaoImpl implements TheoriestundeDao {

	@Override
	public List<Theoriestunde> getAlleTheoriestunden() {
		String home = System.getProperty("user.home");
		File dir = new File(home + "/Downloads/Fahrschule/Theoriestunde/");
		File[] theoriestundedateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.startsWith("Theorie") && arg1.endsWith(".ser");
			}

		});
		List<Theoriestunde> liste = new ArrayList<Theoriestunde>();
		Theoriestunde theoriestunde = null;
		for (int i = 0; i < theoriestundedateien.length; i++) {
			File file = theoriestundedateien[i];
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				theoriestunde = (Theoriestunde) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			liste.add(theoriestunde);
		}
		return liste;
	}

	private File generateFile(Theoriestunde theoriestunde) {
		String home = System.getProperty("user.home");
		File dir = new File(
				home + "/Downloads/Fahrschule/Theoriestunde/" + "Theorie" + theoriestunde.getGenid() + ".ser");
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
