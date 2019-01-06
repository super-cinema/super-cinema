package pl.superCinema.backend.api.controllers.cinemaHallController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.api.controllers.seatController.SeatBuilder;
import pl.superCinema.backend.domain.repository.CinemaHallRepository;
import pl.superCinema.backend.domain.repository.SeatRepository;

@Configuration
public class CinemaHallConfiguration {

    @Bean
    CinemaHallFacade cinemaHallFacade(CinemaHallRepository cinemaHallRepository, CinemaHallBuilder cinemaHallBuilder,
                                      SeatBuilder seatBuilder, SeatRepository seatRepository) {
        return new CinemaHallFacade(cinemaHallRepository, cinemaHallBuilder, seatBuilder, seatRepository);
    }

    @Bean
    CinemaHallBuilder cinemaHallBuilderService(){
        return new CinemaHallBuilder();
    }

}
