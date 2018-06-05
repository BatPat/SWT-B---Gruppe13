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

import fachlogik.FahrlehrerDTO;
import fachlogik.HibernateUtil;

public class FahrlehrerDaoImpl implements FahrlehrerDao {
	
	private static final String FAHRLEHRER_PATH = "/Fahrschule/Fahrlehrer/";
	private static final String JAVADIR = System.getProperty("user.dir");
	private static FahrlehrerDaoImpl instance;
	private static HibernateUtil h;

	private FahrlehrerDaoImpl() {
		
	}
	
	public static FahrlehrerDaoImpl getInstance() {
		if(instance == null) {
			instance = new FahrlehrerDaoImpl();
		}
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
	public void addFahrlehrer(FahrlehrerDTO fahrlehrer) {
		h = new HibernateUtil();
		h.saveObject(fahrlehrer);
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

	//TODO FahrlehrerDTO fahrlehrer als übergabe bei get Fahrlehrer
	@Override
	public FahrlehrerDTO getFahrlehrer(String fahrlehrerName) {
		h = new HibernateUtil();
		return null; //TODO h.getObject(fahrlehrer.getID);
	}

}
