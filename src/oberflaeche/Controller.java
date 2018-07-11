package oberflaeche;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.logging.Logger;

import fachlogik.FahrschulModel;
import fachlogik.Fahrschule;
import fachlogik.Fahrstundenart;
import fachlogik.Fuehrerscheinklasse;
import fachlogik.MyLoggerUtil;
import fachlogik.PersonInfo;

public class Controller implements Observer {

	private FahrschulModel model;
	private MainView mainview;
	private Fahrschule fahrschule;
	private List<Integer> fahrlehrerids;
	private List<Integer> fahrschuelerids;
	private int chachedFahrlehrerSelection = -1;
	private int chachedFahrschuelerSelection = -1;
	private Properties languageProperties;
	private static Logger log = MyLoggerUtil.createLogger();

	public Controller(Properties languageProperties) {
		initModel();
		fahrschule = new Fahrschule();
		this.languageProperties = languageProperties;
		initGUI(languageProperties);
		mainview.getShell().layout(true);
	}

	private void initGUI(Properties languageProperties) {
		mainview = new MainView(languageProperties);
		mainview.addObserver(this);
		fillListContent();
		mainview.startEventHandler();
		log.fine(" GUI wird initialisiert. ");
	}

	private void initModel() {
		model = new FahrschulModel();
		log.fine(" Model wird initialisiert. ");
	}

	private void fillListContent() {
		fahrlehrerids = new ArrayList<>();
		for (PersonInfo f : fahrschule.getFahrlehrerInfoListe()) {
			mainview.getLehrerCombo().add(f.getName());
			fahrlehrerids.add(f.getId());
		}
		fahrschuelerids = new ArrayList<>();
		for (PersonInfo f : fahrschule.getFahrschuelerInfoListe()) {
			mainview.getSchuelerCombo().add(f.getName());
			fahrschuelerids.add(f.getId());
		}

		for (Fuehrerscheinklasse klasse : fahrschule.getAngeboteneKlassen()) {
			mainview.getFsKlasseCombo().add(klasse.toString());
		}

		for (Fahrstundenart f : Fahrstundenart.values()) {
			mainview.getArtCombo().add(f.getBeschreibung());
		}
		log.fine(" Drop-Down-Listen werden gefüllt. ");
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
		int fId = fahrlehrerids.get(getFahrlehrerSelectionIndex());
		List<LocalDateTime> termine = fahrschule.getTermine(fId);

		for (LocalDateTime l : termine) {
			if (l.getDayOfMonth() == tag && l.getMonthValue() == monat && l.getYear() == jahr) {
				if (l.getHour() > 8 && l.getHour() < 22) {
					String zeit = l.getHour() + ":00";
					mainview.getTimeCombo().remove(zeit);
				}
			}
		}
		log.fine(" Freie Termine werden geladen. ");
	}

	private void uebersichtFahrstunden() {
		int fahrschuelerId = fahrschuelerids.get(getFahrschuelerSelectionIndex());
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

		int fahrschuelerindex = getFahrschuelerSelectionIndex();
		int fahrlehrerindex = getFahrlehrerSelectionIndex();
		String artString = mainview.getArtCombo().getText();
		String uhrzeitString = mainview.getTimeCombo().getText();
		int datumJahr = mainview.getDateFahrstunde().getYear();
		// localdate arbeitet von 1 - 12, das datums element von 0 - 11
		int datumMonat = mainview.getDateFahrstunde().getMonth() + 1;
		int datumTag = mainview.getDateFahrstunde().getDay();

		if (fahrschuelerindex != -1 && fahrschuelerindex != chachedFahrschuelerSelection) {
			chachedFahrschuelerSelection = fahrschuelerindex;
			model.setFahrschuelerId(fahrschuelerids.get(fahrschuelerindex));
		}

		if (fahrlehrerindex != -1 && fahrlehrerindex != chachedFahrlehrerSelection) {
			chachedFahrlehrerSelection = fahrlehrerindex;
			model.setFahrlehrerId(fahrlehrerids.get(fahrlehrerindex));
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
		} else {
			resetViewDatum();
		}

		if (!artString.isEmpty()) {
			for (Fahrstundenart f : Fahrstundenart.values()) {
				if (f.getBeschreibung().equals(artString.trim())) {
					model.setArt(f);
				}
			}
		}
		log.fine(" Model wird auf Veränderungen geprüft und alle Änderungen werden übernommen und neu geladen. ");
	}

	private int getFahrlehrerSelectionIndex() {
		return mainview.getLehrerCombo().getSelectionIndex();
	}

	private void erstellePdf() throws IOException {
		if (getFahrschuelerSelectionIndex() != -1) {
			fahrschule.druckeRechnungPdf(model.getFahrschuelerId());
			log.fine(" PDF-Rechnung wird erstellt. ");
		}
	}

	private void bucheFahrstunde() {
		int fSchuelerId = model.getFahrschuelerId();
		int fLehrerId = model.getFahrlehrerId();
		LocalDate datum = model.getDatum();
		LocalTime zeit = model.getUhrzeit();
		Fahrstundenart art = model.getArt();

		fahrschule.bucheFahrstunde(art, fSchuelerId, fLehrerId, datum, zeit, "Fahrschule Terlau");
		log.fine(" Fahrstunde wurde gebucht. ");
	}

	private void updatePanel() {
		if (getFahrlehrerSelectionIndex() != -1) {
			zeigePassendeTermine();
			updateLehrerLabel();
		}
		if (getFahrschuelerSelectionIndex() != -1) {
			uebersichtFahrstunden();
			updateSchuelerLabel();
		}
		if (LocalDate.now().isAfter(model.getDatum())) {
			resetViewDatum();
		}
	}

	private void updateSchuelerLabel() {
		mainview.getSchuelernameLabel().setText(fahrschule.getFahrschuelerInfo(model.getFahrschuelerId()).getName());
	}

	private void updateLehrerLabel() {
		mainview.getLehrernameLabel().setText(fahrschule.getFahrlehrerInfo(model.getFahrlehrerId()).getName());
	}

	private void resetViewDatum() {
		mainview.getDateFahrstunde().setDay(LocalDate.now().getDayOfMonth());
		// localdate arbeitet von 1 - 12, das datums element von 0 - 11
		mainview.getDateFahrstunde().setMonth(LocalDate.now().getMonthValue() - 1);
		mainview.getDateFahrstunde().setYear(LocalDate.now().getYear());
	}

	public void restoreGUI() {
		initGUI(languageProperties);
		mainview.getShell().layout(true);

	}

	private boolean isAlleFelderAusgefuellt() {
		boolean result = true;

		result = getFahrlehrerSelectionIndex() != -1;
		result = result && getFahrschuelerSelectionIndex() != -1;
		result = result && mainview.getFsKlasseCombo().getSelectionIndex() != -1;
		result = result && mainview.getArtCombo().getSelectionIndex() != -1;
		result = result && mainview.getTimeCombo().getSelectionIndex() != -1;
		log.fine(" Überprüfung ob alle Felder gefüllt sind ");
		return result;
	}

	private int getFahrschuelerSelectionIndex() {
		return mainview.getSchuelerCombo().getSelectionIndex();
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		updateModel();

		switch (arg1.toString()) {
		case "Fahrlehrer":
			zeigePassendeTermine();
			updateLehrerLabel();
			break;

		case "Datum":
			zeigePassendeTermine();
			break;

		case "Fahrschueler":
			uebersichtFahrstunden();
			updateSchuelerLabel();
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
			new StammdatenController(this, fahrschule, languageProperties);
			log.fine(" Modus wurde gewechselt zum Anlegen neuer Person. ");
			break;

		case "german":
			aendereSprache("de");
			log.fine(" Sprache wurde gewechselt zu Deutsch. ");
			break;

		case "english":
			aendereSprache("en");
			log.fine(" Sprache wurde gewechselt zu Englisch. ");
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

	private void aendereSprache(String sprachKuerzel) {
		try {
			languageProperties.load(new FileInputStream("resources/" + sprachKuerzel + ".properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mainview.getDisplay().close();
		restoreGUI();
	}

}
