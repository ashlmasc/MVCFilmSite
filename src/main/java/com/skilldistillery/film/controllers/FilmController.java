package com.skilldistillery.film.controllers;

<<<<<<< HEAD
import java.util.List;
import java.util.stream.Collectors;
=======
import java.sql.SQLException;
>>>>>>> 9a84dcf6ad43b4c22fe16035bfb12ea2f678015e

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	        return "filmNotFound";  // added prefix/suffix in servlet so just names
	    }
	    model.addAttribute("film", film);
	    return "filmDetail";  // added prefix/suffix in servlet so just name
	}
	
	//Mace code:
	
	@GetMapping("/filmForm.do")
	public String showFilmForm(Model model) {
		model.addAttribute("film", new Film());
		return "filmForm";
	}
	
	//end of Mace code.
	
	@PostMapping("/addFilm.do")
	public String addFilm(@ModelAttribute("film") Film film, BindingResult result, Model model, //Mace Code
            @RequestParam(value = "actors", required = false) List<String> actorNames) { //end of Mace Code
        // Validation logic
        if (film.getTitle() == null || film.getTitle().isEmpty()) {
            result.rejectValue("title", "error.film", "Title is required.");
        }
        // may need more validation checks

        if (result.hasErrors()) {
            return "filmForm"; // added prefix/suffix in servlet so just name
        }
        
        //Mace code:
        List<Actor> actors = actorNames.stream()
                .map(actorName -> {
                    String[] names = actorName.split(" ");
                    return new Actor(0, names[0], names[1]);
                })
                .collect(Collectors.toList());

        film.setActors(actors);
        
        System.out.println("Received film: " + film);
        
        //end of Mace code.

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
