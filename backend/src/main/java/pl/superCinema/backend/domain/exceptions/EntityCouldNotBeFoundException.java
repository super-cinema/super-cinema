package pl.superCinema.backend.domain.exceptions;

public class EntityCouldNotBeFoundException extends RuntimeException {
    public EntityCouldNotBeFoundException(String message) {
        super(message);
    }
}
