package pl.superCinema.backend.api.controllers.seatController;

import lombok.NoArgsConstructor;
import pl.superCinema.backend.api.dto.SeatDto;
import pl.superCinema.backend.domain.model.Seat;

@NoArgsConstructor
public class SeatBuilder {

    public SeatDto entityToDto(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.setId(seat.getId());
        seatDto.setSeatColumn(seat.getSeatColumn());
        seatDto.setSeatRow(seat.getSeatRow());
        seatDto.setSeatNumber(seat.getSeatNumber());
        return seatDto;
    }

    public Seat dtoToEntity(SeatDto seatDto) {
        Seat seat = new Seat();
        seat.setId(seatDto.getId());
        seat.setSeatColumn(seatDto.getSeatColumn());
        seat.setSeatRow(seatDto.getSeatRow());
        seat.setSeatNumber(seatDto.getSeatNumber());

        return seat;
    }

}
