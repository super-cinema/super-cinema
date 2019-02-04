package pl.superCinema.backend.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;
import pl.superCinema.backend.domain.models.MovieShow;
import java.util.List;

@Getter
@Setter
public class CinemaHallDto {

    private Long id;
    private List<SeatDto> seats;

    //TODO when MovieShowDto will be implemented
    private List<MovieShow> movieShows;

    

}
