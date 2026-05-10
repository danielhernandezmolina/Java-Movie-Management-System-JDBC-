package gestionPeliculasBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// DAO (Data Access Object) class to make queries to the database
public class PeliculasDAO {

	// We instantiate the connection class to be able to use it in all methods
	ConnectionDB bd = new ConnectionDB();

	// Method to register a new movie in the table
	public void insertarPelicula(String titulo, String director, String genero, int anio, int duracion) {
		try {
			// We open the connection
			Connection c = bd.openConnection();

			// SQL query with question marks to pass the data later
			String sql = "INSERT INTO catalogo (Titulo, director, Genero, anio_estreno, duracion_minutos) VALUES (?, ?, ?, ?, ?)";

			// We use PreparedStatement because we pass variables to the query
			PreparedStatement p = c.prepareStatement(sql);

			// We substitute the question marks with the variables in order
			p.setString(1, titulo);
			p.setString(2, director);
			p.setString(3, genero);
			p.setInt(4, anio);
			p.setInt(5, duracion);

			// executeUpdate is used for INSERT, UPDATE, and DELETE
			p.executeUpdate();
			System.out.println("Movie saved successfully!");

			// IMPORTANT: always close the connection when finished
			bd.closeConnection();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// Method to modify the minutes of a specific movie
	public void actualizarDuracion(String titulo, int nuevaDuracion) {
		try {
			Connection c = bd.openConnection();
			String sql = "UPDATE catalogo SET duracion_minutos = ? WHERE Titulo = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, nuevaDuracion);
			p.setString(2, titulo);

			// We save the result in an int. If it is greater than 0, it means it has
			// modified something
			int filas = p.executeUpdate();
			if (filas > 0) {
				System.out.println("Duration changed successfully");
			} else {
				// If it returns 0, it means the title was not found
				System.out.println("The movie does not exist");
			}
			bd.closeConnection();

		} catch (Exception e) {
			System.out.println("Failed to update: " + e.getMessage());
		}
	}

	// Method to delete an entire record by searching for the title
	public void eliminarPelicula(String titulo) {
		try {
			Connection c = bd.openConnection();
			String sql = "DELETE FROM catalogo WHERE Titulo = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, titulo);

			// Just like in the update, we check if any row was deleted
			int filas = p.executeUpdate();
			if (filas > 0) {
				System.out.println("Movie deleted successfully");
			} else {
				System.out.println("The movie was not found.");
			}
			bd.closeConnection();

		} catch (Exception e) {
			System.out.println("Failed to delete: " + e.getMessage());
		}
	}

	// Method that shows the entire catalog
	public void listarTodasLasPeliculas() {
		try {
			Connection c = bd.openConnection();

			// Here we use a normal Statement because the query does not have variables (?)
			Statement s = c.createStatement();

			// SELECT queries use executeQuery and return a ResultSet
			ResultSet rs = s.executeQuery("SELECT * FROM catalogo");

			System.out.println("--- MOVIE LIST ---");

			// The while loop iterates row by row as long as there is data
			while (rs.next()) {
				// We extract the data specifying their type and the exact column name in the DB
				System.out.println(rs.getInt("pelicula_id") + " - " + rs.getString("Titulo") + " - "
						+ rs.getString("director") + " - " + rs.getString("Genero") + " - " + rs.getInt("anio_estreno")
						+ " - " + rs.getInt("duracion_minutos") + " min");
			}
			bd.closeConnection();

		} catch (Exception e) {
			System.out.println("Error listing: " + e.getMessage());
		}
	}

	// Method to filter movies by the genre introduced by the user
	public void listarPorGenero(String genero) {
		try {
			Connection c = bd.openConnection();
			String sql = "SELECT * FROM catalogo WHERE Genero = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, genero);

			ResultSet rs = p.executeQuery();

			System.out.println("--- " + genero.toUpperCase() + " MOVIES ---");
			while (rs.next()) {
				System.out.println("- " + rs.getString("Titulo") + " (" + rs.getInt("anio_estreno") + ")");
			}
			bd.closeConnection();

		} catch (Exception e) {
			System.out.println("Error searching genre: " + e.getMessage());
		}
	}
}
