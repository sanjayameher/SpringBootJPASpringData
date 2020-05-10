package com.jyotionjava.sdjpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jyotionjava.sdjpa.models.UserResponse;
import com.jyotionjava.sdjpa.util.ValidationUtil;
/*The @ControllerAdvice listens across the whole application for exceptions. When throws an exception, 
it will catch and convert it to the meaningful message.
*/
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<UserResponse> resourceNotFound(ResourceNotFoundException ex) {
    	UserResponse errorResponse = new UserResponse();
        errorResponse.setResourceId(ex.resourceId);
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setErrorMessage(ex.getMessage());

        return new ResponseEntity<UserResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserResponse> handleGenericException(Exception ex) {
    	UserResponse errorResponse = new UserResponse();
        errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setErrorMessage("There is some techncal issue in server..please wait 123");
        return new ResponseEntity<UserResponse>(errorResponse, HttpStatus.OK);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        UserResponse errorResponse = new UserResponse();
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setErrorMessage("Invalid inputs.");
        errorResponse.setErrors(ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<UserResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
}