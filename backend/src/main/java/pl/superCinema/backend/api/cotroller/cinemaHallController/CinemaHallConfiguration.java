package pl.superCinema.backend.api.cotroller.cinemaHallController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.superCinema.backend.api.cotroller.MovieShowController.MovieShowBuilder;
import pl.superCinema.backend.api.cotroller.seatControlller.SeatBuilder;
import pl.superCinema.backend.domain.repository.CinemaHallRepository;

@Configuration
public class CinemaHallConfiguration {

    @Bean
    CinemaHallBuilder cinemaHallBuilder(@Lazy SeatBuilder seatBuilder,@Lazy MovieShowBuilder movieShowBuilder){
        return new CinemaHallBuilder(seatBuilder,movieShowBuilder);
    }

    @Bean
    CinemaHallFacade cinemaHallFacade(@Lazy CinemaHallRepository cinemaHallRepository,@Lazy CinemaHallBuilder cinemaHallBuilder){
        return new CinemaHallFacade(cinemaHallRepository,cinemaHallBuilder);
    }



}
