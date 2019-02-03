package pl.superCinema.backend.api.controllers.movieShowController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.superCinema.backend.api.controllers.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.api.controllers.movieController.MovieBuilder;
import pl.superCinema.backend.api.controllers.seatAvailabilityControlller.SeatAvailabilityBuilder;
import pl.superCinema.backend.domain.repository.MovieShowRepository;

@Configuration
public class MovieShowConfiguration {

    @Bean
    MovieShowBuilder movieShowBuilder(MovieBuilder movieBuilder, CinemaHallBuilder cinemaHallBuilder,
                                       SeatAvailabilityBuilder seatAvailabilityBuilder) {
        return new MovieShowBuilder(movieBuilder, cinemaHallBuilder, seatAvailabilityBuilder);
    }

    @Bean
    MovieShowFacade movieShowFacade(MovieShowRepository movieShowRepository, MovieShowBuilder movieShowBuilder) {
        return new MovieShowFacade(movieShowRepository, movieShowBuilder);
    }

}
