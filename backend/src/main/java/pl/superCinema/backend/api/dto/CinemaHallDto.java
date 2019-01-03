package pl.superCinema.backend.api.dto;

import lombok.Getter;
import lombok.Setter;
import pl.superCinema.backend.domain.model.MovieShow;
import pl.superCinema.backend.domain.model.Seat;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
public class CinemaHallDto {

    private Long id;
    private List<Seat> seats;
    private List<MovieShow> movieShows;

    

}
