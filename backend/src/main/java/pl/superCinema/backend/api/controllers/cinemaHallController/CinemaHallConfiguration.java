package pl.superCinema.backend.api.controllers.cinemaHallController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.api.controllers.seatController.SeatBuilderService;
import pl.superCinema.backend.domain.repository.CinemaHallRepository;
import pl.superCinema.backend.domain.repository.SeatRepository;

@Configuration
public class CinemaHallConfiguration {

    @Bean
    CinemaHallFacade cinemaHallFacade(CinemaHallRepository cinemaHallRepository, CinemaHallBuilderService cinemaHallBuilderService,
                                      SeatBuilderService seatBuilderService, SeatRepository seatRepository) {
        return new CinemaHallFacade(cinemaHallRepository, cinemaHallBuilderService, seatBuilderService, seatRepository);
    }

    @Bean
    CinemaHallBuilderService cinemaHallBuilderService(){
        return new CinemaHallBuilderService();
    }

}
