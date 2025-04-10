package org.nahu.exceptions;

public class JsonParserException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public JsonParserException(String message) {
        super(message);
    }
}
