package xyz.maksimenko.iqbuzztt.DAO.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.maksimenko.iqbuzztt.Place;
import xyz.maksimenko.iqbuzztt.DAO.PlaceDAO;

@Repository
public class PlaceDAOImpl implements PlaceDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public void addPlace(Place place) {
		Session ses = sessionFactory.openSession();
		ses.save(place);
		ses.flush();
		ses.close();
	}

	public Place getPlaceForTicket(Long ticketId) {
		Session ses = sessionFactory.openSession();
		Place place = (Place) ses.createQuery("from Place where placeId = :placeId").setLong("placeId", ticketId).list().get(0);
		ses.close();
		return place;
	}

	@SuppressWarnings("unchecked")
	public List<Place> getPlaces() {
		//return (List<Place>) sessionFactory.getCurrentSession().createQuery("from Place").list();
		Session ses = sessionFactory.openSession();
		List<Place> places = ses.createQuery("from Place").list();
		ses.close();
		return places;
	}

}
