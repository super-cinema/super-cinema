package pl.superCinema.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.models.CinemaHall;

public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {



}
