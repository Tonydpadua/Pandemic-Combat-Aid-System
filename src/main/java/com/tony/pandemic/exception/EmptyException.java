package com.tony.pandemic.exception;

public class EmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmptyException(String msg) {
        super(msg);
    }
}
