package pl.superCinema.backend.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class CinemaHall {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "cinemaHall")
    private List<Seat> seats;

    @ManyToMany(mappedBy = "cinemaHalls")
    List<MovieShow> movieShows;



}
