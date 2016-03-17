package xyz.maksimenko.iqbuzztt.service;

import java.util.List;

import xyz.maksimenko.iqbuzztt.Place;

public interface PlaceService {
	public List<Place> getPlaces(long movieId);
}
