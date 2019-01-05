package pl.superCinema.backend.api.cotroller.seatControlller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.superCinema.backend.api.cotroller.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.domain.repository.SeatRepository;

@Configuration
public class SeatConfiguration {
    @Bean
    SeatBuilder seatBuilder(@Lazy CinemaHallBuilder cinemaHallBuilder) {
        return new SeatBuilder(cinemaHallBuilder);
    }

    @Bean
    SeatFacade seatFacade(@Lazy SeatRepository seatRepository,@Lazy SeatBuilder seatBuilder){
        return new SeatFacade(seatRepository, seatBuilder);
    }
}

