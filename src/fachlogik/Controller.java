package fachlogik;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import datenhaltung.FahrlehrerDao;
import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDao;
import datenhaltung.FahrschuelerDaoImpl;
import datenhaltung.FahrstundeDao;
import datenhaltung.FahrstundeDaoImpl;
import oberflaeche.MainView;

public class Controller implements Observer {

	private FahrschulModel model;
	private MainView mainview;
	private PdfDocumentBill pdf;
	private FahrlehrerDao fahrlehrerdao;
	private FahrschuelerDao fahrschuelerdao;
	private FahrstundeDao fahrstundedao;

	public Controller() {
		initDaos();
		pdf = new PdfDocumentBill();
		initGUI();
		initModel();
		mainview.getShell().layout(true);
	}

	private void initDaos() {
		fahrlehrerdao = new FahrlehrerDaoImpl();
		fahrschuelerdao = new FahrschuelerDaoImpl();
		fahrstundedao = new FahrstundeDaoImpl();
	}

	private void initGUI() {
		mainview = new MainView();
		mainview.addObserver(this);
		fillListContent();
		mainview.startEventHandler();
	}

	private void initModel() {
		model = new FahrschulModel();
	}

	private void fillListContent() {
		for (Fahrlehrer f : fahrlehrerdao.getAlleFahrlehrer()) {
			mainview.getLehrerCombo().add(f.getName());
		}
		for (Fahrschueler f : fahrschuelerdao.getAlleFahrschueler()) {
			mainview.getSchuelerCombo().add(f.getName());
		}
		for (int i = 9; i < 22; i++) {
			String zeit = i + ":00";
			mainview.getZeitCombo().add(zeit);
		}

		for (Fahrstundenart f : Fahrstundenart.values()) {
			mainview.getArtCombo().add(f.getBeschreibung());
		}
	}

	private void zeigePassendeTermine() throws FileNotFoundException, IOException {
		for (int i = 9; i < 22; i++) {
			String zeit = i + ":00";
			mainview.getTimeCombo().add(zeit);
		}
		String fahrlehr = mainview.getLehrerCombo().getText();
		Fahrlehrer fahrlehrer = fahrlehrerdao.getFahrlehrer(fahrlehr);
		ArrayList<Fahrstunde> terminefahrlehr;
		terminefahrlehr = fahrlehrer.getFahrstunden();
		int tag = mainview.getDateFahrstunde().getDay();
		int monat = mainview.getDateFahrstunde().getMonth();
		int jahr = mainview.getDateFahrstunde().getYear();

		// FixMe nimm Termin aus dem Kalender und gleiche erst dann mit dem
		// Fahrlehrertermin ab
		// TODO einbinden wird noch nicht aufgerufen -- Doch beim update über den observer ... funktioniert soweit also denke ich
		for (int i = 0; i < terminefahrlehr.size(); i++) {
			if (terminefahrlehr.get(i).getDatum().getDayOfMonth() == tag
					&& terminefahrlehr.get(i).getDatum().getMonthValue() == monat
					&& terminefahrlehr.get(i).getDatum().getYear() == jahr) {
				for (int j = 9; j < 22; i++) {
					if (terminefahrlehr.get(i).getUhrzeit().getHour() == j) {
						String zeit = j + ":00";
						mainview.getZeitCombo().remove(zeit);
					}
				}

			}
		}
	}

	private void uebersichtFahrstunden() throws FileNotFoundException, IOException {
		String f = mainview.getSchuelerCombo().getText();
		Fahrschueler fahrschueler = fahrschuelerdao.getFahrschueler(f);
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
		anzTheo = fahrschueler.getTheoriestunden().size();
		String anzSo = anzSond + "";
		String anzNo = anzNorm + "";
		String anzTh = anzTheo + "";
		mainview.getTheorieTxt().setText(anzTh);
		mainview.getSonderfahrtTxt().setText(anzSo);
		mainview.getFahrstundeTxt().setText(anzNo);
	}
	
	private boolean auswahlFeldersindleer() {
		//TODO wird noch nicht aufgerufen
		return (mainview.getLehrerCombo().getText().isEmpty() 
				&& mainview.getSchuelerCombo().getText().isEmpty() 
				&& mainview.getZeitCombo().getText().isEmpty());
	}

	private void updateModel() {

		// TODO model aktualisieren

	}

	private void erstellePdf() throws IOException {
		String f = mainview.getSchuelerCombo().getText();
		Fahrschueler fahrschueler = fahrschuelerdao.getFahrschueler(f);
		pdf.createPdf(fahrschueler);
	}

	private void bucheFahrstunde() {
		String fahrschuelername = mainview.getSchuelerCombo().getText();
		String fahlehrername = mainview.getLehrerCombo().getText();

		// TODO FixMe Uhrzeit und Datum werden aus den Elementen aus dem kalenderComposite
		// genommen
		String uhrzeitString = mainview.getZeitCombo().getText();
		int datumJahr = mainview.getDateFahrstunde().getYear();
		int datumMonat = mainview.getDateFahrstunde().getMonth();
		int datumTag = mainview.getDateFahrstunde().getDay();
		String artString = mainview.getArtCombo().getText();

		Fahrschueler fSchueler = fahrschuelerdao.getFahrschueler(fahrschuelername);
		Fahrlehrer fLehrer = fahrlehrerdao.getFahrlehrer(fahlehrername);

		LocalTime terminUhrzeit = LocalTime.of(Integer.parseInt(uhrzeitString.substring(0, 2)),
				Integer.parseInt(uhrzeitString.substring(3)));
		LocalDate terminDatum = LocalDate.of(datumJahr, datumMonat, datumTag);
		Fahrstundenart fStundenArt;
		if (artString == Fahrstundenart.B_STANDARDFAHRT.toString()) {
			fStundenArt = Fahrstundenart.B_STANDARDFAHRT;
		} else {
			fStundenArt = Fahrstundenart.B_SONDERFAHRT;
		}

		Fahrstunde fStunde = new Fahrstunde(fStundenArt, fLehrer, fSchueler, terminUhrzeit, terminDatum, "dummy");
		fahrstundedao.addFahrstunde(fStunde);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		updateModel();
		
		switch (arg1.toString()) {
		case "Fahrlehrer":
			try {
				zeigePassendeTermine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;

		case "Datum":
			try {
				zeigePassendeTermine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;

		case "Fahrschueler":
			try {
				uebersichtFahrstunden();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;

		case "Uhrzeit":
			// nix
			break;

		case "BuchungsZeit":
			// nix
			break;

		case "Führerscheinklasse":
			// nix
			break;

		case "FenstergroesseAendern":
			// nix
			break;
			
		case "Buchen":
			bucheFahrstunde();
			break;

		case "Rechnung":
			try {
				erstellePdf();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			try {
				throw new Exception("unerwartetes Event in der mainView");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

}
