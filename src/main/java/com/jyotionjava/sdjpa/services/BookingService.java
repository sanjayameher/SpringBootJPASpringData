package com.jyotionjava.sdjpa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jyotionjava.sdjpa.models.Booking;

public interface BookingService {

	List<Booking> listAll();

	Booking getById(Long id);

	Booking saveOrUpdate(Booking booking);

	void delete(Long id);
	
	Page<Booking> listAllByPage(Pageable pageable);
	
	Page<Booking> sortByPage(int pageNumber);
	

}
