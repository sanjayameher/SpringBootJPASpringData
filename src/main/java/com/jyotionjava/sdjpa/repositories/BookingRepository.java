package com.jyotionjava.sdjpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jyotionjava.sdjpa.models.Booking;

@Transactional
public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
	
	/**
	   * This method will find an Boooking instance in the database by its departure.
	   * Note that this method is not implemented and its working code will be
	   * automatically generated from its signature by Spring Data JPA.
	   */
	  //public Booking findByDeparture(String departure);
}
