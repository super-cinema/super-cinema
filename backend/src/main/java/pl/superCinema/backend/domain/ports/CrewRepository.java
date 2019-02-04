package pl.superCinema.backend.domain.ports;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.models.Crew;

public interface CrewRepository extends JpaRepository<Crew, Long> {
}
