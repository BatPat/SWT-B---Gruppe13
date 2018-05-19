package fachlogik;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
		initModel();
		initGUI();
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

	private void zeigePassendeTermine(){
		mainview.getTimeCombo().removeAll();
		
		if (!auswahlFeldersindleer() && mainview.getTimeCombo().getText().isEmpty()) {
			for (int i = 9; i < 22; i++) {
				String zeit = i + ":00";
				mainview.getTimeCombo().add(zeit);
			}
		}

		Fahrlehrer fahrlehrer = model.getFahrlehrer();

		List<Fahrstunde> terminefahrlehr;
		terminefahrlehr = fahrlehrer.getFahrstunden();
		int tag = mainview.getDateFahrstunde().getDay();
		int monat = mainview.getDateFahrstunde().getMonth();
		int jahr = mainview.getDateFahrstunde().getYear();

		for (int i = 0; i < terminefahrlehr.size(); i++) {
			if (terminefahrlehr.get(i).getDatum().getDayOfMonth() == tag
					&& terminefahrlehr.get(i).getDatum().getMonthValue() == monat
					&& terminefahrlehr.get(i).getDatum().getYear() == jahr) {
				for (int j = 9; j < 22; j++) {
					if (terminefahrlehr.get(i).getUhrzeit().getHour() == j) {
						String zeit = j + ":00";
						mainview.getTimeCombo().remove(zeit);
					}
				}

			}
		}
	}

	private void uebersichtFahrstunden(){

		Fahrschueler fahrschueler = model.getFahrschueler();
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
		return (mainview.getLehrerCombo().getText().isEmpty() && mainview.getSchuelerCombo().getText().isEmpty()
				&& mainview.getZeitCombo().getText().isEmpty());
	}

	private void updateModel() {

		String fahrschuelername = mainview.getSchuelerCombo().getText();
		String fahlehrername = mainview.getLehrerCombo().getText();
		String artString = mainview.getArtCombo().getText();
		String uhrzeitString = mainview.getTimeCombo().getText();
		int datumJahr = mainview.getDateFahrstunde().getYear();
		int datumMonat = mainview.getDateFahrstunde().getMonth();
		int datumTag = mainview.getDateFahrstunde().getDay();

		Fahrschueler fSchueler = null;
		Fahrlehrer fLehrer = null;
		LocalTime terminUhrzeit = null;
		Fahrstundenart fStundenArt = null;
		LocalDate terminDatum = null;

		if (!fahrschuelername.isEmpty()) {
			fSchueler = fahrschuelerdao.getFahrschueler(fahrschuelername);
			model.setFahrschueler(fSchueler);
		}

		if (!fahlehrername.isEmpty()) {
			fLehrer = fahrlehrerdao.getFahrlehrer(fahlehrername);
			model.setFahrlehrer(fLehrer);
		}

		if (!uhrzeitString.isEmpty()) {
			terminUhrzeit = LocalTime.of(Integer.parseInt(uhrzeitString.substring(0, 2)),
					Integer.parseInt(uhrzeitString.substring(3)));
			model.setUhrzeit(terminUhrzeit);
		}

		terminDatum = LocalDate.of(datumJahr, datumMonat, datumTag);
		model.setDatum(terminDatum);

		if (!artString.isEmpty()) {
			if (artString.trim().compareTo(Fahrstundenart.B_STANDARDFAHRT.getBeschreibung()) == 0) {
				fStundenArt = Fahrstundenart.B_STANDARDFAHRT;
			} else {
				fStundenArt = Fahrstundenart.B_SONDERFAHRT;
			}
			model.setArt(fStundenArt);
		}

		
	}

	private void erstellePdf() throws IOException {
		if (model.getFahrschueler().getName() != null) {
			pdf.createPdf(model.getFahrschueler());
		}
	}

	private void bucheFahrstunde() {
		Fahrstunde fStunde = model.getFahrstunde();
		Fahrschueler fSchueler = model.getFahrschueler();
		Fahrlehrer fLehrer = model.getFahrlehrer();

		fLehrer.getFahrstunden().add(fStunde);
		fSchueler.getFahrstunden().add(fStunde);
		fahrstundedao.addFahrstunde(fStunde);
		fahrlehrerdao.updateFahrlehrer(fLehrer);
		fahrschuelerdao.updateFahrschueler(fSchueler);
	}
	
	private void updatePanel() {
		if (model.getFahrlehrer().getName() != null) {
			zeigePassendeTermine();
		}
		if (model.getFahrschueler().getName() != null) {
			uebersichtFahrstunden();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		updateModel();

		switch (arg1.toString()) {
		case "Fahrlehrer":
			updatePanel();
			break;

		case "Datum":
			updatePanel();
			break;

		case "Fahrschueler":
			updatePanel();
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
			if (model.isAlleFelderAusgefuellt()) {
				bucheFahrstunde();
				updatePanel();
			}
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
				e.printStackTrace();
			}
			break;
		}
	}

}
