package pl.superCinema.backend.api.cotroller.seatControlller;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.cotroller.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.api.dto.SeatDto;
import pl.superCinema.backend.domain.model.Seat;

@AllArgsConstructor
public class SeatBuilder {

    private CinemaHallBuilder cinemaHallBuilder;

    public Seat seatDtoToSeat(SeatDto seatDto) {
        Seat seat = new Seat();
        seat.setId(seatDto.getId());
        seat.setColumn(seatDto.getColumn());
        seat.setRow(seatDto.getRow());
        seat.setSeatNumnber(seatDto.getSeatNumnber());
        seat.setCinemaHall(cinemaHallBuilder.cinemaHallDtoToCinemaHall(seatDto.getCinemaHallDto()));

        return seat;
    }

    public SeatDto seatToSeatDto(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.setId(seat.getId());
        seatDto.setColumn(seat.getColumn());
        seatDto.setRow(seat.getRow());
        seatDto.setSeatNumnber(seat.getSeatNumnber());
        seatDto.setCinemaHallDto(cinemaHallBuilder.cinemaHallToCinemaHallDto(seat.getCinemaHall()));

        return seatDto;
    }

}
