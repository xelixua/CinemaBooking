 package xyz.maksimenko.iqbuzztt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import xyz.maksimenko.iqbuzztt.DAO.MovieDAO;
import xyz.maksimenko.iqbuzztt.DAO.TicketDAO;

public class TicketsRescuer {
	
	private final int ONE_MINUTE = 60000;
	private final int THIRTY_MINUTES = 30*ONE_MINUTE;
	@Autowired
	TicketDAO ticketDAO;
	@Autowired
	MovieDAO movieDAO;

	@Scheduled(fixedDelay=ONE_MINUTE)
	public void freeTickets() {
		System.out.println("Free tickets run");
		List<Ticket> tickets = ticketDAO.getTickets();
		List<Movie> movies = movieDAO.getMovies();
		long  currentTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		tickets.stream().forEach(ticket -> {
			if(ticket.getBookedTo() != null){
				//check if ticket was booked more than hour ago and didn't get paid
				if(ticket.getBookedTill() != null && ticket.getBookedTill() >= currentTime){
					cancelTicket(ticket);
				} else {
					//check if it's less then 30 mints before movie start and ticket didn't not get paid
					long movieStart = movies.stream().filter(movie -> movie.getMovieId() == ticket.getMovieid()).findFirst().get().getStart();
					if(currentTime >= movieStart - THIRTY_MINUTES){
						cancelTicket(ticket);
					}
				}
				
			}
		});
	}
	
	private void cancelTicket(Ticket ticket){
		ticket.setBookedTo(null);
		ticket.setBookedTill(null);
		ticketDAO.updateTicket(ticket);
	}
}
