package pl.superCinema.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.models.MovieShow;

public interface MovieShowRepository extends JpaRepository<MovieShow, Long> {
}
