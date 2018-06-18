package oberflaeche;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fachlogik.FahrlehrerDTO;
import fachlogik.FahrschuelerDTO;
import fachlogik.FahrschulModel;
import fachlogik.Fahrschule;
import fachlogik.FahrstundeDTO;
import fachlogik.Fahrstundenart;
import fachlogik.Fuehrerscheinklasse;
import fachlogik.PdfDocumentBill;

public class Controller implements Observer {

	private FahrschulModel model;
	private MainView mainview;
	private PdfDocumentBill pdf;
	private Fahrschule fahrschule;
	private List<Integer> fahrlehrerids;
	private List<Integer> fahrschuelerids;

	public Controller() {
		pdf = new PdfDocumentBill();
		initModel();
		fahrschule = new Fahrschule();
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
		fahrlehrerids = new ArrayList<>();
		for (FahrlehrerDTO f : fahrschule.getFahrlehrerListe()) {
			mainview.getLehrerCombo().add(f.getName());
			fahrlehrerids.add(f.getId());
		}
		fahrschuelerids = new ArrayList<>();
		for (FahrschuelerDTO f : fahrschule.getFahrschuelerListe()) {
			mainview.getSchuelerCombo().add(f.getName());
			fahrschuelerids.add(f.getId());
		}

		for (Fuehrerscheinklasse klasse : fahrschule.getAngeboteneKlassen()) {
			mainview.getFsKlasseCombo().add(klasse.toString());
		}

		for (Fahrstundenart f : Fahrstundenart.values()) {
			mainview.getArtCombo().add(f.getBeschreibung());
		}
	}

	private void zeigePassendeTermine() {
		mainview.getTimeCombo().removeAll();

		if (!auswahlFeldersindleer() && mainview.getTimeCombo().getText().isEmpty()) {
			for (int i = 9; i < 22; i++) {
				String zeit = i + ":00";
				mainview.getTimeCombo().add(zeit);
			}
		}

		
		int tag = mainview.getDateFahrstunde().getDay();
		// localdate arbeitet von 1 - 12, das datums element von 0 - 11
		int monat = mainview.getDateFahrstunde().getMonth() + 1;
		int jahr = mainview.getDateFahrstunde().getYear();
		int fId = fahrlehrerids.get(mainview.getLehrerCombo().getSelectionIndex());
		List<LocalDateTime> termine = fahrschule.getTermine(fId);
		
		for (LocalDateTime l : termine) {
			if(l.getDayOfMonth() == tag
					&& l.getMonthValue() == monat
					&& l.getYear() == jahr) {
				if(l.getHour() > 8 && l.getHour() <23) {
					String zeit = l.getHour() + ":00";
					mainview.getTimeCombo().remove(zeit);
				}
			}
		}
	}

	private void uebersichtFahrstunden() {
		int fahrschuelerId = fahrschuelerids.get(mainview.getSchuelerCombo().getSelectionIndex());
		int[] anzFahrstunden = fahrschule.getAnzSonderAndStandardFahrten(fahrschuelerId);
		String anzSo = anzFahrstunden[0] + "";
		String anzNo = anzFahrstunden[1] + "";
		String anzTh = fahrschule.getAnzTheoriestunden(fahrschuelerId) + "";
		mainview.getTheorieTxt().setText(anzTh);
		mainview.getSonderfahrtTxt().setText(anzSo);
		mainview.getFahrstundeTxt().setText(anzNo);
	}

	private boolean auswahlFeldersindleer() {
		return (mainview.getLehrerCombo().getText().isEmpty() && mainview.getSchuelerCombo().getText().isEmpty()
				&& mainview.getFsKlasseCombo().getText().isEmpty());
	}

	private void updateModel() {

		int fahrschuelerindex = mainview.getSchuelerCombo().getSelectionIndex();
		int fahrlehrerindex = mainview.getLehrerCombo().getSelectionIndex();
		String artString = mainview.getArtCombo().getText();
		String uhrzeitString = mainview.getTimeCombo().getText();
		int datumJahr = mainview.getDateFahrstunde().getYear();
		// localdate arbeitet von 1 - 12, das datums element von 0 - 11
		int datumMonat = mainview.getDateFahrstunde().getMonth() + 1;
		int datumTag = mainview.getDateFahrstunde().getDay();

		
		if (mainview.getSchuelerCombo().getSelectionIndex() != -1) {
			model.setFahrschueler(fahrschule.getFahrschueler(fahrschuelerids.get(fahrschuelerindex)));
		}

		if (mainview.getLehrerCombo().getSelectionIndex() != -1) {
			model.setFahrlehrer(fahrschule.getFahrlehrer(fahrlehrerids.get(fahrlehrerindex)));
		}

		if (!uhrzeitString.isEmpty()) {
			if (uhrzeitString.length() == 4) {
				uhrzeitString = "0" + uhrzeitString;
			}
			LocalTime terminUhrzeit = LocalTime.of(Integer.parseInt(uhrzeitString.substring(0, 2)),
					Integer.parseInt(uhrzeitString.substring(3)));
			model.setUhrzeit(terminUhrzeit);
		}

		LocalDate terminDatum = LocalDate.of(datumJahr, datumMonat, datumTag);
		if (!LocalDate.now().isAfter(terminDatum)) {
			model.setDatum(terminDatum);
		}
		if (!artString.isEmpty()) {
			for(Fahrstundenart f : Fahrstundenart.values()) {
				if(f.getBeschreibung().equals(artString.trim())) {
					model.setArt(f);
				}
			}
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
		// localdate arbeitet von 1 - 12, das datums element von 0 - 11
		int datumMonat = mainview.getDateFahrstunde().getMonth() + 1;
		int datumTag = mainview.getDateFahrstunde().getDay();
		LocalDate terminDatum = LocalDate.of(datumJahr, datumMonat, datumTag);
		if (LocalDate.now().isAfter(terminDatum)) {
			resetViewDatum();
		}
	}

	private void resetViewDatum() {
		mainview.getDateFahrstunde().setDay(LocalDate.now().getDayOfMonth());
		// localdate arbeitet von 1 - 12, das datums element von 0 - 11
		mainview.getDateFahrstunde().setMonth(LocalDate.now().getMonthValue() - 1);
		mainview.getDateFahrstunde().setYear(LocalDate.now().getYear());
	}
	
	public void restoreGUI() {
		initGUI();
		mainview.getShell().layout(true);
		
	}

	public boolean isAlleFelderAusgefuellt() {
		boolean result = true;

		result = mainview.getLehrerCombo().getSelectionIndex() != -1;
		result = result && mainview.getSchuelerCombo().getSelectionIndex() != -1;
		result = result && mainview.getFsKlasseCombo().getSelectionIndex() != -1;
		result = result && mainview.getArtCombo().getSelectionIndex() != -1;
		result = result && mainview.getTimeCombo().getSelectionIndex() != -1;

		return result;
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
			if (isAlleFelderAusgefuellt()) {
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
			new StammdatenController(this, fahrschule);
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
