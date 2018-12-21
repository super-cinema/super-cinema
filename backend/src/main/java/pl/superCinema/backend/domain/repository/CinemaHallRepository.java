package pl.superCinema.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.model.CinemaHall;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {
}
