package pl.superCinema.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CinemaHallDto {

private Long id;
private List<SeatDto> seatDtos;
private List<MovieShowDto> movieShowDtos;
    

}
