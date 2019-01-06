package pl.superCinema.backend.api.controllers.movieShowController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.domain.repository.MovieShowRepository;
@AllArgsConstructor
public class MovieShowFacade {

    private MovieShowRepository movieShowRepository;
    private MovieShowBuilder movieShowBuilder;
}
