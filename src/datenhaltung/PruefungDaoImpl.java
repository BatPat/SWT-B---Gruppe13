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
import fachlogik.PruefungDTO;
public class PruefungDaoImpl implements PruefungDao {

	private static PruefungDaoImpl instance;
	private static Logger log = MyLoggerUtil.createLogger();

	private PruefungDaoImpl() {

	}

	//Singleton-Pattern das die Anzahl der PruefungDaoImpl auf eins begrenzt und so verhindert das die Datenbank mit vielen Anfragen blockiert wird.
	public static PruefungDaoImpl getInstance() {
		if (instance == null) {
			instance = new PruefungDaoImpl();
		}
		log.fine(" Singleton-Instanz von Pruefung wurde erzeugt. ");
		return instance;
	}

	private static String javadir = System.getProperty("user.dir");

	@Override
	public List<PruefungDTO> getAllePruefungen() {
		File dir = new File(javadir + "/Fahrschule/Pruefung/");
		File[] puefungdateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				return arg1.startsWith("Pruefung") && arg1.endsWith(".ser");
			}

		});
		List<PruefungDTO> liste = new ArrayList<>();
		PruefungDTO pruefung = null;
		for (int i = 0; i < puefungdateien.length; i++) {
			File file = puefungdateien[i];
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				pruefung = (PruefungDTO) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			liste.add(pruefung);
		}
		return liste;
	}

	private File generateFile(PruefungDTO pruefung) {
		File dir = new File(javadir + "/Fahrschule/Pruefung/" + "Pruefung" + pruefung.getGenid() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public void addPruefung(PruefungDTO pruefung) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(pruefung));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				oos.writeObject(pruefung);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updatePruefung(PruefungDTO pruefung) {
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
	public void deletePruefung(PruefungDTO pruefung) {
		File td = generateFile(pruefung);
		td.delete();
	}

	@Override
	public PruefungDTO getPruefung(int pruefungid) {
		return new PruefungDTO();
	}

}

