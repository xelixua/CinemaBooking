package xyz.maksimenko.iqbuzztt.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.maksimenko.iqbuzztt.Place;
import xyz.maksimenko.iqbuzztt.Ticket;
import xyz.maksimenko.iqbuzztt.DAO.PlaceDAO;
import xyz.maksimenko.iqbuzztt.DAO.TicketDAO;
import xyz.maksimenko.iqbuzztt.service.PlaceService;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	PlaceDAO placeDAO;
	@Autowired
	TicketDAO ticketDAO;
	
	@Override
	public List<Place> getPlaces(long movieId) {
		System.out.println("Visitor getting places for movie " + movieId);
		List<Place> places = placeDAO.getPlaces();
		List<Ticket> tickets = ticketDAO.getTicketsForMovie(movieId);
		
		places = places.stream().map(place -> {
			String cssClass = "seat";
			switch(place.getComfClass()){
			case 2:
				cssClass += " lux";
				break;
			case 1:
				cssClass += " middle";
				break;
			default:
				break;
			}
			
			Ticket ticket = tickets.stream().filter(t -> t.getPlaceid() == place.getPlaceId()).findFirst().get();
			if(ticket.getBookedTo() != null){
				cssClass += " already_booked";
			}
			
			//hide info about ticket for this place for visitor
			place.setTickets(null);
			
			place.setCssClass(cssClass);
			return place;
		}).collect(Collectors.toList());
		System.out.println("Places size: " + places.size());
		return places;
	}

}
