package pl.superCinema.backend.infrastructure.builders;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.infrastructure.dto.SeatAvailabilityDto;
import pl.superCinema.backend.domain.models.SeatAvailability;

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