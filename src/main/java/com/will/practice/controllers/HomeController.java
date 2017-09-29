package com.will.practice.controllers;

import com.will.practice.models.Movie;
import com.will.practice.repositories.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private MovieDao movieDao;

    //home page lists all movies
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("movies", movieDao.findAll());
        return "index";
    }

    //directs to add movie page with form
    @RequestMapping("/add")
    public String addMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "add";
    }

    //handles post from form to add new movie
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewMovie(@ModelAttribute Movie movie) {
        movieDao.save(movie);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String editForm(Model model,
                           @PathVariable("id") Long id) {
        Movie myMovie = movieDao.findOne(id);
        model.addAttribute("movie", myMovie);
        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateMovie(@ModelAttribute Movie movie) {
        movieDao.save(movie);
        return "redirect:/";
    }
}
