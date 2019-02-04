package pl.superCinema.backend.infrastructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.infrastructure.builders.CinemaHallBuilder;
import pl.superCinema.backend.infrastructure.builders.SeatBuilder;
import pl.superCinema.backend.domain.facades.CinemaHallFacade;
import pl.superCinema.backend.domain.ports.CinemaHallRepository;
import pl.superCinema.backend.domain.ports.SeatRepository;

@Configuration
public class CinemaHallConfiguration {

    @Bean
    CinemaHallFacade cinemaHallFacade(CinemaHallRepository cinemaHallRepository, CinemaHallBuilder cinemaHallBuilder,
                                      SeatBuilder seatBuilder, SeatRepository seatRepository) {
        return new CinemaHallFacade(cinemaHallRepository, cinemaHallBuilder, seatBuilder, seatRepository);
    }

    @Bean
    CinemaHallBuilder cinemaHallBuilderService(SeatBuilder seatBuilder) {
        return new CinemaHallBuilder(seatBuilder);
    }

}
