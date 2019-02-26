package pl.superCinema.backend.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotDeleted extends RuntimeException{
    public EntityNotDeleted(String message) {
        super(message);
    }
}
