package gestionPeliculasBD;

import java.sql.Connection;
import java.sql.DriverManager;

// THIS CLASS IS USED TO CONTROL THE DATABASE CONNECTION. IMPORTANT!! 
public class ConnectionDB {

	String url = "jdbc:mysql://localhost/peliculas"; // DATABASE URL
	String user = "user";
	String pass = "P1234";
	Connection con; // Variable TO STORE THE CONNECTION

	// Method to open the database connection
	public Connection openConnection() {
		try {
			// We try to establish the connection using DriverManager
			con = DriverManager.getConnection(url, user, pass);
			return con; // IF SUCCESSFUL, THE CONNECTION IS STORED HERE
		} catch (Exception e) {
			// We catch any error that occurs when trying to connect. VERY IMPORTANT NOT TO
			// FORGET
			System.out.println("Error connecting: " + e.getMessage());
			return null; // We return null if there is an error
		}
	}

	// Method to close the database connection
	public void closeConnection() {
		try {
			// We verify if the connection is open before trying to close it
			if (con != null) {
				con.close(); // We close the connection to save resources
			}
		} catch (Exception e) {
			// We catch any error that occurs when trying to close the connection. VERY
			// IMPORTANT NOT TO FORGET
			System.out.println("Error closing: " + e.getMessage());
		}
	}
}