package pl.superCinema.backend.api.cotroller.movieController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.api.cotroller.MovieShowController.MovieShowBuilder;
import pl.superCinema.backend.api.cotroller.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.api.cotroller.crewController.CrewBuilder;
import pl.superCinema.backend.api.cotroller.seatAvailabilityControlller.SeatAvailabilityBuilder;
import pl.superCinema.backend.domain.repository.MovieRepository;

@Configuration
public class MovieConfiguration {

    @Bean
    MovieFacade movieService(MovieRepository movieRepository, MovieBuilder movieBuilder) {
        return new MovieFacade(movieRepository, movieBuilder);
    }

    @Bean
    MovieBuilder movieBuilderService(CrewBuilder crewBuilder, MovieShowBuilder movieShowBuilder) {
        return new MovieBuilder(crewBuilder, movieShowBuilder);
    }









}
