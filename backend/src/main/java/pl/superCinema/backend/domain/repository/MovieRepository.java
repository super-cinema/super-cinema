package pl.superCinema.backend.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.superCinema.backend.domain.model.Movie;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    //todo przepisac do basemodel i miec ktory bedzie rozszezany przez rezte i tutaj wlozyc

    Optional<Movie> findByTitle(String title);



}
