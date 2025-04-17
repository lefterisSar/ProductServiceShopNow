package com.shopnow.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception thrown when validation errors occur.
 */
public class ValidationException extends ApiException {
    
    private final Map<String, String> errors;
    
    /**
     * Constructs a new validation exception with the specified message.
     *
     * @param message the detail message
     */
    public ValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
        this.errors = new HashMap<>();
    }
    
    /**
     * Constructs a new validation exception with the specified message and field errors.
     *
     * @param message the detail message
     * @param errors a map of field names to error messages
     */
    public ValidationException(String message, Map<String, String> errors) {
        super(message, HttpStatus.BAD_REQUEST);
        this.errors = errors;
    }
    
    /**
     * Constructs a new validation exception from a binding result.
     *
     * @param bindingResult the binding result containing validation errors
     */
    public ValidationException(BindingResult bindingResult) {
        super("Validation failed", HttpStatus.BAD_REQUEST);
        this.errors = new HashMap<>();
        
        for (FieldError error : bindingResult.getFieldErrors()) {
            this.errors.put(error.getField(), error.getDefaultMessage());
        }
    }
    
    /**
     * Returns the validation errors.
     *
     * @return a map of field names to error messages
     */
    public Map<String, String> getErrors() {
        return errors;
    }
    
    /**
     * Adds a field error to this exception.
     *
     * @param field the field name
     * @param message the error message
     * @return this exception for method chaining
     */
    public ValidationException addError(String field, String message) {
        this.errors.put(field, message);
        return this;
    }
}