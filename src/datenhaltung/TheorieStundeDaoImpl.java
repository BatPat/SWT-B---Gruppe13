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
import java.util.logging.Logger;

import fachlogik.MyLoggerUtil;
import fachlogik.TheoriestundeDTO;
public class TheorieStundeDaoImpl implements TheoriestundeDao {

	private static TheorieStundeDaoImpl instance;
	private static Logger log = MyLoggerUtil.createLogger();

	private TheorieStundeDaoImpl() {

	}

	//Singleton-Pattern das die Anzahl der TheoriestundeDaoImpl auf eins begrenzt und so verhindert das die Datenbank mit vielen Anfragen blockiert wird.
	public static TheorieStundeDaoImpl getInstance() {
		if (instance == null) {
			instance = new TheorieStundeDaoImpl();
		}
		log.fine(" Singleton-Instanz von Theoriestunde wurde erzeugt. ");
		return instance;
	}

	private static String javadir = System.getProperty("user.dir");

	@Override
	public List<TheoriestundeDTO> getAlleTheoriestunden() {
		File dir = new File(javadir + "/Fahrschule/Theoriestunde/");
		File[] theoriestundedateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.startsWith("Theorie") && arg1.endsWith(".ser");
			}

		});
		List<TheoriestundeDTO> liste = new ArrayList<>();
		TheoriestundeDTO theoriestunde = null;
		for (int i = 0; i < theoriestundedateien.length; i++) {
			File file = theoriestundedateien[i];
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				theoriestunde = (TheoriestundeDTO) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			liste.add(theoriestunde);
		}
		return liste;
	}

	private File generateFile(TheoriestundeDTO theoriestunde) {
		File dir = new File(
				javadir + "/Fahrschule/Theoriestunde/" + "Theorie" + theoriestunde.getGenid() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public void addTheoriestunde(TheoriestundeDTO theoriestunde) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(theoriestunde));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(theoriestunde);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updateTheoriestunde(TheoriestundeDTO theoriestunde) {
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
	public void deleteTheoriestunde(TheoriestundeDTO theoriestunde) {
		File td = generateFile(theoriestunde);
		td.delete();
	}

	@Override
	public TheoriestundeDTO getTheoriestunde(int theoriestundeid) {
		return new TheoriestundeDTO();
	}
}

