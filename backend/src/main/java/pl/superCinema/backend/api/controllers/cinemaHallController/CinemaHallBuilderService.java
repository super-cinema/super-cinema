package pl.superCinema.backend.api.controllers.cinemaHallController;

import lombok.NoArgsConstructor;
import pl.superCinema.backend.api.dto.CinemaHallDto;
import pl.superCinema.backend.domain.model.CinemaHall;

@NoArgsConstructor
public class CinemaHallBuilderService {

    public CinemaHall entityFromDto(CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setId(cinemaHallDto.getId());

        cinemaHall.setSeats(cinemaHallDto.getSeats());
        cinemaHall.setMovieShows(cinemaHallDto.getMovieShows());
        return cinemaHall;
    }

    public CinemaHallDto dtoFromEntity(CinemaHall cinemaHall) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setId(cinemaHall.getId());
        cinemaHallDto.setSeats(cinemaHall.getSeats());
        cinemaHallDto.setMovieShows(cinemaHall.getMovieShows());
        return cinemaHallDto;
    }
}
