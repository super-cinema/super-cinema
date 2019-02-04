package pl.superCinema.backend.infrastructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.infrastructure.builders.SeatBuilder;
import pl.superCinema.backend.domain.facades.SeatFacade;
import pl.superCinema.backend.domain.ports.SeatRepository;

@Configuration
public class SeatConfiguration {


    @Bean
    SeatFacade seatFacade(SeatRepository seatRepository, SeatBuilder seatBuilder) {
        return new SeatFacade(seatRepository, seatBuilder);
    }

    @Bean
    SeatBuilder seatBuilderService() {
        return new SeatBuilder();
    }
}
