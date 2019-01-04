package pl.superCinema.backend.api.controllers.cinemaHallController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.api.controllers.seatController.SeatBuilderService;
import pl.superCinema.backend.domain.repository.CinemaHallRepository;

@Configuration
public class CinemaHallConfiguration {

    @Bean
    CinemaHallFacade cinemaHallFacade(CinemaHallRepository cinemaHallRepository, CinemaHallBuilderService cinemaHallBuilderService,
                                      SeatBuilderService seatBuilderService) {
        return new CinemaHallFacade(cinemaHallRepository, cinemaHallBuilderService, seatBuilderService);
    }

    @Bean
    CinemaHallBuilderService cinemaHallBuilderService(){
        return new CinemaHallBuilderService();
    }

}
