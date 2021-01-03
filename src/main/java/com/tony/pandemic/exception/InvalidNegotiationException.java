package com.tony.pandemic.exception;

public class InvalidNegotiationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidNegotiationException(String msg) {
        super(msg);
    }
}
