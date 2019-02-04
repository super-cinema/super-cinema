package pl.superCinema.backend.domain.facades;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.domain.ports.SeatAvailabilityRepository;
import pl.superCinema.backend.infrastructure.builders.SeatAvailabilityBuilder;

@AllArgsConstructor
public class SeatAvailabilityFacade {

    private SeatAvailabilityRepository seatAvailabilityRepository;
    private SeatAvailabilityBuilder seatAvailabilityBuilder;
}
