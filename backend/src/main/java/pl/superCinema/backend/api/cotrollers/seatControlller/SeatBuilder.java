package pl.superCinema.backend.api.cotrollers.seatControlller;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.cotrollers.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.api.dto.SeatDto;
import pl.superCinema.backend.domain.model.Seat;

@AllArgsConstructor
public class SeatBuilder {

    private CinemaHallBuilder cinemaHallBuilder;

    public Seat seatDtoToSeat(SeatDto seatDto) {
        Seat seat = new Seat();
        seat.setId(seatDto.getId());
        seat.setSeatColumn(seatDto.getSeatColumn());
        seat.setSeatRow(seatDto.getSeatRow());
        seat.setSeatNumnber(seatDto.getSeatNumnber());
        seat.setCinemaHall(cinemaHallBuilder.cinemaHallDtoToCinemaHall(seatDto.getCinemaHallDto()));

        return seat;
    }

    public SeatDto seatToSeatDto(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.setId(seat.getId());
        seatDto.setSeatColumn(seat.getSeatColumn());
        seatDto.setSeatRow(seat.getSeatRow());
        seatDto.setSeatNumnber(seat.getSeatNumnber());
        seatDto.setCinemaHallDto(cinemaHallBuilder.cinemaHallToCinemaHallDto(seat.getCinemaHall()));

        return seatDto;
    }

}
