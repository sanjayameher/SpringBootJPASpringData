package com.jyotionjava.sdjpa.models;

import java.util.List;

public class UserResponse  {
	
	
	private int status;
	private String message;
    private int  errorCode;
    private String errorMessage;
    private Long resourceId;
    private List<String> errors;
    
    public UserResponse() {
    }    

    public UserResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public int  getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int  errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	
	public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
}
    
    
}