package se.svit.JeraxTodo.service;

public class InvalidServiceException extends RuntimeException {
    public InvalidServiceException(String message) {
        super(message);
    }
}
