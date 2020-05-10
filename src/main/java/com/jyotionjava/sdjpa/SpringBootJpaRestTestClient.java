/*package com.jyotionjava.sdjpa;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.jyotionjava.sdjpa.controller.BookingController;
import com.jyotionjava.sdjpa.models.Booking;
import com.jyotionjava.sdjpa.models.UserResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;  
 

public class SpringBootJpaRestTestClient {
	
    private static final Logger logger = LogManager.getLogger(SpringBootJpaRestTestClient.class);    
     
    public static final String REST_SERVICE_URI = "http://localhost:8080/booking";     
    
    
   //  * Add HTTP Authorization header, using Basic-Authentication to send user-credentials.
     
   private static HttpHeaders createHttpHeaders(String user, String password)
    {
        String notEncoded = user + ":" + password;
        String encodedAuth =  new String(Base64.encodeBase64(notEncoded.getBytes()));    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + encodedAuth);
        return headers;
    }
   
   // POST --> http://localhost:8080/booking/create 
    private static void addBooking() 
    {
    	logger.info("Testing Add Booking API----------");
       
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = createHttpHeaders("admin","admin");
           // Booking booking = new Booking("AddRestClient3", "Pune", "USA");
            Booking booking=new Booking();
            HttpEntity<Object> request = new HttpEntity<Object>(booking, headers);
            ResponseEntity<Booking> response = restTemplate.exchange(REST_SERVICE_URI+"/create/", HttpMethod.POST, request, Booking.class);
            logger.info ("New Booking Response Status [ "+ response.getStatusCode() + " ] Has Body: " + response.getBody());
        }
        catch (Exception e) {
        	logger.info ("** Exception: "+ e.getMessage());
        }
    }
    
    //  PUT --> http://localhost:8080/booking/update/30?psngrName=Gahan   
	private static void updateBooking() {
		
		logger.info("Testing Update Booking API----------");
		
		HttpHeaders headers = createHttpHeaders("admin","admin");
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		Booking booking = new Booking();
		HttpEntity<Booking> request = new HttpEntity<Booking>(booking, headers);
		ResponseEntity <Booking> response = restTemplate.exchange(REST_SERVICE_URI + "/update/20?psngrName=Update", HttpMethod.PUT, request, Booking.class);
        
		logger.info ("Update Booking Response Status [ "+ response.getStatusCode() + " ] Has Body: " + response.getBody());

	}
  
	// DELETE --> http://localhost:8080/booking/delete/7 
	private static void deleteBooking() {
		
		logger.info("Testing Delete Booking API----------");
		
		HttpHeaders headers = createHttpHeaders("admin", "admin");
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		Booking booking = new Booking();
		HttpEntity<Booking> request = new HttpEntity<Booking>(booking, headers);
		ResponseEntity <UserResponse> response = restTemplate.exchange(REST_SERVICE_URI + "/delete/11", HttpMethod.DELETE,request, UserResponse.class);
        
		logger.info ("Delete Booking Response Status [ "+ response.getStatusCode() + " ]" );

	}
    
	// GET --> http://localhost:8080/booking/readall 
	private static void getAllBooking() {
		
		logger.info("Testing Getting All-Booking API----------");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
 		RestTemplate restTemplate = new RestTemplate();
		Booking booking = new Booking();
		HttpEntity<Booking> request = new HttpEntity<Booking>(booking, headers);
		ResponseEntity<List<Booking>> response = restTemplate.exchange(REST_SERVICE_URI + "/readall", HttpMethod.GET,request,new ParameterizedTypeReference<List<Booking>>() {});
        
		logger.info ("Getting All Booking Response Status [ "+ response.getStatusCode() + " ] Has Body: " + response.getBody());

	}
    
	// GET --> http://localhost:8080/booking/read?bookingId=20 
	private static void getBooking() {
		
		logger.info("Testing Getting Booking API----------");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
 		RestTemplate restTemplate = new RestTemplate();
		Booking booking = new Booking();
		HttpEntity<Booking> request = new HttpEntity<Booking>(booking, headers);
		ResponseEntity<Booking> response = restTemplate.exchange(REST_SERVICE_URI + "/read?bookingId=20", HttpMethod.GET,request,Booking.class);
        
		logger.info ("Getting Booking Response Status [ "+ response.getStatusCode() + " ] Has Body: " + response.getBody());

	}
	 
	// GET --> http://localhost:8080/booking/getSpecificBooking/9 
	private static void getBookingBySpecificId() {
		
		logger.info("Testing Getting Specific Booking API----------");
		
		HttpHeaders headers = createHttpHeaders("user", "user");
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		Booking booking = new Booking();
		HttpEntity<Booking> request = new HttpEntity<Booking>(booking, headers);
		ResponseEntity<Booking> response = restTemplate.exchange(REST_SERVICE_URI + "/getSpecificBooking/21", HttpMethod.GET,request,Booking.class);
        
		logger.info ("Getting Specific Booking Response Status [ "+ response.getStatusCode() + " ] Has Body: " + response.getBody());

	}
  
     
    public static void main(String args[]){
    	
    	 addBooking();
    	 updateBooking();
    	 deleteBooking();    	
    	 getAllBooking();
    	 getBooking();
    	 getBookingBySpecificId();    	
    	
    	
}
}*/