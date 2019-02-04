package pl.superCinema.backend.domain.facades;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.infrastructure.builders.CinemaHallBuilder;
import pl.superCinema.backend.infrastructure.builders.SeatBuilder;
import pl.superCinema.backend.infrastructure.dto.CinemaHallDto;
import pl.superCinema.backend.infrastructure.dto.SeatDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.models.CinemaHall;
import pl.superCinema.backend.domain.models.Seat;
import pl.superCinema.backend.domain.ports.CinemaHallRepository;
import pl.superCinema.backend.domain.ports.SeatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CinemaHallFacade {

    private CinemaHallRepository cinemaHallRepository;
    private CinemaHallBuilder cinemaHallBuilder;
    private SeatBuilder seatBuilder;
    private SeatRepository seatRepository;

    public CinemaHallDto saveCinemaHall(CinemaHallDto cinemaHallDto) {
        List<SeatDto> seatsDtos = cinemaHallDto.getSeats();
        List<Seat> seats = new ArrayList<>();
        if (seatsDtos != null) {
            seats = seatsDtos.stream()
                    .map(seatDto -> {
                        Seat seat = seatBuilder.dtoToEntity(seatDto);
                        return seatRepository.save(seat);
                    })
                    .collect(Collectors.toList());
        }

        CinemaHall cinemaHall = cinemaHallBuilder.dtoToEntity(cinemaHallDto);
        cinemaHall.setSeats(seats);
        CinemaHall cinemaHallSaved = cinemaHallRepository.save(cinemaHall);
        seats.stream()
                .map(seat -> {
                    Optional<Seat> seatOptional = seatRepository.findById(seat.getId());
                    if (seatOptional.isPresent()) {
                        seat.setCinemaHall(cinemaHallSaved);
                        seatRepository.save(seat);
                    }
                    return seat;
                })
                .collect(Collectors.toList());
        cinemaHallSaved.setSeats(seats);
        CinemaHall save = cinemaHallRepository.save(cinemaHallSaved);
        return cinemaHallBuilder.entityToDto(save);
    }

    public CinemaHallDto getCinemaHallById(Long id) {
        CinemaHall cinemaHallEntity = findCinemaHallEntity(id);
        return cinemaHallBuilder.entityToDto(cinemaHallEntity);
    }

    public CinemaHallDto editCinemaHallById(Long id, CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHallEntity = findCinemaHallEntity(id);
        List<Seat> seats = cinemaHallDto.getSeats()
                .stream()
                .map(seatDto -> seatBuilder.dtoToEntity(seatDto))
                .collect(Collectors.toList());
        if (seats != null) {
            cinemaHallEntity.setSeats(seats);
        }
        if (cinemaHallDto.getMovieShows() != null) {
            cinemaHallEntity.setMovieShows(cinemaHallDto.getMovieShows());
        }

        CinemaHall cinemaHallSaved = cinemaHallRepository.save(cinemaHallEntity);
        return cinemaHallBuilder.entityToDto(cinemaHallSaved);
    }

    public void deleteCinemaHallById(Long id) {
        cinemaHallRepository.deleteById(id);
    }

    private CinemaHall findCinemaHallEntity(Long id) {
        return cinemaHallRepository.findById(id).orElseThrow(
                () -> new EntityCouldNotBeFoundException("cinemaHall by id: " + id + " not found")
        );
    }
}
