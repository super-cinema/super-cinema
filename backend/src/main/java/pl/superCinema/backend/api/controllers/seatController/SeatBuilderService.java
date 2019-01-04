package pl.superCinema.backend.api.controllers.seatController;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.superCinema.backend.api.controllers.cinemaHallController.CinemaHallBuilderService;
import pl.superCinema.backend.api.dto.CinemaHallDto;
import pl.superCinema.backend.api.dto.SeatDto;
import pl.superCinema.backend.domain.model.CinemaHall;
import pl.superCinema.backend.domain.model.Seat;

@NoArgsConstructor
public class SeatBuilderService {
    @Autowired
    CinemaHallBuilderService cinemaHallBuilderService;

    public Seat entityFromDto(SeatDto seatDto) {
        Seat seat = new Seat();
        seat.setId(seatDto.getId());
        seat.setSeatColumn(seatDto.getSeatColumn());
        seat.setSeatRow(seatDto.getSeatRow());
        seat.setSeatNumber(seatDto.getSeatNumber());
        CinemaHallDto cinemaHall = seatDto.getCinemaHall();
        if(cinemaHall != null) {
            seat.setCinemaHall(cinemaHallBuilderService.entityFromDto(seatDto.getCinemaHall()));
        }
        return seat;
    }

    public SeatDto dtoFromEntity(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.setId(seat.getId());
        seatDto.setSeatColumn(seat.getSeatColumn());
        seatDto.setSeatRow(seat.getSeatRow());
        seatDto.setSeatNumber(seat.getSeatNumber());
        CinemaHall cinemaHall = seat.getCinemaHall();
        if(cinemaHall != null) {
            seatDto.setCinemaHall(cinemaHallBuilderService.dtoFromEntity(seat.getCinemaHall()));
        }
        return seatDto;
    }
}
