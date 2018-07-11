package oberflaeche;

import java.util.Properties;
import java.util.logging.Logger;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Shell;

import fachlogik.MyLoggerUtil;
import fachlogik.PersonInfo;
import fachlogik.PersonType;

public class PersonAnlegenController {

	private PersonAnlegenDialog dialog;

	private String vorname;
	private String nachname;
	private String telefonnummer;
	private String plz;
	private String ort;
	private String strasse;
	private String hausNr;
	private String geburtsdatum;
	private String fuehrerscheinklasse;
	private PersonInfo createdPersonInfo;
	private static Logger log = MyLoggerUtil.createLogger();
	private Properties languageProperties;

	public PersonAnlegenController(PersonType personType, Shell shell, Properties languageProperties) {
		this.languageProperties = languageProperties;
		initGUI(personType, shell);
	}

	private void initGUI(PersonType personType, Shell shell) {
		dialog = new PersonAnlegenDialog(shell, languageProperties);
		dialog.create();
		createDialogListener();
		if (dialog.open() == Window.OK) {
			personInfoAnlegen(personType);
		}
		log.fine(" GUI wird initialisiert. ");
	}

	private void personInfoAnlegen(PersonType personType) {
		createdPersonInfo = null;
		if (isAlleFelderAusgefüllt()) {
			createdPersonInfo = new PersonInfo(personType, vorname + " " + nachname, plz, ort, strasse, hausNr,
					telefonnummer, geburtsdatum, fuehrerscheinklasse);
		}
		log.fine(" Person wurde angelegt. ");
	}

	private boolean isAlleFelderAusgefüllt() {
		boolean result = true;
		result = result && vorname != null;
		result = result && nachname != null;
		result = result && telefonnummer != null;
		result = result && plz != null;
		result = result && ort != null;
		result = result && strasse != null;
		result = result && hausNr != null;
		result = result && geburtsdatum != null;
		result = result && fuehrerscheinklasse != null;
		log.fine(" Überprüfung ob alle Felder ausgefüllt sind ");
		if (!result) {
			log.warning(" Beim Personen anlegen wurden nicht alle Felder ausgefüllt. ");
		} else {
			log.fine(" Beim Personen anlegen wurden alle Felder ausgefüllt. ");
		}
		return result;
	}

	private void createDialogListener() {
		dialog.getVornameInput().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				vorname = dialog.getVornameInput().getText();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		dialog.getNachnameInput().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				nachname = dialog.getNachnameInput().getText();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		dialog.getPlzInput().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				plz = dialog.getPlzInput().getText();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		dialog.getOrtInput().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				ort = dialog.getOrtInput().getText();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		dialog.getStrInput().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				strasse = dialog.getStrInput().getText();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		dialog.getHausnrInput().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				hausNr = dialog.getHausnrInput().getText();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		dialog.getTelInput().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				telefonnummer = dialog.getTelInput().getText();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		dialog.getFsklasseInput().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				fuehrerscheinklasse = dialog.getFsklasseInput().getText();
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		dialog.getGebdat().addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				int tag = dialog.getGebdat().getDay();
				int monat = dialog.getGebdat().getMonth() + 1;
				int jahr = dialog.getGebdat().getYear();
				geburtsdatum = tag + "." + monat + "." + jahr;
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

		// das eingabe feld für das geburtsdatum besitzt einen standardwert, dieser wird
		// zu Beginn in das "model" übernommen
		int tag = dialog.getGebdat().getDay();
		int monat = dialog.getGebdat().getMonth() + 1;
		int jahr = dialog.getGebdat().getYear();
		geburtsdatum = tag + "." + monat + "." + jahr;
	}

	public PersonInfo getCreatedPersonInfo() {
		return createdPersonInfo;
	}
}
