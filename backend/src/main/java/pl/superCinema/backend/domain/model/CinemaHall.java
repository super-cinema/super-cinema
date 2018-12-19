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

    @ManyToMany(mappedBy = "cinemaHalls")
    List<Movie> movies;

//    @OneToMany(mappedBy = "cinemaHall")
//    private List<Seat> seats;



}
