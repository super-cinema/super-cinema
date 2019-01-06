package pl.superCinema.backend.api.cotrollers.MovieShowController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.domain.repository.MovieShowRepository;
@AllArgsConstructor
public class MovieShowFacade {

    private MovieShowRepository movieShowRepository;
    private MovieShowBuilder movieShowBuilder;
}
