package xyz.maksimenko.iqbuzztt.DAO;

import java.util.List;

import xyz.maksimenko.iqbuzztt.Movie;
import xyz.maksimenko.iqbuzztt.Ticket;

public interface MovieDAO {
	public void addMovie(MovieDAO movie);
	public List<Ticket> getTicketsForMovie(Long movieId);
	public void removeMovie(Long movieId);
	public Movie getMovieById(int movieid);
	public List<Movie> getMovies();
}
