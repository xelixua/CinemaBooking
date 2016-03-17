package xyz.maksimenko.iqbuzztt.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.maksimenko.iqbuzztt.Movie;
import xyz.maksimenko.iqbuzztt.DAO.MovieDAO;
import xyz.maksimenko.iqbuzztt.service.MovieService;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieDAO movieDAO;
	
	@Override
	public List<Movie> getMovies() {
		return movieDAO.getMovies();
	}

}
