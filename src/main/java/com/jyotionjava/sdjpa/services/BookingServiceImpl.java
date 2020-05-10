package com.jyotionjava.sdjpa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jyotionjava.sdjpa.exception.ResourceNotFoundException;
import com.jyotionjava.sdjpa.models.Booking;
import com.jyotionjava.sdjpa.repositories.BookingRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import net.bull.javamelody.MonitoredWithSpring;


@Service
//@MonitoredWithSpring
public class BookingServiceImpl implements BookingService{
	
    private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);
	
	private BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

   // @Cacheable("bookingList")
    @Override
    public List<Booking> listAll() {
    	
    	logger.info(System.currentTimeMillis() +"    listAll --> Start");
    	
        List<Booking> bookings = new ArrayList<Booking>();
        bookingRepository.findAll().forEach(bookings::add); //fun with Java 8
   	    
        logger.info(System.currentTimeMillis() +"    listAll --> End");
        return bookings;
}
    
	@Override
	@Cacheable(value="bookings", key="#id")
	public Booking getById(Long id) {	
		
		 logger.info(System.currentTimeMillis() +"    getById --> Start");
		try{
            Thread.sleep(5000); 
        }catch(Exception e){
        }
		Booking booking = bookingRepository.findOne(id);
		if (booking==null) {
			 throw new ResourceNotFoundException(id, "Booking Not Found");
		}
		 logger.info(System.currentTimeMillis() +"     getById --> End");
		 
		return booking;
	}

	@Override
	@CachePut(value="bookings", key="#booking.bookingId")
	public Booking saveOrUpdate(Booking booking) {
		
		bookingRepository.save(booking);
		  return booking;
	}

	@Override
	@CacheEvict(value = "bookings", key = "#id")
	public void delete(Long id) {
		
		
		try {
		bookingRepository.delete(id);
		}catch(EmptyResultDataAccessException ex) {
			throw new EmptyResultDataAccessException("Entity with id :"+id+ " is not exist",1);
		}
	}
		
	@Override
	public Page<Booking> listAllByPage(Pageable pageable) {
		return bookingRepository.findAll(pageable);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<Booking>  sortByPage(int pageNumber) {
		return  bookingRepository.findAll(gotoPage(0));
	}
	
	private PageRequest gotoPage(int page){
    PageRequest request = new PageRequest(page,4,Sort.Direction.ASC,"bookingId");
	return request;
	}


	
}
