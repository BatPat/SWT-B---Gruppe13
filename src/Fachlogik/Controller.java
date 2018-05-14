package Fachlogik;

import java.util.Observer;

import Oberflaeche.MainView;

public class Controller {

	
	public FahrschulModel model;

	public Controller() {
		initModel();
		initGUI();
	}

	private void initGUI() {
		 MainView gui = new MainView();
		 gui.addObserver((Observer) this);
	}

	private void initModel() {
		model = new FahrschulModel();
	}

	private void fillListContent()
	  {
	  //Komboboxen füllen
	  }
	
	 private void updateModel()
	  {
		 //rechte seite fahrstunden anzeigen
	  }
	 


}
