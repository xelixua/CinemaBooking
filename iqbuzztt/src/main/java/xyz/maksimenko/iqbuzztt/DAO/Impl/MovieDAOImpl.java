package xyz.maksimenko.iqbuzztt.DAO.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xyz.maksimenko.iqbuzztt.Movie;
import xyz.maksimenko.iqbuzztt.Ticket;
import xyz.maksimenko.iqbuzztt.DAO.MovieDAO;

@Repository
//@Transactional
public class MovieDAOImpl implements MovieDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addMovie(MovieDAO movie) {
		Session ses = sessionFactory.openSession();
		ses.save(movie);
		ses.flush();
		ses.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getTicketsForMovie(Long movieId) {
		Session ses = sessionFactory.openSession();
		List<Ticket> tickets = (List<Ticket>) ses.createQuery("from Tickets where movieId = :movieId").setLong("movieId", movieId).list();
		ses.close();
		return tickets;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeMovie(Long movieId) {
		Session ses = sessionFactory.openSession();
		Movie movie = (Movie) ses.load(
				Movie.class, movieId);
		if (null != movie) {
			ses.delete(movie);
		}
		ses.flush();
		ses.close();
	}

	@Override
	public List<Movie> getMovies() {
		Session ses = sessionFactory.openSession();
		List<Movie> movies = ses.createQuery("from Movie").list();
		ses.close();
		return movies;
	}

	@Override
	public Movie getMovieById(int movieId) {
		Session ses = sessionFactory.openSession();
		Movie movie = (Movie) ses.createQuery("from Movie where movieId = :movieId").setLong("movieId", movieId).list().get(0);
		ses.close();
		return movie;
	}

}
