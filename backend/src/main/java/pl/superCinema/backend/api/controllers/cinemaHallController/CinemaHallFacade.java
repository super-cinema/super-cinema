package pl.superCinema.backend.api.controllers.cinemaHallController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.superCinema.backend.api.controllers.seatController.SeatBuilderService;
import pl.superCinema.backend.api.dto.CinemaHallDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.model.CinemaHall;
import pl.superCinema.backend.domain.model.Seat;
import pl.superCinema.backend.domain.repository.CinemaHallRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CinemaHallFacade {

    private CinemaHallRepository cinemaHallRepository;
    private CinemaHallBuilderService cinemaHallBuilderService;
    private SeatBuilderService seatBuilderService;

    public CinemaHallDto saveCinemaHall(CinemaHallDto cinemaHallDto){
        CinemaHall cinemaHall = cinemaHallBuilderService.entityFromDto(cinemaHallDto);
        CinemaHall cinemaHallSaved = cinemaHallRepository.save(cinemaHall);
        return cinemaHallBuilderService.dtoFromEntity(cinemaHallSaved);
    }

    public CinemaHallDto getCinemaHallById(Long id){
        CinemaHall cinemaHallEntity = findCinemaHallEntity(id);
        return cinemaHallBuilderService.dtoFromEntity(cinemaHallEntity);
    }

    public CinemaHallDto editCinemaHallById(Long id, CinemaHallDto cinemaHallDto){
        CinemaHall cinemaHallEntity = findCinemaHallEntity(id);
        List<Seat> seats = cinemaHallDto.getSeats()
                .stream()
                .map(seatDto -> seatBuilderService.entityFromDto(seatDto))
                .collect(Collectors.toList());
        if(seats != null){
            cinemaHallEntity.setSeats(seats);
        }
        if(cinemaHallDto.getMovieShows() != null) {
            cinemaHallEntity.setMovieShows(cinemaHallDto.getMovieShows());
        }

        CinemaHall cinemaHallSaved = cinemaHallRepository.save(cinemaHallEntity);
        return cinemaHallBuilderService.dtoFromEntity(cinemaHallSaved);
    }

    public void deleteCInemaHallById(Long id){
        cinemaHallRepository.deleteById(id);
    }

    private CinemaHall findCinemaHallEntity(Long id) {
        CinemaHall cinemaHall = cinemaHallRepository.findById(id).orElseThrow(
                () -> new EntityCouldNotBeFoundException("cinemaHall by id: " + id + " not found")
        );
        return cinemaHall;
    }
}
