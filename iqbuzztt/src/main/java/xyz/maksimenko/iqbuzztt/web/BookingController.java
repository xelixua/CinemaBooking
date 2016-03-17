package xyz.maksimenko.iqbuzztt.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.maksimenko.iqbuzztt.DAO.MovieDAO;

@Controller
public class BookingController {
	
	@Autowired
	MovieDAO movieDAO;
	
	@RequestMapping("/booking/{movieId}")
	public String showBookingPage(Map<String, Object> map, @PathVariable("movieId") int movieId){
		String movieName = movieDAO.getMovieById(movieId).getName();
		map.put("movieName", movieName);
		map.put("movieId", movieId);
		return "booking";
	}
}