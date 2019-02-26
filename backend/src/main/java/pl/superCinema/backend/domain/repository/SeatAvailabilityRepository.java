package pl.superCinema.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.models.SeatAvailability;

public interface SeatAvailabilityRepository extends JpaRepository<SeatAvailability, Long> {
}
