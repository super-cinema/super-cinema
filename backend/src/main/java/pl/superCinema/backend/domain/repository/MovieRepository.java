package pl.superCinema.backend.domain.model;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Movie,Long> {
    //todo przepisac do basemodel i miec ktory bedzie rozszezany przez rezte i tutaj wlozyc



}
