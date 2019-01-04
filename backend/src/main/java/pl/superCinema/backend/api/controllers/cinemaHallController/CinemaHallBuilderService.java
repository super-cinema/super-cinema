package pl.superCinema.backend.api.controllers.cinemaHallController;

import lombok.NoArgsConstructor;
import pl.superCinema.backend.api.dto.CinemaHallDto;
import pl.superCinema.backend.domain.model.CinemaHall;

@NoArgsConstructor
public class CinemaHallBuilderService {

    public CinemaHall entityFromDto(CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setId(cinemaHallDto.getId());
        //TODO change implementation when DTO classes will be created
        cinemaHall.setSeats(cinemaHallDto.getSeats());
        cinemaHall.setMovieShows(cinemaHallDto.getMovieShows());
        return cinemaHall;
    }

    public CinemaHallDto dtoFromEntity(CinemaHall cinemaHall) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setId(cinemaHall.getId());
        //TODO change implementation when DTO classes will be created
        cinemaHallDto.setSeats(cinemaHall.getSeats());
        cinemaHallDto.setMovieShows(cinemaHall.getMovieShows());
        return cinemaHallDto;
    }
}
