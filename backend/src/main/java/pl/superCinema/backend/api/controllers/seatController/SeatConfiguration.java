package pl.superCinema.backend.api.controllers.seatController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.api.controllers.cinemaHallController.CinemaHallBuilderService;
import pl.superCinema.backend.domain.repository.SeatRepository;

@Configuration
public class SeatConfiguration {


    @Bean
    SeatFacade seatFacade(SeatRepository seatRepository, SeatBuilderService seatBuilderService, CinemaHallBuilderService cinemaHallBuilderService){
        return new SeatFacade(seatRepository, seatBuilderService, cinemaHallBuilderService);
    }

    @Bean
    SeatBuilderService seatBuilderService() {
        return new SeatBuilderService();
    }
}
