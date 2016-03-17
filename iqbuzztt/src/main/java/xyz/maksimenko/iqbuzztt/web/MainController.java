package xyz.maksimenko.iqbuzztt.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.maksimenko.iqbuzztt.Movie;
import xyz.maksimenko.iqbuzztt.DAO.MovieDAO;

@Controller
public class MainController {
	@Autowired
	MovieDAO movieDAO;
	
	@RequestMapping("/index")
	public String showBookingPage(Map<String, Object> map){
		List<Movie> movies = movieDAO.getMovies();
		map.put("movies", movies);
		return "mainpage";
	}
	
	@RequestMapping("/")
	public String home(){
		return "redirect:/index";
	}
}
