package pl.superCinema.backend.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.model.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {



}
