package pl.superCinema.backend.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDto {

    private Long id;
    private String seatColumn;
    private Integer seatRow;
    private Integer seatNumber;
}
