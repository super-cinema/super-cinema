package pl.superCinema.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDto {
    private Long id;
    private String seatColumn;
    private Integer seatRow;
    private Integer seatNumnber;
    private CinemaHallDto cinemaHallDto;

}
