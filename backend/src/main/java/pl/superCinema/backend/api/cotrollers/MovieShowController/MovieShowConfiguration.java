package pl.superCinema.backend.api.cotrollers.MovieShowController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.superCinema.backend.api.cotrollers.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.api.cotrollers.movieController.MovieBuilder;
import pl.superCinema.backend.api.cotrollers.seatAvailabilityControlller.SeatAvailabilityBuilder;
import pl.superCinema.backend.domain.repository.MovieShowRepository;

@Configuration
public class MovieShowConfiguration {

    @Bean
    MovieShowBuilder movieShowBuilder(@Lazy MovieBuilder movieBuilder, @Lazy CinemaHallBuilder cinemaHallBuilder,
                                      @Lazy SeatAvailabilityBuilder seatAvailabilityBuilder) {
        return new MovieShowBuilder(movieBuilder, cinemaHallBuilder, seatAvailabilityBuilder);
    }

    @Bean
    MovieShowFacade movieShowFacade(@Lazy MovieShowRepository movieShowRepository,@Lazy MovieShowBuilder movieShowBuilder) {
        return new MovieShowFacade(movieShowRepository, movieShowBuilder);
    }

}
