package pl.superCinema.backend.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.model.Movie;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    Optional<Movie> findByTitle(String title);



}
