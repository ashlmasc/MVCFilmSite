package com.skilldistillery.film.data;

import java.sql.SQLException;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	Film findFilmById(int filmId);

	boolean save(Film film);

}
