package xyz.maksimenko.iqbuzztt.DAO;

import java.util.List;

import xyz.maksimenko.iqbuzztt.Place;

public interface PlaceDAO {
	public void addPlace(Place place);
	public Place getPlaceForTicket(Long ticketId);
	public List<Place> getPlaces();
}
