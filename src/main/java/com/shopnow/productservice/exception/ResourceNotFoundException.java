package com.shopnow.productservice.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

/**
 * Exception thrown when a requested resource is not found.
 */
public class ResourceNotFoundException extends ApiException {
    
    /**
     * Constructs a new resource not found exception with the specified message.
     *
     * @param message the detail message
     */
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Constructs a new resource not found exception with a default message for the specified resource type and identifier.
     *
     * @param resourceType the type of resource that was not found (e.g., "Product")
     * @param id the identifier of the resource that was not found
     */
    public ResourceNotFoundException(String resourceType, UUID id) {
        super(resourceType + " not found with id: " + id, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Constructs a new resource not found exception with a default message for the specified resource type and field.
     *
     * @param resourceType the type of resource that was not found (e.g., "Product")
     * @param field the field name used for the search (e.g., "name")
     * @param value the value of the field that was searched for
     */
    public ResourceNotFoundException(String resourceType, String field, String value) {
        super(resourceType + " not found with " + field + ": " + value, HttpStatus.NOT_FOUND);
    }
}