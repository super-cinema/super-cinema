package pl.superCinema.backend.api.controllers.seatAvailabilityControlller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.superCinema.backend.api.controllers.movieShowController.MovieShowBuilder;
import pl.superCinema.backend.domain.repository.SeatAvailabilityRepository;

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
