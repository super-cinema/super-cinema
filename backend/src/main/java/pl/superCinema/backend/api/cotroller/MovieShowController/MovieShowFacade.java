package pl.superCinema.backend.api.cotroller.MovieShowController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.domain.repository.MovieShowRepository;
@AllArgsConstructor
public class MovieShowFacade {

    private MovieShowRepository movieShowRepository;
    private MovieShowBuilder movieShowBuilder;
}
