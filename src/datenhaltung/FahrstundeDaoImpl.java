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

import fachlogik.FahrlehrerDTO;
import fachlogik.FahrstundeDTO;
import fachlogik.MyLoggerUtil;
public class FahrstundeDaoImpl implements FahrstundeDao {

	private static FahrstundeDaoImpl instance;
	private static Logger log = MyLoggerUtil.createLogger();
	private static final String FAHRSTUNDEN_PATH = "/Fahrschule/Fahrstunden/";
	private static final String JAVADIR = System.getProperty("user.dir");
	
	private FahrstundeDaoImpl() {

	}

	//Singleton-Pattern das die Anzahl der FahrstundeDaoImpl auf eins begrenzt und so verhindert das die Datenbank mit vielen Anfragen blockiert wird.
	public static FahrstundeDaoImpl getInstance() {
		if (instance == null) {
			instance = new FahrstundeDaoImpl();
		}
		log.fine(" Singleton-Instanz von Fahrstunde wurde erzeugt. ");
		return instance;
	}



	@Override
	public List<FahrstundeDTO> getAlleFahrstunden() {
		File dir = new File(JAVADIR + FAHRSTUNDEN_PATH);
		File[] fahrstundendateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.startsWith("Fahrstunde") && arg1.endsWith(".ser");
			}

		});
		List<FahrstundeDTO> liste = new ArrayList<>();
		FahrstundeDTO fahrstunde = null;
		for (int i = 0; i < fahrstundendateien.length; i++) {
			File file = fahrstundendateien[i];
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				fahrstunde = (FahrstundeDTO) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			liste.add(fahrstunde);
		}
		return liste;
	}

	private File generateFile(FahrstundeDTO fahrstunde) {
		File dir = new File(
				JAVADIR + FAHRSTUNDEN_PATH + "Fahrstunde" + fahrstunde.getGenid() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public void addFahrstunde(FahrstundeDTO fahrstunde) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(fahrstunde));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(fahrstunde);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updateFahrstunde(FahrstundeDTO fahrstunde) {
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
	public void deleteFahrstunde(FahrstundeDTO fahrstunde) {
		File td = generateFile(fahrstunde);
		td.delete();
	}

	@Override
	public FahrstundeDTO getFahrstunde(int fahrstundeid) {
		FahrstundeDTO fahrstunde = null;
		try (FileInputStream fis = new FileInputStream(
				JAVADIR + FAHRSTUNDEN_PATH + fahrstundeid + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			fahrstunde = (FahrstundeDTO) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fahrstunde;
	}

}
