package com.skilldistillery.film.controllers;

import java.util.List;
import java.util.stream.Collectors;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
			return "filmNotFound"; // added prefix/suffix in servlet so just names
		}
		model.addAttribute("film", film);
		return "filmDetail"; // added prefix/suffix in servlet so just name
	}

//	ADDED CODE
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

	// Mace code:

	@GetMapping("/filmForm.do")
	public String showFilmForm(Model model) {
		model.addAttribute("film", new Film());
		return "filmForm";
	}

	// end of Mace code.

	@PostMapping("/addFilm.do")
	public ModelAndView addFilm(@ModelAttribute("film") Film film, BindingResult result, Model model, // Mace Code
			@RequestParam(value = "actors", required = false) List<String> actorNames) throws SQLException { // end of
																												// Mace
																												// Code
		// Validation logic
		if (film.getTitle() == null || film.getTitle().isEmpty()) {
			result.rejectValue("title", "error.film", "Title is required.");
		}

		List<Actor> actors = actorNames.stream().map(actorName -> {
			String[] names = actorName.split(" ");
			return new Actor(0, names[0], names[1]);
		}).collect(Collectors.toList());

		film.setActors(actors);

		Film userFilm = filmDAO.createFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", userFilm);
		mv.setViewName("filmDetail");
		return mv;
	}

	// new code for delete functionality
	@GetMapping("/deleteFilm.do")
	public String deleteFilm(@RequestParam("id") int filmId, Model model) throws SQLException {
		boolean isDeleted = filmDAO.deleteFilm(filmId);
		if (!isDeleted) {
			model.addAttribute("deleteError", "Film could not be deleted.");
			return "deleteFailure"; // JSP page for delete failure
		}
		return "redirect:/films"; // Redirect to list of films after deletion
	}

	@GetMapping("/updateFilm.do")
	public ModelAndView updateFilm(@RequestParam("id") int filmId) throws SQLException {
		Film film = filmDAO.findFilmById(filmId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", film);
		mv.setViewName("update");
		return mv;
	}

	@PostMapping("/filmDetail.do")
	public ModelAndView updatedFilmPage(@RequestParam("id") Film updatedFilm) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDAO.findFilmById(updatedFilm.getId());
		boolean newFilm = filmDAO.updateFilm(film);
		if (newFilm) {
			mv.setViewName("filmDetail");
		} else {
			mv.setViewName("home");
		}
		mv.addObject("film", film);
		return mv;
	}

//	

}