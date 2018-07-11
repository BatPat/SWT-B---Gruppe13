package oberflaeche;

import java.io.FileInputStream;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import fachlogik.Fahrschule;
import fachlogik.PersonInfo;
import fachlogik.PersonType;

public class StammdatenController implements Observer {

	private Controller controller;
	private StammdatenView stammdatenView;
	private Fahrschule fahrschule;
	private Properties languageProperties;

	public StammdatenController(Controller controller, Fahrschule fahrschule, Properties languageProperties) {
		super();
		this.controller = controller;
		this.fahrschule = fahrschule;
		this.languageProperties = languageProperties;
		initGUI(languageProperties);
	}

	private void initGUI(Properties languageProperties) {
		stammdatenView = new StammdatenView(languageProperties);
		stammdatenView.addObserver(this);
		fillSchuelerListContent();
		fillLehrerListContent();
		stammdatenView.startEventHandler();
	}

	private void fillLehrerListContent() {
		Table table = stammdatenView.getLehrerStammdatenTabelle();
		table.removeAll();
		List<PersonInfo> fListe = fahrschule.getFahrlehrerInfoListe();
		for (PersonInfo fahrlehrer : fListe) {
			addPersonInfoToTable(fahrlehrer, table);
		}
		packTable(table);
	}

	private void fillSchuelerListContent() {
		Table table = stammdatenView.getSchuelerStammdatenTable();
		table.removeAll();
		List<PersonInfo> fListe = fahrschule.getFahrschuelerInfoListe();
		for (PersonInfo fahrschueler : fListe) {
			addPersonInfoToTable(fahrschueler, table);
		}
		packTable(table);
	}

	private void packTable(Table table) {
		for (int i = 0; i < table.getColumns().length; i++) {
			TableColumn column = table.getColumns()[i];
			column.pack();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		PersonInfo p;
		switch (arg1.toString()) {
		case "FahrlehrerNeu":
			p = createPersonInfo(PersonType.FAHRLEHRER);
			if (p != null) {
				fahrschule.addPerson(p);
				Table table = stammdatenView.getLehrerStammdatenTabelle();
				addPersonInfoToTable(p, table);
				packTable(table);
			}
			break;

		case "FahrschuelerNeu":
			p = createPersonInfo(PersonType.FAHRSCHUELER);
			if (p != null) {
				fahrschule.addPerson(p);
				Table table = stammdatenView.getSchuelerStammdatenTable();
				addPersonInfoToTable(p, table);
				packTable(table);
			}
			break;

		case "MainGui":
			stammdatenView.getDisplay().close();
			controller.restoreGUI();
			break;

		case "FenstergroesseAendern":
			// nix
			break;

		case "german":
			aendereSprache("de");
			break;

		case "english":
			aendereSprache("en");
			break;

		default:
			try {
				throw new Exception("unerwartetes Event in der stammdatenView");
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
		stammdatenView.getDisplay().close();
		initGUI(languageProperties);
	}

	private void addPersonInfoToTable(PersonInfo p, Table table) {
		TableItem item = new TableItem(table, SWT.NONE);
		int whitespaceIndex = p.getName().indexOf(" ");
		item.setText(0, p.getName().substring(0, whitespaceIndex));
		item.setText(1, p.getName().substring(whitespaceIndex + 1));
		item.setText(2, p.getTelefonnummer());
		item.setText(3, p.getStrasse());
		item.setText(4, p.getHausnummer());
		item.setText(5, p.getPlz());
		item.setText(6, p.getGeburtsdatum());
		item.setText(7, p.getFuehrerscheinklasse());
	}

	private PersonInfo createPersonInfo(PersonType personType) {

		PersonAnlegenController pac = new PersonAnlegenController(personType,
				stammdatenView.getDisplay().getActiveShell());
		return pac.getCreatedPersonInfo();
	}

}
