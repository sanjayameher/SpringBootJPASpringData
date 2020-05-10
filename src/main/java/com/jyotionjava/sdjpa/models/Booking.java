/**
 * 
 */
package com.jyotionjava.sdjpa.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//import javax.persistence.SequenceGenerator;
//import net.bull.javamelody.MonitoredWithSpring;

@Entity
@Table(name = "BOOKING")
//@MonitoredWithSpring
public class Booking implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*public Booking() {
		
	}*/
	
	/*public Booking(Long bookingId, String psngrName, String departure, String destination, Date travelDate) {
		super();
		this.bookingId = bookingId;
		this.psngrName = psngrName;
		this.departure = departure;
		this.destination = destination;
		this.travelDate = travelDate;
	}

	

	public Booking(String psngrName, String departure, String destination) {
		super();
		this.psngrName = psngrName;
		this.departure = departure;
		this.destination = destination;
	}
	
	public Booking(Long bookingId,String psngrName, String departure, String destination) {
		super();
		this.bookingId = bookingId;
		this.psngrName = psngrName;
		this.departure = departure;
		this.destination = destination;
	}*/

	@Id
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BOOKINGSEQ")
	@SequenceGenerator(name="BOOKINGSEQ", sequenceName="BOOKINGSEQ", allocationSize=1)*/
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long bookingId;
	
	@NotNull(message = "Passenger name can not be null.")
	@Column
	String psngrName;
	
	@NotNull(message = "Departure can not be null.")
	@Column
	String departure;
	
	@NotNull(message = "Destination can not be null.")
	@Column
	String destination;
	
	@Column
	Date travelDate;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getPsngrName() {
		return psngrName;
	}

	public void setPsngrName(String psngrName) {
		this.psngrName = psngrName;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", psngrName=" + psngrName + ", departure=" + departure
				+ ", destination=" + destination + ", travelDate=" + travelDate + "]";
	}
	
}
