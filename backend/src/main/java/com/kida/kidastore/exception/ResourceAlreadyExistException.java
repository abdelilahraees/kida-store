package com.kida.kidastore.exception;

public class ResourceAlreadyExistException extends RuntimeException {

    public ResourceAlreadyExistException() {
    }

    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
