package Datenhaltung;
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
import Fachlogik.Fahrlehrer;

public class FahrlehrerDaoImpl implements FahrlehrerDao{

	@Override
	public List<Fahrlehrer> getAlleFahrlehrer() {
		String home = System.getProperty("user.home");
		File dir = new File(home + "/Downloads/Fahrschule/Fahrlehrer/");
		File[] fahrlehrerdateien = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File arg0, String arg1) {
				// TODO Auto-generated method stub
				return arg1.endsWith(".ser");
			}
			
		});
		List<Fahrlehrer> liste = new ArrayList<Fahrlehrer>();
		Fahrlehrer fahrlehrer = null;
		for (int i = 0; i < fahrlehrerdateien.length; i++) {
			File file = fahrlehrerdateien[i]; 
			 try (FileInputStream fis = new FileInputStream (file);
					    ObjectInputStream ois = new ObjectInputStream (fis)) {
				 fahrlehrer = (Fahrlehrer) ois.readObject ();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			liste.add(fahrlehrer); 
		}
		return liste;
	}

	private File generateFile(Fahrlehrer fahrlehrer) {
		String home = System.getProperty("user.home");
		File dir = new File(home + "/Downloads/Fahrschule/Fahrlehrer/"+ fahrlehrer.getName() + ".ser");
		dir.getParentFile().mkdirs();
		return dir;
	}

	@Override
	public void addFahrlehrer(Fahrlehrer fahrlehrer) {
		try (FileOutputStream fos = new FileOutputStream(generateFile(fahrlehrer));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			try {
				oos.writeObject(fahrlehrer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updateFahrlehrer(Fahrlehrer fahrlehrer) {
		File td = generateFile(fahrlehrer);
		td.delete();
		try (FileOutputStream fos = new FileOutputStream(generateFile(fahrlehrer));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			try {
				oos.writeObject(fahrlehrer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void deleteFahrlehrer(Fahrlehrer fahrlehrer) {
		File td = generateFile(fahrlehrer);
		td.delete();
	}

	@Override
	public Fahrlehrer getFahrlehrer(String fahrlehrerName) {
		String home = System.getProperty("user.home");
		Fahrlehrer fahrlehrer = null; 
		 try (FileInputStream fis = new FileInputStream (home + "/Downloads/Fahrschule/Fahrlehrer/" + fahrlehrerName + ".ser");
				    ObjectInputStream ois = new ObjectInputStream (fis)) {
			 fahrlehrer = (Fahrlehrer) ois.readObject ();
				  assert (fahrlehrer.getName().equals(fahrlehrerName));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return fahrlehrer;
	}

}
