package oberflaeche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;
import fachlogik.FahrlehrerDTO;
import fachlogik.FahrschuelerDTO;
import fachlogik.Fahrschule;
import fachlogik.Person;
import fachlogik.PersonType;

public class StammdatenController implements Observer {
	
	private Controller controller;
	private StammdatenView stammdatenView;
	private Fahrschule fahrschule;

	public StammdatenController(Controller controller, Fahrschule fahrschule) {
		super();
		this.controller = controller;
		this.fahrschule = fahrschule;
		initGUI();
	}

	private void initGUI() {
		stammdatenView = new StammdatenView();
		stammdatenView.addObserver(this);
		fillSchuelerListContent();
		fillLehrerListContent();
		stammdatenView.startEventHandler();
	}

	private void fillLehrerListContent() {
		Table table = stammdatenView.getLehrerStammdatenTabelle();
		table.removeAll();
		List<FahrlehrerDTO> fListe= fahrschule.getFahrlehrerListe();
		for (FahrlehrerDTO fahrlehrer : fListe) {
			addPersonToTable(fahrlehrer, table);
		}
		packTable(table);
	}

	private void fillSchuelerListContent() {
		Table table = stammdatenView.getSchuelerStammdatenTable();
		table.removeAll();
		List<FahrschuelerDTO> fListe= fahrschule.getFahrschuelerListe();
		for (FahrschuelerDTO fahrschueler : fListe) {
			addPersonToTable(fahrschueler, table);
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
		Person p;
		switch (arg1.toString()) {
		case "FahrlehrerNeu":
			p = createPerson(PersonType.FAHRLEHRER);
			if(p != null) {
				Table table = stammdatenView.getLehrerStammdatenTabelle();
				addPersonToTable(p, table);				
				packTable(table);
			}
			break;

		case "FahrschuelerNeu":
			p = createPerson(PersonType.FAHRSCHUELER);
			if(p != null) {
				Table table = stammdatenView.getSchuelerStammdatenTable();
				addPersonToTable(p, table);				
				packTable(table);
			}
			break;

		case "MainGui":
			stammdatenView.getDisplay().close();
			controller.restoreGUI();
			break;

		case "FenstergroesseAendern":
			//nix
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

	private void addPersonToTable(Person p, Table table) {
		TableItem item= new TableItem(table, SWT.NONE);
		int whitespaceIndex= p.getName().indexOf(" ");
		item.setText(0, p.getName().substring(0, whitespaceIndex));
		item.setText(1, p.getName().substring(whitespaceIndex + 1));
		item.setText(2, p.getTelefonnummer());
		item.setText(3, p.getStrasse());
		item.setText(4, p.getHausnummer());
		item.setText(5, p.getPlz());
		item.setText(6, p.getGeburtsdatum());
		item.setText(7, p.getFuehrerscheinklasse());
	}

	private Person createPerson(PersonType personType) {
		
		PersonAnlegenController pac = new PersonAnlegenController(personType, stammdatenView.getDisplay().getActiveShell());
		return pac.getCreatedPerson();
	}

}
