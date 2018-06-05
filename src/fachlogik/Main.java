package fachlogik;

public class Main {

	public static void main(String[] args) {
//    	 new Controller();
	    FahrlehrerDTO f = new FahrlehrerDTO("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3");
		HibernateUtil h = new HibernateUtil();
		h.saveObject(f);
//		h.getObject(f);
	}
}
