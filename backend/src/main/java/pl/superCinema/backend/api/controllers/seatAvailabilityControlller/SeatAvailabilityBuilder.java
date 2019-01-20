package pl.superCinema.backend.api.controllers.seatAvailabilityControlller;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.controllers.movieShowController.MovieShowBuilder;
import pl.superCinema.backend.api.dto.SeatAvailabilityDto;
import pl.superCinema.backend.domain.model.SeatAvailability;

@AllArgsConstructor
public class SeatAvailabilityBuilder {


    public SeatAvailabilityDto entityToDto(SeatAvailability seatAvailability) {
        SeatAvailabilityDto seatAvailabilityDto = new SeatAvailabilityDto();
        seatAvailabilityDto.setId(seatAvailability.getId());
        seatAvailabilityDto.setSeatTaken(seatAvailability.isSeatTaken());

        return seatAvailabilityDto;
    }

    public SeatAvailability dtoToEntity(SeatAvailabilityDto seatAvailabilityDto) {
        SeatAvailability seatAvailability = new SeatAvailability();
        seatAvailability.setId(seatAvailabilityDto.getId());
        seatAvailability.setSeatTaken(seatAvailabilityDto.isSeatTaken());

        return seatAvailability;
    }

}