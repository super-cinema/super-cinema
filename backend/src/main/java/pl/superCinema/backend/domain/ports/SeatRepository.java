package pl.superCinema.backend.domain.ports;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.models.Seat;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findBySeatNumber(Long number);

}
