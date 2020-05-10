/**
 * 
 */
package com.jyotionjava.sdjpa.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jyotionjava.sdjpa.exception.ResourceNotFoundException;
import com.jyotionjava.sdjpa.models.UserResponse;
import com.jyotionjava.sdjpa.models.Booking;
import com.jyotionjava.sdjpa.repositories.BookingRepository;
import com.jyotionjava.sdjpa.services.BookingService;
import com.jyotionjava.sdjpa.services.BookingServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import net.bull.javamelody.MonitoredWithSpring;


@RestController
@RequestMapping("/booking")
@Api(value="BookingStore", description="Operations pertaining to Booking in Online Store")
//@MonitoredWithSpring
@Consumes("application/json")
public class BookingController {
	
    private static final Logger logger = LogManager.getLogger(BookingController.class);

	@Autowired
	BookingService bookingService;

	// POST --> http://localhost:8080/booking/create
	@ApiOperation(value = "Add a Booking")
	@PostMapping("/create")	
	@Produces("application/json")
 	public  ResponseEntity<Booking> addBooking(@Valid @RequestBody Booking booking) {
		logger.info ("Booking To Save:: " + booking);
		booking.setTravelDate(new Date());
		booking = bookingService.saveOrUpdate(booking);
		return new ResponseEntity<Booking>(booking, HttpStatus.CREATED);
	}

	// GET --> http://localhost:8080/booking/read?bookingId=7
	@ApiOperation(value = "Search a Booking with an ID", response = Booking.class)
	@GetMapping("/read")
	@Produces("application/json")
	 	public ResponseEntity<Booking> getBooking(@RequestParam Long bookingId) {
		logger.info ("Booking ID To Return:: " + bookingId);
		Booking booking = bookingService.getById(bookingId);
		if (null==booking) {
			logger.debug("Booking with id:: " + bookingId + " does not exists");
			throw new ResourceNotFoundException(bookingId, "Booking Is Not Available");
		}
		logger.debug("Found Booking:: " + booking);
        return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}

	// GET --> http://localhost:8080/booking/getSpecificBooking/9
	@ApiOperation(value = "Search a Specific Booking with an ID",response = Booking.class)
	@GetMapping(value = "/getSpecificBooking/{bookingId}")
	@Produces("application/json")
 	public @ResponseBody ResponseEntity<Booking> getBookingBySpecificId(@PathVariable("bookingId") Long bookingId) throws ResourceNotFoundException {
		logger.info ("Specific Booking ID To Return:: " + bookingId);
		Booking booking;
		booking = bookingService.getById(bookingId);
		if (null==booking) {
			throw new ResourceNotFoundException(bookingId, "Specific Booking Is Not Available");
		}
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);

	}

	// GET --> http://localhost:8080/booking/readall
	@ApiOperation(value = "View a list of available bookings", response = List.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
	)
	@GetMapping("/readall")
	@Produces("application/json")
 	public ResponseEntity<List<Booking>> getAllBooking() {
		
		logger.info("Returning all the Booking´s");
		
		return new ResponseEntity<List<Booking>>(bookingService.listAll(), HttpStatus.OK);
	}

	// PUT --> http://localhost:8080/booking/update/7?psngrName=Jyoti
	@ApiOperation(value = "Update a Booking")
	@PutMapping("/update/{bookingId}")
	@Produces("application/json")
 	public ResponseEntity<Booking> updateBooking(@PathVariable Long bookingId, @RequestParam String psngrName) {
		logger.info("Booking to Update " + bookingId + "   "+psngrName );
		Booking booking = bookingService.getById(bookingId);
		if (null==booking) {
			throw new ResourceNotFoundException(bookingId, "Booking Is Not Available");
		}
		booking.setPsngrName(psngrName);
		booking = bookingService.saveOrUpdate(booking);
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}

	// DELETE --> http://localhost:8080/booking/delete/7
    @ApiOperation(value = "Delete a Booking")
	@DeleteMapping("/delete/{bookingId}")
    @Produces("application/json")
 	public  @ResponseBody ResponseEntity<UserResponse> deleteBooking(@PathVariable Long bookingId) {
		logger.info("Booking to Delete " + bookingId);
		Booking booking = bookingService.getById(bookingId);
		if (null==booking) {
			throw new ResourceNotFoundException(bookingId, "Booking Is Not Available");
		}
		bookingService.delete(bookingId);
		return new ResponseEntity<UserResponse>(new UserResponse(HttpStatus.OK.value(), "Booking Has Been Deleted"), HttpStatus.OK);		
	}
    
    //  GET --> http://localhost:8080/booking/bookings?page=0&size=3   
	@ApiOperation(value = "View Bookings Page Wise", response = Page.class)
    @Produces("application/json")
	@GetMapping("/bookings")
    public  ResponseEntity<Page<Booking>>  getBookingsPageWise( Pageable pageable){
		logger.info("Display Booking Page Wise" + pageable);
		Page<Booking> bookings = bookingService.listAllByPage(pageable);		
		return new ResponseEntity< Page<Booking> >(bookings, HttpStatus.OK);
	} 
	
	// GET --> http://localhost:8080/booking/sortedbookings/2
	@ApiOperation(value = "View Sorted Booking PageWise")
	@GetMapping("/sortedbookings/{pageNumber}")
	@Produces("application/json")
 	public ResponseEntity<Page<Booking>>  getSortedBookingPageWise(@PathVariable int pageNumber) {		
		logger.info("Returning Sorted Bookings Pagewise..");	
		Page<Booking> bookings = bookingService.sortByPage(pageNumber);	
		return new ResponseEntity< Page<Booking> >(bookings, HttpStatus.OK);
	}
}
