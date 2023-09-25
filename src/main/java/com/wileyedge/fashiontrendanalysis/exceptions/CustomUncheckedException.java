package com.wileyedge.fashiontrendanalysis.exceptions;

public class CustomUncheckedException extends RuntimeException {
    public CustomUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }
}
