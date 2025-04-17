package com.shopnow.productservice.exception;

import org.springframework.http.HttpStatus;

/**
 * Base exception class for all API exceptions.
 * Provides common functionality for all API exceptions.
 */
public abstract class ApiException extends RuntimeException {
    
    private final HttpStatus status;
    
    /**
     * Constructs a new API exception with the specified message and HTTP status.
     *
     * @param message the detail message
     * @param status the HTTP status to be returned to the client
     */
    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    
    /**
     * Constructs a new API exception with the specified message, cause, and HTTP status.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     * @param status the HTTP status to be returned to the client
     */
    public ApiException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }
    
    /**
     * Returns the HTTP status associated with this exception.
     *
     * @return the HTTP status
     */
    public HttpStatus getStatus() {
        return status;
    }
}