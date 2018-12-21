package pl.superCinema.backend.api.cotroller.seatControlller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.api.cotroller.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.domain.repository.SeatRepository;

@Configuration
public class SeatConfiguration {
    @Bean
    SeatBuilder seatBuilder(CinemaHallBuilder cinemaHallBuilder) {
        return new SeatBuilder(cinemaHallBuilder);
    }

    @Bean
    SeatFacade seatFacade(SeatRepository seatRepository, SeatBuilder seatBuilder){
        return new SeatFacade(seatRepository, seatBuilder);
    }
}

