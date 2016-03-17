package xyz.maksimenko.iqbuzztt.DAO.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.maksimenko.iqbuzztt.Place;
import xyz.maksimenko.iqbuzztt.Ticket;

@Repository
public class TicketDAOImpl implements xyz.maksimenko.iqbuzztt.DAO.TicketDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public void addTicket(Ticket ticket) {
		Session ses = sessionFactory.openSession();
		ses.save(ticket);
		ses.flush();
		ses.close();
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> getTickets() {
		Session ses = sessionFactory.openSession();
		try{
			return (List<Ticket>) ses.createQuery("from Ticket").list();
		} catch (NullPointerException e){
			return null;
		} finally {
			ses.close();
		}
	}
	
	public Ticket getTicketForPlace(long movieId, byte row, byte seat) {
		Session ses = sessionFactory.openSession();
		try{
			Place place = (Place) ses.createQuery("from Place where row = :row and seat = :seat").setByte("row", row).setByte("seat", seat).list().get(0);
			return place.getTickets().stream().filter(ticket -> ticket.getMovieid() == movieId).findFirst().get();
		} catch (NullPointerException e){
			return null;
		} finally {
			ses.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketsForMovie(long movieId) {
		Session ses = sessionFactory.openSession();
		try{
			return (List<Ticket>) ses.createQuery("from Ticket where movieid = :movieid").setLong("movieid", movieId).list();
		} catch (NullPointerException e){
			return null;
		} finally {
			ses.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketsForVisitorName(String userName) {
		Session ses = sessionFactory.openSession();
		List<Ticket> tickets = (List<Ticket>) ses.createQuery("from Ticket where bookedTo = :username").setString("username", userName).list();
		ses.close();
		return tickets;
	}

	public void removeTicket(String ticketId) {
		Session ses = sessionFactory.openSession();
		Ticket ticket = (Ticket) ses.load(
				Ticket.class, ticketId);
		if (null != ticket) {
			ses.delete(ticket);
		}
		ses.flush();
		ses.close();
	}

	public void updateTicket(Ticket ticket) {
		Session ses = sessionFactory.openSession();
		ses.update(ticket);
		ses.flush();
		ses.close();
	}
}
