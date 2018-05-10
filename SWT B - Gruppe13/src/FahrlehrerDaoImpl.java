import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class FahrlehrerDaoImpl implements FahrlehrerDao, Serializable {

	@Override
	public List<Fahrlehrer> getAlleFahrlehrer() {
		return null;
	}

	private File generateFile(Fahrlehrer fahrlehrer) {
		String home = System.getProperty("user.home");
		File dir = new File(home + "/Downloads/Fahrschule/Fahrlehrer"+ fahrlehrer.getName() + ".ser");
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

}
