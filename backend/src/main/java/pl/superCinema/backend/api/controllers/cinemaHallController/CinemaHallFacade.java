package pl.superCinema.backend.api.controllers.cinemaHallController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.dto.CinemaHallDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.model.CinemaHall;
import pl.superCinema.backend.domain.repository.CinemaHallRepository;

@AllArgsConstructor
public class CinemaHallFacade {

    CinemaHallRepository cinemaHallRepository;
    CinemaHallBuilderService cinemaHallBuilderService;

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
        cinemaHallEntity.setSeats(cinemaHallDto.getSeats());
        cinemaHallEntity.setMovieShows(cinemaHallDto.getMovieShows());
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
