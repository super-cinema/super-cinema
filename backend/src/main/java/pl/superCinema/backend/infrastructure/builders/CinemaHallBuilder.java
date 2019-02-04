package pl.superCinema.backend.infrastructure.builders;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.infrastructure.dto.CinemaHallDto;
import pl.superCinema.backend.infrastructure.dto.SeatDto;
import pl.superCinema.backend.domain.models.CinemaHall;
import pl.superCinema.backend.domain.models.Seat;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CinemaHallBuilder {

    private SeatBuilder seatBuilder;

    public CinemaHallDto entityToDto(CinemaHall cinemaHall) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setId(cinemaHall.getId());

        List<Seat> seats = cinemaHall.getSeats();
        if (seats != null) {
            List<SeatDto> seatDtos = seats.stream()
                    .map(seat -> seatBuilder.entityToDto(seat))
                    .collect(Collectors.toList());
            cinemaHallDto.setSeats(seatDtos);
        }

        //TODO change implementation when DTO classes will be created
        cinemaHallDto.setMovieShows(cinemaHall.getMovieShows());
        return cinemaHallDto;
    }

    public CinemaHall dtoToEntity(CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setId(cinemaHallDto.getId());
        //TODO change implementation when DTO classes will be created
        cinemaHall.setMovieShows(cinemaHallDto.getMovieShows());
        return cinemaHall;
    }
}
