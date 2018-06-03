package fachlogik;

import java.sql.*;



public class Main {

	public static void main(String[] args) {
//		 new Controller();
//		String jdbcurl = "jdbc:mysql://localhost:3306/fahrschule?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//		String user = "fahrschule";
//		String pw = "fahrschule";
//		try {
//			Connection con = DriverManager.getConnection(jdbcurl, user, pw);
//			System.out.println("Connection Successfull");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		Fahrlehrer f = new Fahrlehrer("Stefan Terlau", "44723", "Dortmund", "Kaspergaeschen", "3");
		HibernateUtil h = new HibernateUtil();
		h.saveObject(f);
	}
}
