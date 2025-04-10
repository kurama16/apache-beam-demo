package org.nahu.exceptions;

public class HttpClientNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public HttpClientNotFoundException(String message) {
        super(message);
    }
}
