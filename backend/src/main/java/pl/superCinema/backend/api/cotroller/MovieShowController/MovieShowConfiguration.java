package pl.superCinema.backend.api.cotroller.MovieShowController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.superCinema.backend.api.cotroller.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.api.cotroller.movieController.MovieBuilder;
import pl.superCinema.backend.api.cotroller.seatAvailabilityControlller.SeatAvailabilityBuilder;
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
