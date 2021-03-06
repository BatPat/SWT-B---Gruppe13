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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainView extends Observable {

	private Display display;
	private Shell shell;
	private CLabel btBuchen, btRechnung, btStammdaten;
	private DateTime dateFahrstunde;

	private Combo lehrerCombo, schuelerCombo, fsKlasseCombo, artCombo, timeCombo;
	private Text fahrstundeTxt, sonderfahrtTxt, theorieTxt;

	private Label schuelernameLabel, lehrernameLabel;

	private Label fillerLabel;
	private FahrschulTheme theme;
	private Composite mainComposite, headerComposite, eingabe1Composite, eingabe2Composite, kalenderComposite,
			uebersichtComposite;

	public MainView() {
		initUI();
	}

	private void initUI() {
		display = Display.getDefault();
		shell = new Shell(display);
		theme = new FahrschulTheme();

		initShell();
		erzeugeHeader();
		createFillerLabel(mainComposite, 8);
		createFillerLabel(mainComposite, 8);
		erzeugeHauptPanels();
		erzeugeObereWidgets();
		erzeugeUntereLinkeWidgets();
		erzeugeUntereRechteWidgets();
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
				anpassenAnzahlFillerLabel();
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

		btStammdaten = new CLabel(headerComposite, SWT.BORDER);
		btStammdaten.setText("Stammdaten \n anzeigen");
		btStammdaten.setTextDirection(SWT.CENTER);
		btStammdaten.setFont(theme.getFont2());
		btStammdaten.setBackground(theme.getBlueColor());
		btStammdaten.setForeground(theme.getWhiteColor());
		btStammdaten.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
				btStammdaten.setBackground(new Color(display, 0, 0, 255));
				setChanged();
				notifyObservers("StammdatenanGui");
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				btStammdaten.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) { // not needed
			}
		});

		btStammdaten.addMouseTrackListener(new MouseTrackListener() {

			@Override
			public void mouseHover(MouseEvent arg0) { // not needed
			}

			@Override
			public void mouseExit(MouseEvent arg0) {
				btStammdaten.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseEnter(MouseEvent arg0) {
				btStammdaten.setBackground(new Color(display, 125, 125, 255));
			}
		});

		erzeugeLinie(headerComposite);
	}

	private void erzeugeHauptPanels() {

		eingabe1Composite = new Composite(mainComposite, SWT.NONE);
		eingabe1Composite.setBackground(theme.getWhiteColor());
		eingabe1Composite.setLayout(new GridLayout(3, true));

		eingabe2Composite = new Composite(mainComposite, SWT.NONE);
		eingabe2Composite.setBackground(theme.getWhiteColor());
		eingabe2Composite.setLayout(new GridLayout(3, true));

		createFillerLabel(mainComposite, 8);
		createFillerLabel(mainComposite, 8);

		kalenderComposite = new Composite(mainComposite, SWT.NONE);
		kalenderComposite.setBackground(theme.getWhiteColor());
		kalenderComposite.setLayout(new GridLayout(3, true));

		uebersichtComposite = new Composite(mainComposite, SWT.NONE);
		uebersichtComposite.setBackground(theme.getWhiteColor());
		uebersichtComposite.setLayout(new GridLayout(3, true));
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

	private void anpassenAnzahlFillerLabel() {
		if (display.getBounds().width < 1870) {
			createFillerLabel(mainComposite, 2);
		} else {
			createFillerLabel(mainComposite, 4);
		}
	}

	public void uebergebeSpaltenanzahl() {
		setzeSpaltenVonFluidGrudData(eingabe1Composite, 4, 4, 8, 4);
		setzeSpaltenVonFluidGrudData(eingabe2Composite, 4, 4, 8, 4);
		setzeSpaltenVonFluidGrudData(kalenderComposite, 4, 4, 8, 4);
		setzeSpaltenVonFluidGrudData(uebersichtComposite, 4, 4, 8, 4);
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

	private void erzeugeObereWidgets() {

		Label lehrerLabel = new Label(eingabe1Composite, SWT.NONE);
		lehrerLabel.setText("Fahrlehrer");
		lehrerLabel.setFont(theme.getFont1());
		lehrerLabel.setBackground(theme.getWhiteColor());
		lehrerLabel.setLayoutData(createFillFillTrueFalseGridData());

		SelectionListener selectionListenerFahrlehrerCombo = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				setChanged();
				notifyObservers("Fahrlehrer");
			};
		};

		lehrerCombo = new Combo(eingabe1Composite, SWT.READ_ONLY);
		lehrerCombo.addSelectionListener(selectionListenerFahrlehrerCombo);
		lehrerCombo.setLayoutData(createFillFillTrueFalseGridData());
		lehrerCombo.setFont(theme.getFont2());

		new Label(eingabe1Composite, SWT.NONE);

		Label datumLabel = new Label(eingabe1Composite, SWT.NONE);
		datumLabel.setText("Datum:");
		datumLabel.setFont(theme.getFont1());
		datumLabel.setBackground(theme.getWhiteColor());
		datumLabel.setLayoutData(createFillFillTrueFalseGridData());

		dateFahrstunde = new DateTime(eingabe1Composite, SWT.DATE);
		dateFahrstunde.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setChanged();
				notifyObservers("Datum");
			}
		});

		createFillerLabel(eingabe1Composite, 8);
		erzeugeLinie(eingabe1Composite);

		Label schuelerLabel = new Label(eingabe2Composite, SWT.NONE);
		schuelerLabel.setText("Fahrschüler");
		schuelerLabel.setFont(theme.getFont1());
		schuelerLabel.setBackground(theme.getWhiteColor());
		schuelerLabel.setLayoutData(createFillFillTrueFalseGridData());

		SelectionListener selectionListenerSchuelerCombo = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				setChanged();
				notifyObservers("Fahrschueler");
			};
		};

		schuelerCombo = new Combo(eingabe2Composite, SWT.READ_ONLY);
		schuelerCombo.addSelectionListener(selectionListenerSchuelerCombo);
		schuelerCombo.setLayoutData(createFillFillTrueFalseGridData());
		schuelerCombo.setFont(theme.getFont2());

		new Label(eingabe2Composite, SWT.NONE);

		Label fsKlasseLabel = new Label(eingabe2Composite, SWT.NONE);
		fsKlasseLabel.setText("Führerscheinklasse:");
		fsKlasseLabel.setFont(theme.getFont1());
		fsKlasseLabel.setBackground(theme.getWhiteColor());
		fsKlasseLabel.setLayoutData(createFillFillTrueFalseGridData());

		SelectionListener selectionListenerZeitCombo = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				setChanged();
				notifyObservers("Führerscheinklasse");
			};
		};

		fsKlasseCombo = new Combo(eingabe2Composite, SWT.READ_ONLY);
		fsKlasseCombo.addSelectionListener(selectionListenerZeitCombo);
		fsKlasseCombo.setLayoutData(createFillFillTrueFalseGridData());

		createFillerLabel(eingabe2Composite, 8);
		erzeugeLinie(eingabe2Composite);
	}

	private GridData createFillFillTrueFalseGridData() {
		return new GridData(SWT.FILL, SWT.FILL, true, false);
	}

	private void createFillerLabel(Composite parent, int anzahl) {
		for (int i = 0; i < anzahl; i++) {
			Label l = new Label(parent, SWT.NONE);
			l.setBackground(theme.getWhiteColor());
		}
	}

	private void erzeugeUntereLinkeWidgets() {

		createFillerLabel(kalenderComposite, 4);

		Label ueberschrift = new Label(kalenderComposite, SWT.NONE | SWT.WRAP);
		ueberschrift.setText("Offene Stunden von Fahrlehrer: ");
		ueberschrift.setLayoutData(createFillFillTrueFalseGridData());
		ueberschrift.setFont(theme.getFont1());
		ueberschrift.setBackground(theme.getWhiteColor());

		createFillerLabel(kalenderComposite, 2);

		lehrernameLabel = new Label(kalenderComposite, SWT.NONE | SWT.WRAP);
		lehrernameLabel.setText("Fahrlehrername");
		lehrernameLabel.setLayoutData(createFillFillTrueFalseGridData());
		lehrernameLabel.setFont(theme.getNamefont());
		lehrernameLabel.setBackground(theme.getWhiteColor());
		lehrernameLabel.setForeground(theme.getBlueColor());

		createFillerLabel(kalenderComposite, 7);

		Label zeitLabel = new Label(kalenderComposite, SWT.NONE);
		zeitLabel.setText("Uhrzeit");
		zeitLabel.setBackground(theme.getWhiteColor());
		zeitLabel.setFont(theme.getFont2());
		zeitLabel.setLayoutData(createFillFillTrueFalseGridData());

		timeCombo = new Combo(kalenderComposite, SWT.READ_ONLY);
		timeCombo.setLayoutData(createFillFillTrueFalseGridData());
		timeCombo.setFont(theme.getFont2());
		timeCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setChanged();
				notifyObservers("BuchungsZeit");
			}
		});

		createFillerLabel(kalenderComposite, 1);

		Label artLabel = new Label(kalenderComposite, SWT.NONE);
		artLabel.setText("Art der Fahrstunde:");
		artLabel.setBackground(theme.getWhiteColor());
		artLabel.setFont(theme.getFont2());
		artLabel.setLayoutData(createFillFillTrueFalseGridData());

		SelectionListener selectionListenerArtCombo = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				setChanged();
				notifyObservers("Führerscheinklasse");
			};
		};

		artCombo = new Combo(kalenderComposite, SWT.READ_ONLY);
		artCombo.addSelectionListener(selectionListenerArtCombo);
		artCombo.setLayoutData(createFillFillTrueFalseGridData());
		artCombo.setFont(theme.getFont2());

		createFillerLabel(kalenderComposite, 8);

		btBuchen = new CLabel(kalenderComposite, SWT.BORDER);
		btBuchen.setText("Fahrstunde \n buchen");
		btBuchen.setTextDirection(SWT.CENTER);
		btBuchen.setFont(theme.getFont2());
		btBuchen.setBackground(theme.getBlueColor());
		btBuchen.setForeground(theme.getWhiteColor());
		btBuchen.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
				btBuchen.setBackground(new Color(display, 0, 0, 255));
				setChanged();
				notifyObservers("Buchen");
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				btBuchen.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) { // not needed
			}
		});

		btBuchen.addMouseTrackListener(new MouseTrackListener() {

			@Override
			public void mouseHover(MouseEvent arg0) { // not needed
			}

			@Override
			public void mouseExit(MouseEvent arg0) {
				btBuchen.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseEnter(MouseEvent arg0) {
				btBuchen.setBackground(new Color(display, 125, 125, 255));
			}
		});

	}

	private void erzeugeUntereRechteWidgets() {

		createFillerLabel(uebersichtComposite, 4);

		Label ueberschrift = new Label(uebersichtComposite, SWT.NONE | SWT.WRAP);
		ueberschrift.setText("Übersicht der Stunden von Schüler:");
		ueberschrift.setLayoutData(createFillFillTrueFalseGridData());
		ueberschrift.setFont(theme.getFont1());
		ueberschrift.setBackground(theme.getWhiteColor());

		createFillerLabel(uebersichtComposite, 2);

		schuelernameLabel = new Label(uebersichtComposite, SWT.NONE | SWT.WRAP);
		schuelernameLabel.setText("Fahrschuelername");
		schuelernameLabel.setLayoutData(createFillFillTrueFalseGridData());
		schuelernameLabel.setFont(theme.getNamefont());
		schuelernameLabel.setBackground(theme.getWhiteColor());
		schuelernameLabel.setForeground(theme.getBlueColor());

		createFillerLabel(uebersichtComposite, 7);

		Label fahrstundeLabel = new Label(uebersichtComposite, SWT.NONE | SWT.WRAP);
		fahrstundeLabel.setText("Fahrstunden:");
		fahrstundeLabel.setBackground(theme.getWhiteColor());
		fahrstundeLabel.setFont(theme.getFont2());

		fahrstundeTxt = new Text(uebersichtComposite, SWT.BORDER);
		fahrstundeTxt.setEnabled(false);

		createFillerLabel(uebersichtComposite, 1);

		Label sonderfahrtLabel = new Label(uebersichtComposite, SWT.NONE);
		sonderfahrtLabel.setText("Sonderfahrten:");
		sonderfahrtLabel.setBackground(theme.getWhiteColor());
		sonderfahrtLabel.setFont(theme.getFont2());

		sonderfahrtTxt = new Text(uebersichtComposite, SWT.BORDER);
		sonderfahrtTxt.setEnabled(false);

		createFillerLabel(uebersichtComposite, 1);

		Label theorieLabel = new Label(uebersichtComposite, SWT.NONE | SWT.WRAP);
		theorieLabel.setText("Theoriestunden:");
		theorieLabel.setBackground(theme.getWhiteColor());
		theorieLabel.setFont(theme.getFont2());

		theorieTxt = new Text(uebersichtComposite, SWT.BORDER);
		theorieTxt.setEnabled(false);

		createFillerLabel(uebersichtComposite, 8);

		btRechnung = new CLabel(uebersichtComposite, SWT.BORDER);
		btRechnung.setText("Rechnungsübersicht \n erstellen");
		btRechnung.setTextDirection(SWT.CENTER);
		btRechnung.setFont(theme.getFont2());
		btRechnung.setBackground(theme.getBlueColor());
		btRechnung.setForeground(theme.getWhiteColor());
		btRechnung.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
				btRechnung.setBackground(new Color(display, 0, 0, 255));
				setChanged();
				notifyObservers("Rechnung");
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				btRechnung.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) { // not needed
			}
		});

		btRechnung.addMouseTrackListener(new MouseTrackListener() {

			@Override
			public void mouseHover(MouseEvent arg0) { // not needed
			}

			@Override
			public void mouseExit(MouseEvent arg0) {
				btRechnung.setBackground(theme.getBlueColor());
			}

			@Override
			public void mouseEnter(MouseEvent arg0) {
				btRechnung.setBackground(new Color(display, 125, 125, 255));
			}
		});
		createFillerLabel(uebersichtComposite, 8);
	}

	public Label getSchuelernameLabel() {
		return schuelernameLabel;
	}

	public Label getLehrernameLabel() {
		return lehrernameLabel;
	}

	public void erzeugeLinie(Composite composite) {
		Label trennstrichLabel = new Label(composite, SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
		gridData.heightHint = 1;
		gridData.horizontalSpan = 5;
		trennstrichLabel.setLayoutData(gridData);
	}

	public void startEventHandler() {
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public Display getDisplay() {
		return display;
	}

	public Shell getShell() {
		return shell;
	}

	public CLabel getBtBuchen() {
		return btBuchen;
	}

	public CLabel getBtRechnung() {
		return btRechnung;
	}

	public DateTime getDateFahrstunde() {
		return dateFahrstunde;
	}

	public Combo getLehrerCombo() {
		return lehrerCombo;
	}

	public Combo getSchuelerCombo() {
		return schuelerCombo;
	}

	public Combo getFsKlasseCombo() {
		return fsKlasseCombo;
	}

	public Combo getArtCombo() {
		return artCombo;
	}

	public Combo getTimeCombo() {
		return timeCombo;
	}

	public Text getFahrstundeTxt() {
		return fahrstundeTxt;
	}

	public Text getSonderfahrtTxt() {
		return sonderfahrtTxt;
	}

	public Text getTheorieTxt() {
		return theorieTxt;
	}

	public FahrschulTheme getTheme() {
		return theme;
	}

}
