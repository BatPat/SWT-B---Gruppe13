package fachlogik;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import datenhaltung.FahrlehrerDaoImpl;
import datenhaltung.FahrschuelerDaoImpl;
import oberflaeche.StammdatenView;

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
			TableItem item = new TableItem(table, SWT.NONE);
			int whitespaceIndex= fahrlehrer.getName().indexOf(" ");
			item.setText(0, fahrlehrer.getName().substring(0, whitespaceIndex));
			item.setText(1, fahrlehrer.getName().substring(whitespaceIndex + 1));
			item.setText(2, "Telefonnummer");
			item.setText(3, fahrlehrer.getStrasse());
			item.setText(4, fahrlehrer.getHausnummer());
			item.setText(5, fahrlehrer.getPlz());
			item.setText(6, "Geburtsdatum");
			item.setText(7, "Fuehrerscheinklasse");
		}
		for (int i = 0; i < table.getColumns().length; i++) {
			TableColumn column = table.getColumns()[i];
			column.pack();
		}
	}

	private void fillSchuelerListContent() {
		Table table = stammdatenView.getSchuelerStammdatenTable();
		table.removeAll();
		List<FahrschuelerDTO> fListe= fahrschule.getFahrschuelerListe();
		for (FahrschuelerDTO fahrschueler : fListe) {
			TableItem item = new TableItem(table, SWT.NONE);
			int whitespaceIndex= fahrschueler.getName().indexOf(" ");
			item.setText(0, fahrschueler.getName().substring(0, whitespaceIndex));
			item.setText(1, fahrschueler.getName().substring(whitespaceIndex + 1));
			item.setText(2, "Telefonnummer");
			item.setText(3, fahrschueler.getStrasse());
			item.setText(4, fahrschueler.getHausnummer());
			item.setText(5, fahrschueler.getPlz());
			item.setText(6, "Geburtsdatum");
			item.setText(7, "Fuehrerscheinklasse");
		}
		for (int i = 0; i < table.getColumns().length; i++) {
			TableColumn column = table.getColumns()[i];
			column.pack();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		switch (arg1.toString()) {
		case "FahrlehrerNeu":
			//anpassen wenn view neue fahrlehrer erlaubt
			fillLehrerListContent();
			break;

		case "FahrschuelerNeu":
			//anpassen wenn view neue fahrschueler erlaubt
			fillSchuelerListContent();
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

}
