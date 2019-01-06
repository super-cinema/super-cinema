package pl.superCinema.backend.api.controllers.seatController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.domain.repository.SeatRepository;

@Configuration
public class SeatConfiguration {


    @Bean
    SeatFacade seatFacade(SeatRepository seatRepository, SeatBuilder seatBuilder){
        return new SeatFacade(seatRepository, seatBuilder);
    }

    @Bean
    SeatBuilder seatBuilderService() {
        return new SeatBuilder();
    }
}
