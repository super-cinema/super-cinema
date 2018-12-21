package pl.superCinema.backend.api.cotroller.cinemaHallController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.domain.repository.CinemaHallRepository;
@AllArgsConstructor
public class CinemaHallFacade {

    private CinemaHallRepository cinemaHallRepository;
    private CinemaHallBuilder cinemaHallBuilder;
}
