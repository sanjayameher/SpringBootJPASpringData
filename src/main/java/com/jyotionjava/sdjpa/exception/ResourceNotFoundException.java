package com.jyotionjava.sdjpa.exception;

public class ResourceNotFoundException extends RuntimeException {

	public Long resourceId;

    public ResourceNotFoundException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
}