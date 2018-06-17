package fachlogik;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Shell;

import oberflaeche.PersonAnlegenDialog;

public class PersonAnlegenController{
	
	private AbstractPersonFactory factory;
	private PersonType personType;
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
	
	
	public PersonAnlegenController(PersonType personType, Shell shell) {
		factory = new PersonFactory();
		this.personType = personType;
		initGUI(shell);
	}

	private void initGUI(Shell shell) {
		dialog = new PersonAnlegenDialog(shell);
		dialog.create();
		createDialogListener();
		if (dialog.open() == Window.OK) {
			personAnlegen();
		}
	}


	private void personAnlegen() {
		
		Person person = factory.createPerson(personType, vorname + " " + nachname, plz, ort, strasse, hausNr, telefonnummer, geburtsdatum, fuehrerscheinklasse);
		
		//TODO Person abspeichern
		System.out.println("neuer " + personType.name() + ": " + person.getName());
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
			public void focusGained(FocusEvent e) {}
		});
		dialog.getOrtInput().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				ort = dialog.getOrtInput().getText();
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		dialog.getStrInput().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				strasse = dialog.getStrInput().getText();
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		dialog.getHausnrInput().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				hausNr = dialog.getHausnrInput().getText();
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		dialog.getTelInput().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				telefonnummer = dialog.getTelInput().getText();
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		dialog.getFsklasseInput().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				fuehrerscheinklasse = dialog.getFsklasseInput().getText();
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
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
	}
}
