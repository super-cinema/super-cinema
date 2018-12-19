package pl.superCinema.backend.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Crew {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private CrewRole crewRole;


    @ManyToMany(mappedBy = "directors")
    List<Movie> directedMovies;

    @ManyToMany(mappedBy = "cast")
    List<Movie> starredMovies;
}
