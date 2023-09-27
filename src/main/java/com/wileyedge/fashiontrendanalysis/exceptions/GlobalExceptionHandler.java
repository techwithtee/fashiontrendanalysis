package com.wileyedge.fashiontrendanalysis.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A global exception handler that intercepts exceptions thrown throughout the application
 * and transforms them into appropriate HTTP responses. This ensures that all exceptions
 * are caught and handled in a consistent manner, providing meaningful feedback to the client.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Logger instance for logging exception details and diagnostics.
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles general exceptions that are not caught by other specific handlers.
     * This acts as a fallback mechanism to catch all unhandled exceptions and return
     * a generic internal server error response.
     *
     * @param e the exception
     * @return a response entity with status INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleGenericExceptions(Exception e) {
        logger.error("Unhandled exception caught: ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles exceptions of type CustomUncheckedException. This is a custom exception type
     * representing specific error scenarios within the application. This handler logs the error code
     * associated with the exception and provides detailed feedback to the client.
     *
     * @param e the custom unchecked exception
     * @return a response entity with status BAD_REQUEST
     */
    @ExceptionHandler(value = {CustomUncheckedException.class})
    public ResponseEntity<String> handleCustomUncheckedException(CustomUncheckedException e) {
        logger.error("CustomUncheckedException caught. ErrorCode: {}", e.getErrorCode(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles EmptyResultDataAccessException which is thrown when a data access operation
     * fails to return any results (e.g., querying a database for a record that doesn't exist).
     * This handler provides a meaningful response to the client indicating that the requested
     * data was not found.
     *
     * @param e the empty result data access exception
     * @return a response entity with status NOT_FOUND
     */
    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        logger.error("No data found: ", e);
        return new ResponseEntity<>("No data found for the provided input.", HttpStatus.NOT_FOUND);
    }
}
