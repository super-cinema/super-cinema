package pl.superCinema.backend.api.cotrollers.seatAvailabilityControlller;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.domain.repository.SeatAvailabilityRepository;

@AllArgsConstructor
public class SeatAvailabilityFacade {

    private SeatAvailabilityRepository seatAvailabilityRepository;
    private SeatAvailabilityBuilder seatAvailabilityBuilder;
}
