package pl.superCinema.backend.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieShowDto {
    private Long id;
    private LocalDate startMovieShow;
    private LocalDate endMovieShow;
    private MovieDto movieDto;
    private List<CinemaHallDto> cinemaHallDtos;
    private List<SeatAvailabilityDto> seatAvailabilityDtos;
}
