package pl.superCinema.backend.domain.facades;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.infrastructure.builders.MovieShowBuilder;
import pl.superCinema.backend.domain.ports.MovieShowRepository;

@AllArgsConstructor
public class MovieShowFacade {

    private MovieShowRepository movieShowRepository;
    private MovieShowBuilder movieShowBuilder;
}
