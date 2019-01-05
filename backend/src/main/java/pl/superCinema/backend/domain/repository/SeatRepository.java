package pl.superCinema.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.model.Seat;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}
