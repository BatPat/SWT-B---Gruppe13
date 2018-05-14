
package Oberflaeche;

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
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
	
public class MainView extends Observable {

		private GridLayout layout;
		private Display display;
		private Shell shell;
		private Menu menu, submenu;
		private MenuItem menuitem;
		private GridData dataGrunddaten, kalender, dataUebersicht;
		private CLabel btBuchen, btRechnung;
		private DateTime dateFahrstunde;
		private Font font1, font2;
		private Combo lehrerCombo, schuelerCombo, zeitCombo;
		private Text nachtfahrtTxt, autobahnTxt, ueberlandTxt, fahrstundeTxt, theorieTxt;
		private Color whiteColor;
		
		public MainView() {
			initUI();
		}

		private void initUI() {
			display = new Display();
			shell = new Shell(display);
			whiteColor = new Color(display, 255, 255, 255);
			shell.setBackground(whiteColor);
			font1 = new Font(display, "Sans-Serif", 13, SWT.NORMAL);
			font2 = new Font(display, "Sans-Serif", 11, SWT.NORMAL);
			shell.setFont(font1);
			
			layout = new GridLayout(5, false);
			shell.setLayout(layout);

			shell.setText("Fahrschul Verwaltung");
			

			erzeugeMenu();
			
			// Widgets fuer die Shell erzeugen
			erzeugeObereWidgets();
			new Label(shell, SWT.NONE);
			erzeugeUntereLinkeWidgets();
			new Label(shell, SWT.NONE);
			erzeugeUntereRechteWidgets();

			shell.pack();
			shell.open();
		}

		private void erzeugeObereWidgets() {

			SelectionListener selectionListenerCombo = new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent event) {
					setChanged();
					notifyObservers(null); //null ersetzen
				};
			};
			
			Label lehrerLabel = new Label(shell, SWT.NONE);
			lehrerLabel.setText("Fahrlehrer");
			lehrerLabel.setFont(font1);
			lehrerLabel.setBackground(whiteColor);
			
			lehrerCombo = new Combo(shell, SWT.READ_ONLY);
			lehrerCombo.addSelectionListener(selectionListenerCombo);
			lehrerCombo.setLayoutData(createFillFillTrueFalseGridData());
			lehrerCombo.setFont(font2);
			
			new Label(shell, SWT.NONE);

			Label schuelerLabel = new Label(shell, SWT.NONE);
			schuelerLabel.setText("Fahrschüler");
			schuelerLabel.setFont(font1);
			schuelerLabel.setBackground(whiteColor);
			
			schuelerCombo = new Combo(shell, SWT.READ_ONLY);
			schuelerCombo.addSelectionListener(selectionListenerCombo);
			schuelerCombo.setLayoutData(createFillFillTrueFalseGridData());
			schuelerCombo.setFont(font2);


			Label datumLabel = new Label(shell, SWT.NONE);
			datumLabel.setText("Datum:");
			datumLabel.setFont(font1);
			datumLabel.setBackground(whiteColor);
			
			dateFahrstunde = new DateTime(shell, SWT.DATE);
			dateFahrstunde.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					setChanged();
					notifyObservers(null); //null ersetzen
				}
			});

			new Label(shell, SWT.NONE);
	
			Label zeitLabel  = new Label(shell, SWT.NONE);
			zeitLabel.setText("Uhrzeit:");
			zeitLabel.setFont(font1);
			zeitLabel.setBackground(whiteColor);
			
			zeitCombo = new Combo(shell, SWT.READ_ONLY);
			zeitCombo.addSelectionListener(selectionListenerCombo);

			erzeugeLinie();

		}

		private GridData createFillFillTrueFalseGridData() {
			return new GridData(SWT.FILL, SWT.FILL, true, false);
		}

		private void erzeugeUntereLinkeWidgets() {
			
			Composite calendar = new Composite(shell, SWT.NONE);
			calendar.setBackground(whiteColor);
			
		    calendar.setLayout(new GridLayout(3, false));
		    
		    new Label(calendar, SWT.None);
		    
		    DateTime zeitplan = new DateTime(calendar, SWT.CALENDAR);
		    zeitplan.setFont(font1);
		    
		    new Label(calendar, SWT.None);
		    new Label(calendar, SWT.None);
		    
		    Label zeitLabel = new Label(calendar, SWT.NONE);
		    zeitLabel.setText("Uhrzeit");
		    zeitLabel.setBackground(whiteColor);
		    zeitLabel.setFont(font2);
		    
		    DateTime time = new DateTime(calendar, SWT.TIME);
		    time.setFont(font2);
		    
		    new Label(calendar, SWT.None);
		    new Label(calendar, SWT.None);
		    
		    btBuchen = new CLabel(calendar, SWT.BORDER);
		    btBuchen.setText("Fahrstunde \n buchen");
		    btBuchen.setTextDirection(SWT.CENTER);
		    btBuchen.setFont(font2);
		    btBuchen.setLayoutData(createFillFillTrueFalseGridData());
		    btBuchen.setBackground(new Color(display, 25, 61, 138));
			btBuchen.setForeground(whiteColor);
			btBuchen.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent arg0) {
					btBuchen.setBackground(new Color(display, 0, 0, 255));
				}

				@Override
				public void mouseDown(MouseEvent arg0) {
					btBuchen.setBackground(new Color(display, 25, 61, 138));
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent arg0) { //not needed
				}
			});

			btBuchen.addMouseTrackListener(new MouseTrackListener() {

				@Override
				public void mouseHover(MouseEvent arg0) {	//not needed
				} 

				@Override
				public void mouseExit(MouseEvent arg0) {
					btBuchen.setBackground(new Color(display, 25, 61, 138));
				}

				@Override
				public void mouseEnter(MouseEvent arg0) {
					btBuchen.setBackground(new Color(display, 125, 125, 255));
				}
			});
			
		}

		private void erzeugeUntereRechteWidgets() {
			dataUebersicht = new GridData(SWT.RIGHT, SWT.TOP, false, false, 2, 6);
			
			Composite compositeUebersicht = new Composite(shell, SWT.BORDER);
			compositeUebersicht.setLayoutData(dataUebersicht);
			compositeUebersicht.setLayout(new GridLayout(2, false));
			compositeUebersicht.setBackground(whiteColor);

			Label ueberschrift = new Label(compositeUebersicht, SWT.NONE | SWT.WRAP);
			ueberschrift.setText("Übersicht der Stunden vom Schüler");
			ueberschrift.setLayoutData(createFillFillTrueFalseGridData());
			ueberschrift.setFont(font1);
			ueberschrift.setBackground(whiteColor);
			
			new Label(compositeUebersicht, SWT.None);
			new Label(compositeUebersicht, SWT.None);
			new Label(compositeUebersicht, SWT.None);
			
			
			Label nachtLabel = new Label(compositeUebersicht, SWT.NONE | SWT.WRAP);
			nachtLabel.setText("Nachtfahrt:");
			nachtLabel.setBackground(whiteColor);
			nachtLabel.setFont(font2);

			nachtfahrtTxt = new Text(compositeUebersicht, SWT.BORDER);
			nachtfahrtTxt.setEnabled(false);

			Label autobahnLabel = new Label(compositeUebersicht, SWT.NONE);
			autobahnLabel.setText("Autobahnfahrt:");
			autobahnLabel.setBackground(whiteColor);
			autobahnLabel.setFont(font2);
			
			autobahnTxt = new Text(compositeUebersicht, SWT.BORDER);
			autobahnTxt.setEnabled(false);

			Label ueberlandLabel = new Label(compositeUebersicht, SWT.NONE | SWT.WRAP);
			ueberlandLabel.setText("Überlandfahrt:");
			ueberlandLabel.setBackground(whiteColor);
			ueberlandLabel.setFont(font2);

			ueberlandTxt = new Text(compositeUebersicht, SWT.BORDER);
			ueberlandTxt.setEnabled(false);
			
			Label theorieLabel = new Label(compositeUebersicht, SWT.NONE | SWT.WRAP);
			theorieLabel.setText("Theoriestunden");
			theorieLabel.setBackground(whiteColor);
			theorieLabel.setFont(font2);
			
			theorieTxt = new Text(compositeUebersicht, SWT.BORDER);
			theorieTxt.setEnabled(false);
			
			new Label(compositeUebersicht, SWT.NONE);
			new Label(compositeUebersicht, SWT.None);
			new Label(compositeUebersicht, SWT.None);
			
			btRechnung = new CLabel(compositeUebersicht, SWT.BORDER);
			btRechnung.setText("Rechnungsübersicht \n erstellen");
			btRechnung.setTextDirection(SWT.CENTER);
			btRechnung.setFont(font2);
			btRechnung.setLayoutData(createFillFillTrueFalseGridData());
			btRechnung.setBackground(new Color(display, 25, 61, 138));
			btRechnung.setForeground(whiteColor);
			btRechnung.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent arg0) {
					btRechnung.setBackground(new Color(display, 0, 0, 255));
				}

				@Override
				public void mouseDown(MouseEvent arg0) {
					btRechnung.setBackground(new Color(display, 25, 61, 138));
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent arg0) { //not needed
				}
			});

			btRechnung.addMouseTrackListener(new MouseTrackListener() {

				@Override
				public void mouseHover(MouseEvent arg0) {	//not needed
				} 

				@Override
				public void mouseExit(MouseEvent arg0) {
					btRechnung.setBackground(new Color(display, 25, 61, 138));
				}

				@Override
				public void mouseEnter(MouseEvent arg0) {
					btRechnung.setBackground(new Color(display, 125, 125, 255));
				}
			});
		}
		
		public void erzeugeLinie() {
			Label hrLabel = new Label(shell, SWT.BORDER);
			GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
			gridData.heightHint = 1;
			gridData.horizontalSpan = 5;
			hrLabel.setLayoutData(gridData);
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

	public GridLayout getLayout() {
			return layout;
		}

		public Display getDisplay() {
			return display;
		}

		public Shell getShell() {
			return shell;
		}

		public Menu getMenu() {
			return menu;
		}

		public Menu getSubmenu() {
			return submenu;
		}

		public MenuItem getMenuitem() {
			return menuitem;
		}

		public GridData getDataGrunddaten() {
			return dataGrunddaten;
		}

		public GridData getKalender() {
			return kalender;
		}

		public GridData getDataUebersicht() {
			return dataUebersicht;
		}

		public CLabel getBtKalender() {
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

		public Combo getZeitCombo() {
			return zeitCombo;
		}

		public Text getNachtfahrtTxt() {
			return nachtfahrtTxt;
		}

		public Text getAutobahnTxt() {
			return autobahnTxt;
		}

		public Text getUeberlandTxt() {
			return ueberlandTxt;
		}

		public Text getFahrstundeTxt() {
			return fahrstundeTxt;
		}

		public Text getTheorieTxt() {
			return theorieTxt;
		}

	public static void main(String[] args) {
		MainView gui = new MainView();
		gui.startEventHandler();
	}

}

