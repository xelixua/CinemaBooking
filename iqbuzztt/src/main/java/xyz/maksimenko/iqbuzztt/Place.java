package xyz.maksimenko.iqbuzztt;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "places")
public class Place {
	@Id
	@Column(name = "placeid")
	@GenericGenerator(name="autoincrement",strategy="increment")
	@GeneratedValue(generator="autoincrement")
	protected int placeId;
	
	@Column(name = "comfclass")
	/* 0 - cheap
	 * 1 - middle
	   2 - lux */
	protected byte comfClass;  
	
	@Column(name = "price")
	protected short price;
	
	@Column(name = "row")
	protected byte row;
	
	@Column(name = "seat")
	protected byte seat;
	
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="placeid", referencedColumnName="placeid")
	protected List<Ticket> tickets;
	
	//for displaying type of seat on map
	@Transient
	protected String cssClass;
	
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public short getPrice() {
		return price;
	}
	public void setPrice(short price) {
		this.price = price;
	}
	public byte getRow() {
		return row;
	}
	public void setRow(byte row) {
		this.row = row;
	}
	public byte getColumn() {
		return seat;
	}
	public void setColumn(byte column) {
		this.seat = column;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> ticket) {
		this.tickets = ticket;
	}
	public byte getComfClass() {
		return comfClass;
	}
	public void setComfClass(byte comfClass) {
		this.comfClass = comfClass;
	}
	public byte getSeat() {
		return seat;
	}
	public void setSeat(byte seat) {
		this.seat = seat;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getCssClass() {
		return cssClass;
	} 
}
