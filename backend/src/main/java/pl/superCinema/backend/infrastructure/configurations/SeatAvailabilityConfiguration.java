package pl.superCinema.backend.infrastructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.infrastructure.builders.SeatAvailabilityBuilder;
import pl.superCinema.backend.domain.facades.SeatAvailabilityFacade;
import pl.superCinema.backend.domain.ports.SeatAvailabilityRepository;

@Configuration
public class SeatAvailabilityConfiguration {

    @Bean
    SeatAvailabilityBuilder seatAvailabilityBuilder() {
        return new SeatAvailabilityBuilder();
    }

    @Bean
    SeatAvailabilityFacade seatAvailabilityFacade(SeatAvailabilityRepository seatAvailabilityRepository, SeatAvailabilityBuilder seatAvailabilityBuilder) {
        return new SeatAvailabilityFacade(seatAvailabilityRepository, seatAvailabilityBuilder);
    }
}
