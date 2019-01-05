package pl.superCinema.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatAvailabilityDto {
    private Long id;
    boolean isSeatTaken;
    MovieShowDto movieShowDto;
}
