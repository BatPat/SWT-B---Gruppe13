package Fachlogik;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observer;

import Datenhaltung.FahrlehrerDaoImpl;
import Datenhaltung.FahrschuelerDaoImpl;
import Oberflaeche.MainView;

public class Controller {

	private FahrschulModel model;
	private MainView mainview;
	private PdfDocumentBill pdf;
	private FahrlehrerDaoImpl fahrlehrerdao;
	private FahrschuelerDaoImpl fahrschuelerdao;

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

	private void fillListContent() {
		for (Fahrlehrer f : fahrlehrerdao.getAlleFahrlehrer()) {
			mainview.getLehrerCombo().add(f.getName());
		}
		for (Fahrlehrer f : fahrlehrerdao.getAlleFahrlehrer()) {
			mainview.getSchuelerCombo().add(f.getName());
		}
		for (int i = 9; i < 22; i++) {
			String zeit = i + ":00"; 
			mainview.getZeitCombo().add(zeit);
		}
	}

	private void uebersichtFahrstunden() throws FileNotFoundException, IOException {
		String f = mainview.getSchuelerCombo().getText();
		String home = System.getProperty("user.home");
		Fahrschueler fahrschueler = null;
		try (FileInputStream fis = new FileInputStream(home + "/Downloads/Fahrschule/Fahrschueler/" + f + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			fahrschueler = (Fahrschueler) ois.readObject();
			assert (fahrschueler.getName().equals(f));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		int anzNorm = 0;
		int anzSond = 0;
		int anzTheo = 0;
		for (Fahrstunde fahrstunde : fahrschueler.getFahrstunden()) {
			if (fahrstunde.getArt() == Fahrstundenart.B_STANDARDFAHRT) {
				anzNorm++;
			} else if (fahrstunde.getArt() != Fahrstundenart.B_STANDARDFAHRT) {
				anzSond++;
			}
		}
		for (Pruefung p : fahrschueler.getPruefungen()) {
			if (p.isTheoriepruefung()) {
				anzTheo++;
			}
		}
		String anzSo = anzSond + "";
		String anzNo = anzNorm + "";
		String anzTh = anzTheo + "";
		mainview.getTheorieTxt().setText(anzTh);
		mainview.getSonderfahrtTxt().setText(anzSo);
		mainview.getFahrstundeTxt().setText(anzNo);
	}

	private void updateModel() {
		
	}

	private void erstellePdf() throws FileNotFoundException, IOException {
		String f = mainview.getSchuelerCombo().getText();
		String home = System.getProperty("user.home");
		Fahrschueler fahrschueler = null;
		try (FileInputStream fis = new FileInputStream(home + "/Downloads/Fahrschule/Fahrschueler/" + f + ".ser");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			fahrschueler = (Fahrschueler) ois.readObject();
			assert (fahrschueler.getName().equals(f));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		pdf.createPdf(fahrschueler);
	}

}
