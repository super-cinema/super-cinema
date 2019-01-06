package pl.superCinema.backend.api.controllers.cinemaHallController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.superCinema.backend.api.controllers.seatController.SeatBuilder;
import pl.superCinema.backend.api.dto.CinemaHallDto;
import pl.superCinema.backend.api.dto.SeatDto;
import pl.superCinema.backend.domain.model.CinemaHall;
import pl.superCinema.backend.domain.model.Seat;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CinemaHallBuilder {

    private SeatBuilder seatBuilder;

    public CinemaHall entityFromDto(CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setId(cinemaHallDto.getId());
        //TODO change implementation when DTO classes will be created
        cinemaHall.setMovieShows(cinemaHallDto.getMovieShows());
        return cinemaHall;
    }

    public CinemaHallDto dtoFromEntity(CinemaHall cinemaHall) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setId(cinemaHall.getId());

        List<Seat> seats = cinemaHall.getSeats();
        if(seats != null) {
            List<SeatDto> seatDtos = seats.stream()
                    .map(seat -> seatBuilder.dtoFromEntity(seat))
                    .collect(Collectors.toList());
            cinemaHallDto.setSeats(seatDtos);
        }

        //TODO change implementation when DTO classes will be created
        cinemaHallDto.setMovieShows(cinemaHall.getMovieShows());
        return cinemaHallDto;
    }
}
