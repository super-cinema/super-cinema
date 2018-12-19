package pl.superCinema.backend.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Seat {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String column;
    private Integer row;
    private Integer seatNumnber;

    @ManyToOne
    @JoinColumn(name = "CINEMA_HALL_ID")
    private CinemaHall cinemaHall;
}
