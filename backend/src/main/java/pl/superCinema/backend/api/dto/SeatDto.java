package pl.superCinema.backend.api.dto;

import lombok.Getter;
import lombok.Setter;
import pl.superCinema.backend.domain.model.CinemaHall;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class SeatDto {

    private Long id;
    private String seatColumn;
    private Integer seatRow;
    private Integer seatNumber;

    private CinemaHallDto cinemaHall;

}
