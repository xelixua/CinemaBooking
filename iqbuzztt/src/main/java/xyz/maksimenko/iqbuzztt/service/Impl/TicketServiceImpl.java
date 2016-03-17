package xyz.maksimenko.iqbuzztt.service.Impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.maksimenko.iqbuzztt.Place;
import xyz.maksimenko.iqbuzztt.Ticket;
import xyz.maksimenko.iqbuzztt.Visitor;
import xyz.maksimenko.iqbuzztt.DAO.TicketDAO;
import xyz.maksimenko.iqbuzztt.DAO.VisitorDAO;
import xyz.maksimenko.iqbuzztt.service.TicketService;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketDAO ticketDAO;
	@Autowired
	VisitorDAO visitorDAO;

	@Transactional
	public Set<Ticket> bookTicket(long movieId, String userName, Place[] places){
		//TODO check only two or more booking for lux seats
		Visitor visitor = visitorDAO.getVisitorByUserName(userName);
		if(visitor != null)
			System.out.println("Visitor " + userName + " id is " + visitor.getUserId());
		
		if(visitor == null){
			System.out.println("Visitor is null!");
			visitor = new Visitor();
			visitor.setFullName(userName);
			visitorDAO.addVisitor(visitor);
			visitor = visitorDAO.getVisitorByUserName(userName);
			System.out.println(visitor);
			System.out.println("Visitor " + userName + " id is " + visitor.getUserId());
		}
		long  currentTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
				afterHour = currentTime/1000 + 3600;
		System.out.println("Afterhour " + afterHour);
		//book specific ticket for each place
		Set<Ticket> bookedTickets = new HashSet<Ticket>();
		List<Place> bookedLuxPlaces = Arrays.asList(places).stream().filter(place -> place.getRow() == 5).collect(Collectors.toList());
		if(bookedLuxPlaces.size() > 0){ //if visitor is booking lux seats
			System.out.println("Visitor is booking lux seats");
			if(bookedLuxPlaces.size() == 2) { //visitor is booking only 2 lux seats
				System.out.println("Visitor is booking 2 lux seats. It's normal");
				if(Math.abs(bookedLuxPlaces.get(0).getSeat() - bookedLuxPlaces.get(1).getSeat()) != 1) { //visitor is not booking two neighbour seats
					System.out.println("Lux seats are not near");
					places = excludeLuxSeats(places);
					return new HashSet<Ticket>();
				}
			} else {
				System.out.println("Visitor is booking more than two lux seats");
				//places = excludeLuxSeats(places);
				return new HashSet<Ticket>();

			}
		}
		for(Place place : places){
			Ticket ticket = ticketDAO.getTicketForPlace(movieId, place.getRow(), place.getSeat());
			ticket.setBookedTo(visitor.getUserId());
			ticket.setBookedTill(afterHour);
			ticketDAO.updateTicket(ticket);
			bookedTickets.add(ticket);
		}
		return bookedTickets;

	}
	
	private Place[] excludeLuxSeats(Place[] places){
		return Arrays.asList(places).stream().filter(place -> place.getRow() != 5).collect(Collectors.toList()).toArray(new Place[]{});
	}
}
