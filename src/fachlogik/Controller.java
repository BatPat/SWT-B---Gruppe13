package fachlogik;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;
import datenhaltung.FahrstundeDaoImpl;
import datenhaltung.PruefungDaoImpl;
import datenhaltung.TheorieStundeDaoImpl;
import oberflaeche.MainView;

public class Controller implements Observer {

	private FahrschulModel model;
	private MainView mainview;
	private PdfDocumentBill pdf;
	private Fahrschule fahrschule;
	private Kalender kalender;
	private StammdatenController stammdatenController;

	public Controller() {
		pdf = new PdfDocumentBill();
		initModel();
		List<Fuehrerscheinklasse> klassen = new ArrayList<>();
		klassen.add(Fuehrerscheinklasse.B);
		fahrschule = new Fahrschule(FahrschuelerDaoImpl.getInstance(), FahrlehrerDaoImpl.getInstance(), klassen);
		kalender = new Kalender(TheorieStundeDaoImpl.getInstance(), FahrstundeDaoImpl.getInstance(), PruefungDaoImpl.getInstance());
		initGUI();
		mainview.getShell().layout(true);
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
		for (FahrlehrerDTO f : fahrschule.getFahrlehrerListe()) {
			mainview.getLehrerCombo().add(f.getName());
		}
		for (FahrschuelerDTO f : fahrschule.getFahrschuelerListe()) {
			mainview.getSchuelerCombo().add(f.getName());
		}
		
		
		for (Fuehrerscheinklasse klasse : fahrschule.getAngeboteneKlassen()) {
			mainview.getFsKlasseCombo().add(klasse.toString());
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

		FahrlehrerDTO fahrlehrer = model.getFahrlehrer();

		List<FahrstundeDTO> terminefahrlehr;
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

		FahrschuelerDTO fahrschueler = model.getFahrschueler();
		int anzNorm = 0;
		int anzSond = 0;
		int anzTheo = 0;
		for (FahrstundeDTO fahrstunde : fahrschueler.getFahrstunden()) {
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
				&& mainview.getFsKlasseCombo().getText().isEmpty());
	}

	private void updateModel() {

		String fahrschuelername = mainview.getSchuelerCombo().getText();
		String fahlehrername = mainview.getLehrerCombo().getText();
		String artString = mainview.getArtCombo().getText();
		String uhrzeitString = mainview.getTimeCombo().getText();
		int datumJahr = mainview.getDateFahrstunde().getYear();
		int datumMonat = mainview.getDateFahrstunde().getMonth();
		int datumTag = mainview.getDateFahrstunde().getDay();

		FahrschuelerDTO fSchueler = null;
		FahrlehrerDTO fLehrer = null;
		LocalTime terminUhrzeit = null;
		Fahrstundenart fStundenArt = null;
		LocalDate terminDatum = null;

		if (!fahrschuelername.isEmpty()) {
			fSchueler = fahrschule.getFahrschueler(fahrschuelername);
			model.setFahrschueler(fSchueler);
		}

		if (!fahlehrername.isEmpty()) {
			fLehrer = fahrschule.getFahrlehrer(fahlehrername);
			model.setFahrlehrer(fLehrer);
		}

		if (!uhrzeitString.isEmpty()) {
			if(uhrzeitString.length() == 4) {
				uhrzeitString = "0" + uhrzeitString;
			}
			terminUhrzeit = LocalTime.of(Integer.parseInt(uhrzeitString.substring(0, 2)),
					Integer.parseInt(uhrzeitString.substring(3)));
			model.setUhrzeit(terminUhrzeit);
		}

		terminDatum = LocalDate.of(datumJahr, datumMonat, datumTag);
		if (!LocalDate.now().isAfter(terminDatum)) {
			model.setDatum(terminDatum);
		}
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
		FahrstundeDTO fStunde = model.getFahrstunde();
		FahrschuelerDTO fSchueler = model.getFahrschueler();
		FahrlehrerDTO fLehrer = model.getFahrlehrer();

		fLehrer.getFahrstunden().add(fStunde);
		fSchueler.getFahrstunden().add(fStunde);
		kalender.addFahrstunde(fStunde);
		fahrschule.updateFahrlehrer(fLehrer);
		fahrschule.updateFahrschueler(fSchueler);
	}
	
	private void updatePanel() {
		if (model.getFahrlehrer().getName() != null) {
			zeigePassendeTermine();
			mainview.getLehrernameLabel().setText(model.getFahrlehrer().getName());
		}
		if (model.getFahrschueler().getName() != null) {
			uebersichtFahrstunden();
			mainview.getSchuelernameLabel().setText(model.getFahrschueler().getName());
		}

		int datumJahr = mainview.getDateFahrstunde().getYear();
		int datumMonat = mainview.getDateFahrstunde().getMonth();
		int datumTag = mainview.getDateFahrstunde().getDay();
		LocalDate terminDatum = LocalDate.of(datumJahr, datumMonat, datumTag);
		if (LocalDate.now().isAfter(terminDatum)) {
			resetViewDatum();
		}
	}

	private void resetViewDatum() {
		mainview.getDateFahrstunde().setDay(LocalDate.now().getDayOfMonth());
		mainview.getDateFahrstunde().setMonth(LocalDate.now().getMonthValue());
		mainview.getDateFahrstunde().setYear(LocalDate.now().getYear());
	}
	
	public void restoreGUI() {
		initGUI();
		mainview.getShell().layout(true);
		
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

		case "StammdatenanGui":
			mainview.getDisplay().close();
			stammdatenController = new StammdatenController(this, fahrschule);
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
