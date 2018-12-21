package pl.superCinema.backend.api.cotroller.seatControlller;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.domain.repository.SeatRepository;

@AllArgsConstructor
public class SeatFacade {
    private SeatRepository seatRepository;
    private SeatBuilder seatBuilder;
}
