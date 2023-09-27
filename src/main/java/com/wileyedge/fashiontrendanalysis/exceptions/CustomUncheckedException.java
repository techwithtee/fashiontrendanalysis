package com.wileyedge.fashiontrendanalysis.exceptions;

/**
 * CustomUncheckedException is thrown when specific scenarios within the fashion trend analysis
 * are met that aren't covered by standard exceptions. This might happen when a certain operation
 * fails due to unexpected data or system states.
 *
 * <p>Recovery: Ensure the system's data is in a consistent state and that any third-party
 * integrations are functioning correctly. Verify the operations' preconditions before invoking them.</p>
 */
public class CustomUncheckedException extends RuntimeException {

    private String errorCode;

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message
     */
    public CustomUncheckedException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause.
     *
     * @param cause the cause
     */
    public CustomUncheckedException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public CustomUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message and error code.
     *
     * @param message the detail message
     * @param errorCode the error code
     */
    public CustomUncheckedException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Returns the error code associated with this exception.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }
}
