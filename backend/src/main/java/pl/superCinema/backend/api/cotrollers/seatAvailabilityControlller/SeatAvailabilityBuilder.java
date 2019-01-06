package pl.superCinema.backend.api.cotrollers.seatAvailabilityControlller;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.cotrollers.MovieShowController.MovieShowBuilder;
import pl.superCinema.backend.api.dto.SeatAvailabilityDto;
import pl.superCinema.backend.domain.model.SeatAvailability;

@AllArgsConstructor
public class SeatAvailabilityBuilder {
    private MovieShowBuilder movieShowBuilder;

    public SeatAvailability seatAvailabilityDtoToSeatAvailability(SeatAvailabilityDto seatAvailabilityDto) {
        SeatAvailability seatAvailability = new SeatAvailability();
        seatAvailability.setId(seatAvailabilityDto.getId());
        seatAvailability.setSeatTaken(seatAvailabilityDto.isSeatTaken());
        seatAvailability.setMovieShow(movieShowBuilder.movieShowDtoToMovieShow(seatAvailabilityDto.getMovieShowDto()));

        return seatAvailability;
    }

    public SeatAvailabilityDto seatAvailabilityToSeatAvailabilityDto(SeatAvailability seatAvailability) {
        SeatAvailabilityDto seatAvailabilityDto = new SeatAvailabilityDto();
        seatAvailabilityDto.setId(seatAvailability.getId());
        seatAvailabilityDto.setSeatTaken(seatAvailability.isSeatTaken());
        seatAvailabilityDto.setMovieShowDto(movieShowBuilder.movieShowToMovieShowDto(seatAvailability.getMovieShow()));

        return seatAvailabilityDto;
    }
}