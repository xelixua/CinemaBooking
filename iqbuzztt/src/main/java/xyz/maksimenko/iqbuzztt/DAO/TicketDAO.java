package xyz.maksimenko.iqbuzztt.DAO;

import java.util.List;

import xyz.maksimenko.iqbuzztt.Ticket;

public interface TicketDAO {
	public void addTicket(Ticket ticket);
	public List<Ticket> getTickets();
	public Ticket getTicketForPlace(long movieId, byte row, byte seat);
	public List<Ticket> getTicketsForMovie(long movieId);
	public void updateTicket(Ticket ticket);
	public List<Ticket> getTicketsForVisitorName(String userName);
	public void removeTicket(String ticketId);
	
}
