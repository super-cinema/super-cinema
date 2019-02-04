package pl.superCinema.backend.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"seats"})
public class CinemaHall {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "cinemaHall", cascade = CascadeType.ALL)
    private List<Seat> seats;

    @ManyToMany(mappedBy = "cinemaHalls")
    private List<MovieShow> movieShows;


}
