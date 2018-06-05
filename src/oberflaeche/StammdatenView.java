package oberflaeche;

import static com.eclipsesource.tabris.passepartout.PassePartout.columns;
import static com.eclipsesource.tabris.passepartout.PassePartout.createFluidGrid;
import static com.eclipsesource.tabris.passepartout.PassePartout.createFluidGridData;
import static com.eclipsesource.tabris.passepartout.PassePartout.maxWidth;
import static com.eclipsesource.tabris.passepartout.PassePartout.minWidth;
import static com.eclipsesource.tabris.passepartout.PassePartout.px;
import static com.eclipsesource.tabris.passepartout.PassePartout.when;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class StammdatenView extends Observable {

	private Display display;
	private Shell shell;
	private Menu menu, submenu;
	private MenuItem menuitem;

	private CLabel btNeuerFahrschueler, btNeuerFahrlehrer, btStundenBuchen;
	private Table schuelerStammdatenTable, lehrerStammdatenTabelle;

	private Label fillerLabel;
	private FahrschulTheme theme;
	private Composite mainComposite, headerComposite, datenComposite;

	public StammdatenView() {
		initUI();
	}

	private void initUI() {
		display = Display.getDefault();
		shell = new Shell(display);
		theme = new FahrschulTheme();

		initShell();
		erzeugeMenu();
		erzeugeHeader();
		createFillerLabel(mainComposite, 8);
		createFillerLabel(mainComposite, 8);
		erzeugeHauptPanel();
		uebergebeSpaltenanzahl();

		shell.open();
	}

	private void initShell() {
		shell.setFont(theme.getFont1());
		shell.setLayout(createFluidGrid());
		shell.setText("Fahrschul Verwaltung");
		aendereShellGroesse();

		display.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event e) {
				aendereShellGroesse();
				uebergebeSpaltenanzahl();
				anpassenLabelGroesse(fillerLabel);
				setChanged();
				notifyObservers("FenstergroesseAendern");
			}
		});

		mainComposite = new Composite(shell, SWT.BORDER);
		mainComposite.getVerticalBar();
		mainComposite.setRedraw(true);
		mainComposite.setBackground(theme.getWhiteColor());
		mainComposite.setLayout(createFluidGrid());
		mainComposite.setLayoutData(createFluidGridData(when(minWidth(px(720))).then(columns(16)),
				when(maxWidth(px(719))).then(columns(8))));
	}

	private void aendereShellGroesse() {
		// die Shell muss zum Anzeigen der Schrollbar länger sein, als das Display,
		// von der Breite wird Platz für die Scrollbar abgezogen

		Rectangle help = display.getBounds();
		if (help.width < 720) {
			help.width -= 15;
			help.height = 1220; // Gesamthöhe aller Widgets bei schmalem Display
		} else {
			int fill = 867 - help.height; // minimale Höhe, bei der das Textfeld noch komplett zu sehen ist, bei
											// Displaybreite ab 720

			if (fill > 0) {
				help.width -= 15;
				help.height += fill;
			}
		}
		shell.setBounds(help);
	}

	private void erzeugeHeader() {
		headerComposite = new Composite(mainComposite, SWT.NONE);
		headerComposite.setLayout(new GridLayout(5, true));
		setzeSpaltenVonFluidGrudData(headerComposite, 8, 4, 16, 16);

		Label nameLabel = new Label(headerComposite, SWT.NORMAL);
		nameLabel.setText("Fahrschul-Verwaltungssoftware" + "\n" + "Version: 2.0");
		nameLabel.setFont(theme.getFont1());
		nameLabel.setBackground(theme.getWhiteColor());
		nameLabel.setForeground(theme.getBlueColor());

		createFillerLabel(headerComposite, 2);

		Label wechselLabel = new Label(headerComposite, SWT.NONE);
		wechselLabel.setText("Ansicht wechseln:");
		wechselLabel.setFont(theme.getNamefont());

		btStundenBuchen = new CLabel(headerComposite, SWT.BORDER);
		btStundenBuchen.setText("Fahrstunden \n buchen");
		btStundenBuchen.setTextDirection(SWT.CENTER);
		btStundenBuchen.setFont(theme.getFont2());
		btStundenBuchen.setBackground(theme.getBlueColor());
		btStundenBuchen.setForeground(theme.getWhiteColor());
		btStundenBuchen.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
				btStundenBuchen.setBackground(new Color(display, 0, 0, 255));
				setChanged();
				notifyObservers("MainGui");
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				btStundenBuchen.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) { // not needed
			}
		});

		btStundenBuchen.addMouseTrackListener(new MouseTrackListener() {

			@Override
			public void mouseHover(MouseEvent arg0) { // not needed
			}

			@Override
			public void mouseExit(MouseEvent arg0) {
				btStundenBuchen.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseEnter(MouseEvent arg0) {
				btStundenBuchen.setBackground(new Color(display, 125, 125, 255));
			}
		});

		erzeugeLinie(headerComposite);
	}

	private void erzeugeHauptPanel() {

		datenComposite = new Composite(mainComposite, SWT.NONE);
		datenComposite.setBackground(theme.getWhiteColor());
		datenComposite.setLayout(new GridLayout(2, true));

		erzeugeWidgets();

	}

	private void anpassenLabelGroesse(Label label) {
		if (display.getBounds().width < 1870) {
			label.setLayoutData(createFluidGridData(when(minWidth(px(720))).then(columns(2)),
					when(maxWidth(px(719))).then(columns(2))));
		} else {
			label.setLayoutData(createFluidGridData(when(minWidth(px(1871))).then(columns(8)),
					when(maxWidth(px(1970))).then(columns(4))));
		}
	}

	public void uebergebeSpaltenanzahl() {
		setzeSpaltenVonFluidGrudData(datenComposite, 6, 8, 16, 16);
		setzeSpaltenVonFluidGrudData(headerComposite, 8, 4, 16, 16);
	}

	private void setzeSpaltenVonFluidGrudData(Composite composite, int minWidthNormal, int maxWidthNormal,
			int minWidthGross, int maxWidthGross) {
		if (display.getBounds().width < 1871) {
			composite.setLayoutData(createFluidGridData(when(minWidth(px(720))).then(columns(minWidthNormal)),
					when(maxWidth(px(719))).then(columns(maxWidthNormal))));
		} else {
			composite.setLayoutData(createFluidGridData(when(minWidth(px(1872))).then(columns(minWidthGross)),
					when(maxWidth(px(1871))).then(columns(minWidthNormal))));
		}
		shell.layout();
	}

	private void createFillerLabel(Composite parent, int anzahl) {
		for (int i = 0; i < anzahl; i++) {
			Label l = new Label(parent, SWT.NONE);
			l.setBackground(theme.getWhiteColor());
		}
	}

	private void erzeugeWidgets() {

		Label uebersichtFS = new Label(datenComposite, SWT.NONE);
		uebersichtFS.setFont(theme.getNamefont());
		uebersichtFS.setText("Übersicht über die Fahrschüler");
		uebersichtFS.setBackground(theme.getWhiteColor());
		uebersichtFS.setForeground(theme.getBlueColor());

		new Label(datenComposite, SWT.NONE);

		schuelerStammdatenTable = new Table(datenComposite, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		schuelerStammdatenTable.setLinesVisible(true);
		schuelerStammdatenTable.setHeaderVisible(true);
		GridData schuelerdata = new GridData(SWT.FILL, SWT.FILL, true, true);
		schuelerdata.heightHint = 350;
		schuelerStammdatenTable.setLayoutData(schuelerdata);

		String[] schuelertitles = { "Vorname", "Nachname", "Telefonnummer", "Straße", "Hausnummer", "PLZ",
				"Geburtsdatum", "Führerscheinklasse" };
		for (int i = 0; i < schuelertitles.length; i++) {
			TableColumn column = new TableColumn(schuelerStammdatenTable, SWT.NONE);
			column.setText(schuelertitles[i]);
			schuelerStammdatenTable.getColumn(i).pack();
		}

		// So wird die Tabelle im Controller befüllt
		for (int i = 0; i <= 50; i++) {
			TableItem item = new TableItem(schuelerStammdatenTable, SWT.NONE);
			item.setText(0, "Person " + i);
			item.setText(1, "LastName " + i);
			item.setText(2, String.valueOf(i));
		}

		for (int i = 0; i < schuelertitles.length; i++) {
			schuelerStammdatenTable.getColumn(i).pack();
		}

		btNeuerFahrschueler = new CLabel(datenComposite, SWT.BORDER);
		btNeuerFahrschueler.setText("Fahrschueler \n hinzufügen");
		btNeuerFahrschueler.setTextDirection(SWT.CENTER);
		btNeuerFahrschueler.setFont(theme.getFont2());
		btNeuerFahrschueler.setBackground(theme.getBlueColor());
		btNeuerFahrschueler.setForeground(theme.getWhiteColor());
		btNeuerFahrschueler.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
				btNeuerFahrschueler.setBackground(new Color(display, 0, 0, 255));
				setChanged();
				notifyObservers("FahrschuelerNeu");

				MessageBox dialog = new MessageBox(shell, 0);
				dialog.setMessage("Hier könnte man einen neuen Fahrschüler anlegen");
				dialog.open();
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				btNeuerFahrschueler.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) { // not needed
			}
		});

		btNeuerFahrschueler.addMouseTrackListener(new MouseTrackListener() {

			@Override
			public void mouseHover(MouseEvent arg0) { // not needed
			}

			@Override
			public void mouseExit(MouseEvent arg0) {
				btNeuerFahrschueler.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseEnter(MouseEvent arg0) {
				btNeuerFahrschueler.setBackground(new Color(display, 125, 125, 255));
			}
		});

		erzeugeLinie(datenComposite);

		createFillerLabel(datenComposite, 4);

		Label uebersichtFL = new Label(datenComposite, SWT.NONE);
		uebersichtFL.setFont(theme.getNamefont());
		uebersichtFL.setText("Übersicht über die Fahrlehrer");
		uebersichtFL.setBackground(theme.getWhiteColor());
		uebersichtFL.setForeground(theme.getBlueColor());

		new Label(datenComposite, SWT.NONE);

		lehrerStammdatenTabelle = new Table(datenComposite, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		lehrerStammdatenTabelle.setLinesVisible(true);
		lehrerStammdatenTabelle.setHeaderVisible(true);
		GridData lehrerdata = new GridData(SWT.FILL, SWT.FILL, true, true);
		lehrerdata.heightHint = 200;
		lehrerStammdatenTabelle.setLayoutData(lehrerdata);

		String[] lehrertitles = { "Vorname", "Nachname", "Telefonnummer", "Straße", "Hausnummer", "PLZ", "Geburtsdatum",
				"Führerscheinklasse" };
		for (int i = 0; i < lehrertitles.length; i++) {
			TableColumn column = new TableColumn(lehrerStammdatenTabelle, SWT.NONE);
			column.setText(lehrertitles[i]);
			lehrerStammdatenTabelle.getColumn(i).pack();
		}

		// So wird die Tabelle im Controller befüllt
		for (int i = 0; i <= 50; i++) {
			TableItem item = new TableItem(lehrerStammdatenTabelle, SWT.NONE);
			item.setText(0, "Person " + i);
			item.setText(1, "LastName " + i);
			item.setText(2, String.valueOf(i));
		}

		for (int i = 0; i < lehrertitles.length; i++) {
			lehrerStammdatenTabelle.getColumn(i).pack();
		}

		btNeuerFahrlehrer = new CLabel(datenComposite, SWT.BORDER);
		btNeuerFahrlehrer.setText("Fahrlehrer \n hinzufügen");
		btNeuerFahrlehrer.setTextDirection(SWT.CENTER);
		btNeuerFahrlehrer.setFont(theme.getFont2());
		btNeuerFahrlehrer.setBackground(theme.getBlueColor());
		btNeuerFahrlehrer.setForeground(theme.getWhiteColor());
		btNeuerFahrlehrer.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
				btNeuerFahrlehrer.setBackground(new Color(display, 0, 0, 255));
				setChanged();
				notifyObservers("FahrlehrerNeu");

				MessageBox dialog = new MessageBox(shell, 0);
				dialog.setMessage("Hier könnte man einen neuen Fahrlehrer anlegen");
				dialog.open();
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				btNeuerFahrlehrer.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) { // not needed
			}
		});

		btNeuerFahrlehrer.addMouseTrackListener(new MouseTrackListener() {

			@Override
			public void mouseHover(MouseEvent arg0) { // not needed
			}

			@Override
			public void mouseExit(MouseEvent arg0) {
				btNeuerFahrlehrer.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseEnter(MouseEvent arg0) {
				btNeuerFahrlehrer.setBackground(new Color(display, 125, 125, 255));
			}
		});

		erzeugeLinie(datenComposite);

	}

	public void erzeugeLinie(Composite composite) {
		Label trennstrichLabel = new Label(composite, SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
		gridData.heightHint = 1;
		gridData.horizontalSpan = 5;
		trennstrichLabel.setLayoutData(gridData);
	}

	private void erzeugeMenu() {
		// Erzeugung des Menus
		String[] menuBarEntries = { "Datei", "Hilfe" };
		String[] menuEntries = { "Öffnen,Speichern,Schließen", "Hilfe,--,About" };

		menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		for (int i = 0; i < menuBarEntries.length; i++) {
			// Eintrag im Hauptmenu
			menuitem = new MenuItem(menu, SWT.CASCADE);
			menuitem.setText(menuBarEntries[i]);

			// Menu, das an einen Eintrag gebunden ist
			submenu = new Menu(shell, SWT.DROP_DOWN);
			menuitem.setMenu(submenu);

			createSubmenuEntries(submenu, menuEntries[i]);
		}
	}

	private void createSubmenuEntries(Menu menuitem, String string) {
		String[] entries = string.split(",");
		for (int i = 0; i < entries.length; i++) {
			if (entries[i].equalsIgnoreCase("--")) {
				new MenuItem(menuitem, SWT.SEPARATOR);
			} else {
				MenuItem item = new MenuItem(menuitem, SWT.PUSH);
				item.setText(entries[i]);
			}
		}
	}

	public void startEventHandler() {
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public static void main(String[] args) {
		StammdatenView stv = new StammdatenView();
		stv.startEventHandler();

	}

	public CLabel getBtNeuerFahrschueler() {
		return btNeuerFahrschueler;
	}

	public CLabel getBtNeuerFahrlehrer() {
		return btNeuerFahrlehrer;
	}

	public Table getSchuelerStammdatenTable() {
		return schuelerStammdatenTable;
	}

	public Table getLehrerStammdatenTabelle() {
		return lehrerStammdatenTabelle;
	}

	public Display getDisplay() {
		return this.display;
	}

}
