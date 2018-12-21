package pl.superCinema.backend.api.cotroller.MovieShowController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.api.cotroller.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.api.cotroller.movieController.MovieBuilder;
import pl.superCinema.backend.api.cotroller.movieController.MovieFacade;
import pl.superCinema.backend.api.cotroller.seatAvailabilityControlller.SeatAvailabilityBuilder;
import pl.superCinema.backend.domain.repository.MovieRepository;

@Configuration
public class MovieShowConfiguration {

    @Bean
    MovieShowBuilder movieShowBuilder(MovieBuilder movieBuilder, CinemaHallBuilder cinemaHallBuilder,
                                      SeatAvailabilityBuilder seatAvailabilityBuilder){
        return new MovieShowBuilder(movieBuilder,cinemaHallBuilder,seatAvailabilityBuilder);
    }

    @Bean
    MovieFacade movieFacade(MovieRepository movieRepository, MovieBuilder movieBuilder){
        return new MovieFacade(movieRepository,movieBuilder);
    }

}
