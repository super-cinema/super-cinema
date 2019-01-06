package pl.superCinema.backend.api.cotrollers.cinemaHallController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.cotrollers.MovieShowController.MovieShowBuilder;
import pl.superCinema.backend.api.cotrollers.seatControlller.SeatBuilder;
import pl.superCinema.backend.api.dto.CinemaHallDto;
import pl.superCinema.backend.api.dto.MovieShowDto;
import pl.superCinema.backend.api.dto.SeatDto;
import pl.superCinema.backend.domain.model.CinemaHall;
import pl.superCinema.backend.domain.model.MovieShow;
import pl.superCinema.backend.domain.model.Seat;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CinemaHallBuilder {

    private SeatBuilder seatBuilder;
    private MovieShowBuilder movieShowBuilder;

    public CinemaHall cinemaHallDtoToCinemaHall(CinemaHallDto cinemaHallDto){
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setId(cinemaHallDto.getId());

        List<Seat> seats = cinemaHallDto.getSeatDtos()
                .stream()
                .map(x -> seatBuilder.seatDtoToSeat(x))
                .collect(Collectors.toList());
        cinemaHall.setSeats(seats);

        List<MovieShow> movieShows = cinemaHallDto.getMovieShowDtos()
                .stream()
                .map(x -> movieShowBuilder.movieShowDtoToMovieShow(x))
                .collect(Collectors.toList());
        cinemaHall.setMovieShows(movieShows);

        return cinemaHall;
    }

    public CinemaHallDto cinemaHallToCinemaHallDto(CinemaHall cinemaHall){
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        cinemaHallDto.setId(cinemaHall.getId());

        List<SeatDto> seatDtos = cinemaHall.getSeats()
                .stream()
                .map(x -> seatBuilder.seatToSeatDto(x))
                .collect(Collectors.toList());
        cinemaHallDto.setSeatDtos(seatDtos);

        List<MovieShowDto> movieShowDtos = cinemaHall.getMovieShows()
                .stream()
                .map(x -> movieShowBuilder.movieShowToMovieShowDto(x))
                .collect(Collectors.toList());
        cinemaHallDto.setMovieShowDtos(movieShowDtos);

        return cinemaHallDto;
    }

}
