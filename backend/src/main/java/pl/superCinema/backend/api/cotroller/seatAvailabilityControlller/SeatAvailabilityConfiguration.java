package pl.superCinema.backend.api.cotroller.seatAvailabilityControlller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.api.cotroller.MovieShowController.MovieShowBuilder;
import pl.superCinema.backend.api.cotroller.seatControlller.SeatBuilder;
import pl.superCinema.backend.api.cotroller.seatControlller.SeatFacade;
import pl.superCinema.backend.domain.repository.SeatRepository;

@Configuration
public class SeatAvailabilityConfiguration {

    @Bean
    SeatAvailabilityBuilder seatAvailabilityBuilder(MovieShowBuilder movieShowBuilder){
        return new SeatAvailabilityBuilder(movieShowBuilder);
    }

    @Bean
    SeatFacade seatFacade(SeatRepository seatRepository, SeatBuilder seatBuilder){
        return new SeatFacade(seatRepository, seatBuilder);
    }
}
