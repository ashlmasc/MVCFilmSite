package com.skilldistillery.film.data;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	Film findFilmById(int filmId);

	boolean save(Film film);

	Actor findActorById(int actorId);

	List<Film> findFilmByKeyword(String userKeyword);

	boolean updateFilm(Film film);
	
	public Film createFilm(Film film) throws SQLException;

	boolean deleteFilm(int filmId) throws SQLException;

}
