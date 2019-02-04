package pl.superCinema.backend.domain.models;

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

    private String seatColumn;
    private Integer seatRow;
    private Integer seatNumber;

    @ManyToOne
    @JoinColumn(name = "CINEMA_HALL_ID")
    private CinemaHall cinemaHall;
}
