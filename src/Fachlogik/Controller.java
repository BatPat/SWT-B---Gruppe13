package Fachlogik;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observer;

import Datenhaltung.FahrlehrerDaoImpl;
import Oberflaeche.MainView;

public class Controller {

	
	private FahrschulModel model;
	private MainView mainview;
	private PdfDocumentBill pdf;
	private FahrlehrerDaoImpl fahrlehrerdao;
	
	public Controller() {
		initGUI();
		initModel();
		mainview.getShell().layout(true);
	}

	private void initGUI() {
		 MainView gui = new MainView();
		 gui.addObserver((Observer) this);
	}

	private void initModel() {
		model = new FahrschulModel();
	}

	private void fillListContent()
	  {
		String[] fahrlehrernamen = null;
		for (Fahrlehrer f : fahrlehrerdao.getAlleFahrlehrer()) {
			int count = 0;
			fahrlehrernamen[count] = f.getName();
		}
		String[] fahrschuelernamen = null;
		for (Fahrlehrer f : fahrlehrerdao.getAlleFahrlehrer()) {
			int count = 0;
			fahrschuelernamen[count] = f.getName();
		}
		mainview.getLehrerCombo().setItems(fahrlehrernamen);
		mainview.getSchuelerCombo().setItems(fahrschuelernamen);
	  }
	
	 private void updateModel()
	  {
		 //rechte seite fahrstunden anzeigen
	  }
	 
	 private void erstellePdf() throws FileNotFoundException, IOException {
		 String f = mainview.getSchuelerCombo().getText();
		 String home = System.getProperty("user.home");
		 Fahrschueler fahrschueler = null; 
		 try (FileInputStream fis = new FileInputStream (home + "/Downloads/Fahrschule/Fahrschueler/" + f + ".ser");
				    ObjectInputStream ois = new ObjectInputStream (fis)) {
				  fahrschueler = (Fahrschueler) ois.readObject ();
				  assert (fahrschueler.getName().equals(f));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		 pdf.createPdf(fahrschueler);
	 }

}
