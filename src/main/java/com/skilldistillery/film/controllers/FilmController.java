package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDAO;

	@RequestMapping("")
	public String home() {
		return "home";
	}

	@GetMapping("/viewFilm.do")
	public String viewFilm(@RequestParam("id") int filmId, Model model) {
		Film film = filmDAO.findFilmById(filmId);
		if (film == null) {
			model.addAttribute("errorMessage", "No film found with ID " + filmId);
			return "filmNotFound";
		}
		model.addAttribute("film", film);
		return "filmDetail";
	}

	@RequestMapping(path = "filmsSearch.do")
	public ModelAndView filmsSearch(@RequestParam("search") String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = filmDAO.findFilmByKeyword(keyword);
		if (films.isEmpty()) {
			mv.setViewName("filmNotFound");
		}
		mv.addObject("films", films);
		mv.setViewName("filmsSearch");
		return mv;
	}

	@GetMapping("/filmForm.do")
	public String showFilmForm(Model model) {
		model.addAttribute("film", new Film());
		return "filmForm";
	}

	@PostMapping("/addFilm.do")
	public ModelAndView addFilm(@ModelAttribute("film") Film film, BindingResult result, Model model, // Mace Code
			@RequestParam(value = "actors", required = false) List<String> actorNames) throws SQLException { // end of
																												// Mace
		if (film.getTitle() == null || film.getTitle().isEmpty()) {
			result.rejectValue("title", "error.film", "Title is required.");
		}

//		List<Actor> actors = actorNames.stream().map(actorName -> {
//			String[] names = actorName.split(" ");
//			return new Actor(0, names[0], names[1]);
//		}).collect(Collectors.toList());

		List<Actor> actors = actorNames.stream().map(actorName -> {
			String[] names = actorName.split(" ");
			if (names.length > 1) {
				return new Actor(0, names[0], names[1]);
			} else {
				// Handle the case where there is no last name or provide a default value
				return new Actor(0, names[0], "");
			}
		}).collect(Collectors.toList());

		film.setActors(actors);

		Film userFilm = filmDAO.createFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", userFilm);
		mv.setViewName("filmDetail");
		return mv;
	}

	@PostMapping("/deleteFilm.do")
	public String deleteFilm(@RequestParam("id") int filmId, Model model) throws SQLException {
		boolean isDeleted = filmDAO.deleteFilm(filmId);
		if (!isDeleted) {
			model.addAttribute("message", "Film could not be deleted.");
		} else {
			model.addAttribute("message", "Film successfully deleted.");
		}
		return "deleteStatus";
	}

	@RequestMapping(path = "/updateFilm.do", method = RequestMethod.GET)
	public String updateFilmForm(@RequestParam("id") int id, Model model) {
		Film film = filmDAO.findFilmById(id);
		model.addAttribute("film", film);
		return "update";
	}

	@RequestMapping(path = "/filmDetail.do", method = RequestMethod.POST)
	public String updateFilmFinally(@ModelAttribute("film") Film updatedFilm, Model model) {
		Film film = filmDAO.findFilmById(updatedFilm.getId());

		if (film != null) {
			film.setTitle(updatedFilm.getTitle());
			film.setDescription(updatedFilm.getDescription());
			film.setRating(updatedFilm.getRating());
			film.setLength(updatedFilm.getLength());
			film.setLength(updatedFilm.getLength());
		}

		try {
			boolean newFilm = filmDAO.updateFilm(film);
			if (newFilm) {
				model.addAttribute("Updated the film");
			} else {
				model.addAttribute("Failed to update film");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "filmDetail";
	}
	
	@ControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(Exception.class)
	    public String handleException(Exception e, Model model) {
	        model.addAttribute("errorMessage", e.getMessage());
	        return "errorPage";
	    }
	}
}