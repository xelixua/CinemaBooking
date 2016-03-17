package xyz.maksimenko.iqbuzztt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tickets")
public class Ticket {
	
	@Id
	@Column(name = "ticketid")
	@GenericGenerator(name="autoincrement",strategy="increment")
	@GeneratedValue(generator="autoincrement")
	private int ticketId;
	
	@Column(name = "movieid")
	private int movieid;
	
	@Column(name = "bookedto")
	private Integer bookedTo;
	
	@Column(name = "bookedTill")
	private Long bookedTill;
	
	private int placeid;
	
	private boolean paid;

	public Integer getBookedTo() {
		return bookedTo;
	}

	public void setBookedTo(Integer bookedTo) {
		this.bookedTo = bookedTo;
	}

	public Long getBookedTill() {
		return bookedTill;
	}

	public void setBookedTill(Long bookedTill) {
		this.bookedTill = bookedTill;
	}

	public int getPlaceid() {
		return placeid;
	}

	public void setPlace(int placeid) {
		this.placeid = placeid;
	}

	public boolean getPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
}
