package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skilldistillery.film.entities.Film;


@Repository
public class FilmDAOImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PWD = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			return film;
		}
		
		try {
			Connection connection = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT film.*, language.name AS language_name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, filmId);
			ResultSet filmResult = statement.executeQuery();
			if (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));

//			film.setActors(findActorsByFilmId(filmId));
//
//			List<String> categories = findFilmCategories(filmId);
//			film.setCategories(categories);
//
//			film.setLanguage(filmResult.getString("language_name"));
//
//			List<InventoryItem> inventoryItems = findInventoryByFilmId(filmId);
				//film.setInventoryItems(inventoryItems);
			}
			filmResult.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public boolean save(Film film) {
		boolean success = false;
		// Code to connect to the database
		// Determine if this is an insert (new film) or an update (existing film)
		// Execute an INSERT or UPDATE statement
		// Set success to true if the operation is successful
		return success;
	}

}
