package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.List;

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
	        return "filmNotFound";  // added prefix/suffix in servlet so just names
	    }
	    model.addAttribute("film", film);
	    return "filmDetail";  // added prefix/suffix in servlet so just name
	}
//	ADDED CODE
	@RequestMapping(path = "filmsSearch.do")
	public ModelAndView filmsSearch(@RequestParam("search") String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = filmDAO.findFilmByKeyword(keyword);
		if(films.isEmpty()) {
			mv.setViewName("filmNotFound");
		}
		mv.addObject("films", films);
		mv.setViewName("filmsSearch");
		return mv;
	}
	
	
	@PostMapping("/addFilm")
    public String addFilm(@ModelAttribute("film") Film film, BindingResult result, Model model) {
        // Validation logic
        if (film.getTitle() == null || film.getTitle().isEmpty()) {
            result.rejectValue("title", "error.film", "Title is required.");
        }
        // may need more validation checks

        if (result.hasErrors()) {
            return "filmForm"; // added prefix/suffix in servlet so just name
        }

        boolean isSaved = filmDAO.save(film); // Save the film
        if (!isSaved) {
            model.addAttribute("saveError", "There was an error saving the film.");
            return "filmForm"; // Return to the form view in case of save error
        }

        return "redirect:/films"; // Redirect after successful operation
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
	
	
}
