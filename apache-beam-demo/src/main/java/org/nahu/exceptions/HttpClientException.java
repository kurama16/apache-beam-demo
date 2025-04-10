package org.nahu.exceptions;

public class HttpClientException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public HttpClientException(String message) {
        super(message);
    }
}
