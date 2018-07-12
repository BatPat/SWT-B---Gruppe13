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
import fachlogik.MyLoggerUtil;

public class FahrlehrerDaoImpl implements FahrlehrerDao {
	
	private static final String FAHRLEHRER_PATH = "/Fahrschule/Fahrlehrer/";
	private static FahrlehrerDaoImpl instance;
	private static final String JAVADIR = System.getProperty("user.dir");
	private static Logger log = MyLoggerUtil.createLogger();
	
	public static FahrlehrerDaoImpl getInstance() {
		if (instance == null) {
			instance = new FahrlehrerDaoImpl();
		}
		log.fine(" Singleton-Instanz von Fahrlehrer wurde erzeugt. ");
		return instance;
	}

	@Override
	public List<FahrlehrerDTO> getAlleFahrlehrer() {
		File dir = new File(JAVADIR + FAHRLEHRER_PATH);
		File[] fahrlehrerdateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.endsWith(".ser");
			}

		});
		List<FahrlehrerDTO> liste = new ArrayList<>();
		FahrlehrerDTO fahrlehrer = null;
		for (int i = 0; i < fahrlehrerdateien.length; i++) {
			File file = fahrlehrerdateien[i];
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				fahrlehrer = (FahrlehrerDTO) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			liste.add(fahrlehrer);
		}
		return liste;
	}

	private File generateFile(FahrlehrerDTO fahrlehrer) {
		File dir = new File(JAVADIR + FAHRLEHRER_PATH + fahrlehrer.getName() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public FahrlehrerDTO getFahrlehrer(int fahrlehrerid) {
		FahrlehrerDTO fahrlehrer = null;
		try (FileInputStream fis = new FileInputStream(
				JAVADIR + FAHRLEHRER_PATH + fahrlehrerid + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			fahrlehrer = (FahrlehrerDTO) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fahrlehrer;
	}

	@Override
	public void addFahrlehrer(FahrlehrerDTO fahrlehrer) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(fahrlehrer));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(fahrlehrer);			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updateFahrlehrer(FahrlehrerDTO fahrlehrer) {
		File td = generateFile(fahrlehrer);
		td.delete();
		addFahrlehrer(fahrlehrer);		
	}

	@Override
	public void deleteFahrlehrer(FahrlehrerDTO fahrlehrer) {
		File td = generateFile(fahrlehrer);
		td.delete();		
	}
}
