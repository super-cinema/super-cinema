package pl.superCinema.backend.api.cotrollers.seatAvailabilityControlller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.superCinema.backend.api.cotrollers.MovieShowController.MovieShowBuilder;
import pl.superCinema.backend.domain.repository.SeatAvailabilityRepository;

@Configuration
public class SeatAvailabilityConfiguration {

    @Bean
    SeatAvailabilityBuilder seatAvailabilityBuilder(@Lazy MovieShowBuilder movieShowBuilder){
        return new SeatAvailabilityBuilder(movieShowBuilder);
    }

    @Bean
    SeatAvailabilityFacade seatAvailabilityFacade(@Lazy SeatAvailabilityRepository seatAvailabilityRepository,@Lazy SeatAvailabilityBuilder seatAvailabilityBuilder){
        return new SeatAvailabilityFacade(seatAvailabilityRepository,seatAvailabilityBuilder);
    }


}
