package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skilldistillery.film.entities.Actor;
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

				film.setActors(findActorsByFilmId(filmId));

				List<String> categories = findFilmCategories(filmId);
				film.setCategories(categories);

				film.setLanguage(filmResult.getString("language_name"));
//
//			List<InventoryItem> inventoryItems = findInventoryByFilmId(filmId);
				// film.setInventoryItems(inventoryItems);
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
	public List<Film> findFilmByKeyword(String userKeyword) {
		List<Film> foundFilms = new ArrayList<Film>();
		String user = "student";
		String pass = "student";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT * " + " FROM film JOIN language ON film.language_id = language.id "
					+ " WHERE film.title LIKE ? OR film.description LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			String userQuery = "%" + userKeyword + "%";
			ps.setString(1, userQuery);
			ps.setString(2, userQuery);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Film film = new Film();

				film.setId(resultSet.getInt("id"));
				film.setTitle(resultSet.getString("title"));
				film.setDescription(resultSet.getString("description"));
				film.setReleaseYear(resultSet.getInt("release_year"));
				film.setLanguageId(resultSet.getInt("language_id"));
				film.setRentalDuration(resultSet.getInt("rental_duration"));
				film.setRentalRate(resultSet.getDouble("rental_rate"));
				film.setLength(resultSet.getInt("length"));
				film.setReplacementCost(resultSet.getDouble("replacement_cost"));
				film.setRating(resultSet.getString("rating"));
				film.setSpecialFeatures(resultSet.getString("special_features"));

				 //film.setActors(findActorById(film.getId()));

				foundFilms.add(film);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return foundFilms;

	}
	
	public List<String> findFilmCategories(int filmId) throws SQLException {
		List<String> categories = new ArrayList<>();
		Connection connection = DriverManager.getConnection(URL, USER, PWD);
		String sql = "SELECT category.name FROM category JOIN film_category ON category.id = film_category.category_id WHERE film_category.film_id = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, filmId);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			categories.add(resultSet.getString("name"));
		}
		resultSet.close();
		statement.close();
		connection.close();

		return categories;
	}

	@Override
	public boolean updateFilm(Film film) {
		Connection conn = null;
		String user = "student";
		String pass = "student";

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ? WHERE id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, film.getTitle());
			statement.setString(2, film.getDescription());
			if (film.getReleaseYear() != null) {
				statement.setInt(3, film.getReleaseYear());
			} else {
				statement.setNull(3, java.sql.Types.INTEGER);
			}
			statement.setInt(4, film.getLanguageId());
			statement.setInt(5, film.getRentalDuration());
			statement.setDouble(6, film.getRentalRate());
			if (film.getLength() != null) {
				statement.setInt(7, film.getLength());
			} else {
				statement.setNull(7, java.sql.Types.INTEGER);
			}
			statement.setDouble(8, film.getReplacementCost());
			statement.setString(9, film.getRating());
			statement.setString(10, film.getSpecialFeatures());
			statement.setInt(11, film.getId());
			int updateCount = statement.executeUpdate();
			if (updateCount == 1) {
				conn.commit();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;

	}
	
	@Override
    public boolean deleteFilm(int filmId) throws SQLException {
        String sql = "DELETE FROM film WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PWD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, filmId);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }
	
	

	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;

		Connection connection = DriverManager.getConnection(URL, USER, PWD);

		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, actorId);
		ResultSet actorResult = statement.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
					actorResult.getString("last_name"));
		}
		actorResult.close();
		statement.close();
		connection.close();
		return actor;
	}
	
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<>();

		Connection connection = DriverManager.getConnection(URL, USER, PWD);

		String sql = "SELECT actor.id, actor.first_name, actor.last_name "
				+ "FROM actor JOIN film_actor ON actor.id = film_actor.actor_id " + "WHERE film_actor.film_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, filmId);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			Actor actor = new Actor(resultSet.getInt("id"), resultSet.getString("first_name"),
					resultSet.getString("last_name"));
			actors.add(actor);
		}
		resultSet.close();
		statement.close();
		connection.close();
		return actors;
	}
	
	public Film createFilm(Film film) throws SQLException {
		String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) VALUES (?, ?, ?, 1, ?, ?, ?, ?, ?, ?)";

		try (Connection connnnection = DriverManager.getConnection(URL, USER, PWD);
				PreparedStatement statement = connnnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, film.getTitle());

			if (film.getDescription() != null) {
				statement.setString(2, film.getDescription());
			} else {
				statement.setNull(2, java.sql.Types.VARCHAR);
			}

			if (film.getReleaseYear() != null) {
				statement.setInt(3, film.getReleaseYear());
			} else {
				statement.setNull(3, java.sql.Types.INTEGER);
			}

			
			statement.setInt(4, film.getRentalDuration());
			statement.setDouble(5, film.getRentalRate());


			if (film.getLength() != null) {
				statement.setInt(6, film.getLength()); 
			} else {
				statement.setNull(6, java.sql.Types.INTEGER);
			}
			statement.setDouble(7, film.getReplacementCost());

			if (film.getRating() != null) {
				statement.setString(8, film.getRating());
			} else {
				statement.setNull(8, java.sql.Types.VARCHAR);
			}

			if (film.getSpecialFeatures() != null) {
				statement.setString(9, film.getSpecialFeatures());
			} else {
				statement.setNull(9, java.sql.Types.VARCHAR);
			}

			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				return null;
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					film.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating film failed, no ID obtained.");
				}
			}
		}
		return film;
	}

	//Mace code:
	@Override
	public boolean save(Film film) {
	    try {
	        if (film.getId() == 0) {
	            createFilm(film);
	        } else {
	            updateFilm(film);
	        }
	        return true; // Success
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Error occurred
	    }
	}
	//end of Mace code

}
