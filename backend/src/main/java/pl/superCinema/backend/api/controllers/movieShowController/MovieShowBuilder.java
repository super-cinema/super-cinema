package pl.superCinema.backend.api.controllers.movieShowController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.controllers.cinemaHallController.CinemaHallBuilder;
import pl.superCinema.backend.api.controllers.movieController.MovieBuilder;
import pl.superCinema.backend.api.controllers.seatAvailabilityControlller.SeatAvailabilityBuilder;
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

    public MovieShowDto entityToDto(MovieShow movieShow) {
        MovieShowDto movieShowDto = new MovieShowDto();

        movieShowDto.setId(movieShow.getId());
        movieShowDto.setStartMovieShow(movieShow.getStartMovieShow());
        movieShowDto.setEndMovieShow(movieShow.getEndMovieShow());
        movieShowDto.setMovieDto(movieBuilder.entityToDto(movieShow.getMovie()));

        List<CinemaHallDto> cinemaHallDtos = movieShow.getCinemaHalls()
                .stream()
                .map(x -> cinemaHallBuilder.entityToDto(x))
                .collect(Collectors.toList());
        movieShowDto.setCinemaHallDtos(cinemaHallDtos);

        List<SeatAvailabilityDto> seatAvailabilityDtos = movieShow.getSeatsAvailability()
                .stream()
                .map(x -> seatAvailabilityBuilder.entityToDto(x))
                .collect(Collectors.toList());
        movieShowDto.setSeatAvailabilityDtos(seatAvailabilityDtos);

        return movieShowDto;
    }

    public MovieShow dtoToEntity(MovieShowDto movieShowDto) {
        MovieShow movieShow = new MovieShow();
        movieShow.setId(movieShowDto.getId());
        movieShow.setStartMovieShow(movieShowDto.getStartMovieShow());
        movieShow.setEndMovieShow(movieShowDto.getEndMovieShow());
        movieShow.setMovie(movieBuilder.dtoToEntity(movieShowDto.getMovieDto()));

        List<CinemaHall> cinemaHalls = movieShowDto.getCinemaHallDtos()
                .stream()
                .map(x -> cinemaHallBuilder.dtoToEntity(x))
                .collect(Collectors.toList());
        movieShow.setCinemaHalls(cinemaHalls);

        List<SeatAvailability> seatAvailabilities = movieShowDto.getSeatAvailabilityDtos()
                .stream()
                .map(x -> seatAvailabilityBuilder.dtoToEntity(x))
                .collect(Collectors.toList());
        movieShow.setSeatsAvailability(seatAvailabilities);

        return movieShow;
    }

}
