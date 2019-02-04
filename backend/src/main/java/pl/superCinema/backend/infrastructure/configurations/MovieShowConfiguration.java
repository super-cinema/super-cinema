package pl.superCinema.backend.infrastructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.infrastructure.builders.CinemaHallBuilder;
import pl.superCinema.backend.infrastructure.builders.MovieBuilder;
import pl.superCinema.backend.infrastructure.builders.MovieShowBuilder;
import pl.superCinema.backend.infrastructure.builders.SeatAvailabilityBuilder;
import pl.superCinema.backend.domain.facades.MovieShowFacade;
import pl.superCinema.backend.domain.ports.MovieShowRepository;

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
