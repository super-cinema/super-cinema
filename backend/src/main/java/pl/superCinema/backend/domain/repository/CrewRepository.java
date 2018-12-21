package pl.superCinema.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.model.Crew;

public interface CrewRepository extends JpaRepository<Crew,Long> {
}
