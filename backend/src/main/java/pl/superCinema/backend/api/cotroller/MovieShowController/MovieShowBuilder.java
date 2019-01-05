package pl.superCinema.backend.api.cotroller.MovieShowController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.cotroller.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.api.cotroller.movieController.MovieBuilder;
import pl.superCinema.backend.api.cotroller.seatAvailabilityControlller.SeatAvailabilityBuilder;
import pl.superCinema.backend.api.dto.CinemaHallDto;
import pl.superCinema.backend.api.dto.MovieShowDto;
import pl.superCinema.backend.api.dto.SeatAvailabilityDto;
import pl.superCinema.backend.domain.model.CinemaHall;
import pl.superCinema.backend.domain.model.MovieShow;
import pl.superCinema.backend.domain.model.SeatAvailability;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
public class MovieShowBuilder {

    private MovieBuilder movieBuilder;
    private CinemaHallBuilder cinemaHallBuilder;
    private SeatAvailabilityBuilder seatAvailabilityBuilder;

    public MovieShow movieShowDtoToMovieShow(MovieShowDto movieShowDto) {
        MovieShow movieShow = new MovieShow();
        movieShow.setId(movieShowDto.getId());
        movieShow.setStartMovieShow(movieShowDto.getStartMovieShow());
        movieShow.setEndMovieShow(movieShowDto.getEndMovieShow());
        movieShow.setMovie(movieBuilder.movieDtoToMovie(movieShowDto.getMovieDto()));

        List<CinemaHall> cinemaHalls = movieShowDto.getCinemaHallDtos()
                .stream()
                .map(x -> cinemaHallBuilder.cinemaHallDtoToCinemaHall(x))
                .collect(Collectors.toList());
        movieShow.setCinemaHalls(cinemaHalls);

        List<SeatAvailability> seatAvailabilities = movieShowDto.getSeatAvailabilityDtos()
                .stream()
                .map(x -> seatAvailabilityBuilder.seatAvailabilityDtoToSeatAvailability(x))
                .collect(Collectors.toList());
        movieShow.setSeatsAvailability(seatAvailabilities);

        return movieShow;
    }


    public MovieShowDto movieShowToMovieShowDto(MovieShow movieShow) {
        MovieShowDto movieShowDto = new MovieShowDto();

        movieShowDto.setId(movieShow.getId());
        movieShowDto.setStartMovieShow(movieShow.getStartMovieShow());
        movieShowDto.setEndMovieShow(movieShow.getEndMovieShow());
        movieShowDto.setMovieDto(movieBuilder.movieToMovieDto(movieShow.getMovie()));

        List<CinemaHallDto> cinemaHallDtos = movieShow.getCinemaHalls()
                .stream()
                .map(x -> cinemaHallBuilder.cinemaHallToCinemaHallDto(x))
                .collect(Collectors.toList());
        movieShowDto.setCinemaHallDtos(cinemaHallDtos);

        List<SeatAvailabilityDto> seatAvailabilityDtos = movieShow.getSeatsAvailability()
                .stream()
                .map(x -> seatAvailabilityBuilder.seatAvailabilityToSeatAvailabilityDto(x))
                .collect(Collectors.toList());
        movieShowDto.setSeatAvailabilityDtos(seatAvailabilityDtos);

        return movieShowDto;

    }


}
