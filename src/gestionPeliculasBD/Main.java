package gestionPeliculasBD;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		// We create a Scanner object to read data from the console
		Scanner teclado = new Scanner(System.in);
		// We create an instance of PeliculasDAO to handle database operations
		PeliculasDAO dao = new PeliculasDAO();
		int opcion = 0; // Variable to store the option chosen by the user

		while (opcion != 6) { // If it's not 6, it repeats

			System.out.println("--- MENU ---");
			System.out.println("1. New movie");
			System.out.println("2. Change duration");
			System.out.println("3. Delete movie");
			System.out.println("4. View all");
			System.out.println("5. Search by genre");
			System.out.println("6. Exit");
			System.out.print("Choose an option: ");

			opcion = teclado.nextInt();
			teclado.nextLine(); // We clear the buffer

			if (opcion == 1) {
				System.out.print("Title: ");
				String titulo = teclado.nextLine();
				System.out.print("Director: ");
				String director = teclado.nextLine();
				System.out.print("Genre: ");
				String genero = teclado.nextLine();
				System.out.print("Year: ");
				int año = teclado.nextInt();
				System.out.print("Duration: ");
				int duracion = teclado.nextInt();

				// We call the method to insert a movie
				dao.insertarPelicula(titulo, director, genero, año, duracion);

			} else if (opcion == 2) {
				// CHANGE DURATION
				System.out.print("Title of the movie you would like to change: ");
				String titulo = teclado.nextLine();
				System.out.print("New duration you would like to set: ");
				int duracion = teclado.nextInt();

				// We call the method to update the duration
				dao.actualizarDuracion(titulo, duracion);

			} else if (opcion == 3) {
				// DELETE MOVIE
				System.out.print("Title you would like to delete: ");
				String titulo = teclado.nextLine();

				// We call the DELETE MOVIE method
				dao.eliminarPelicula(titulo);

			} else if (opcion == 4) {
				// LIST MOVIES
				dao.listarTodasLasPeliculas(); // We call the LIST MOVIES method

			} else if (opcion == 5) {
				// Option to search movies by genre
				System.out.print("Type the genre you are looking for: ");
				String genero = teclado.nextLine(); // We read the requested genre

				// We call the LIST BY GENRE method
				dao.listarPorGenero(genero);

			} else if (opcion == 6) {
				// EXIT
				System.out.println("Exiting...");

			} else {
				System.out.println("Please insert a number from 1 to 6");
			}
		}
		teclado.close(); // We close the scanner to free resources. IMPORTANT!!
	}
}
