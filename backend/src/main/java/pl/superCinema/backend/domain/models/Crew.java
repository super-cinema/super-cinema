package pl.superCinema.backend.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"directedMovies"})
public class Crew {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String surname;

    @ElementCollection(targetClass = CrewRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "crew_and_roles")
    private List<CrewRole> crewRoles;

    @ManyToMany(mappedBy = "directors")
    private List<Movie> directedMovies;

    @ManyToMany(mappedBy = "cast")
    private List<Movie> starredMovies;

    @Override
    public String toString() {
        String directedMoviesString = "[]";
        if (directedMovies != null) {
            directedMoviesString = flatMovieListToString(directedMovies);
        }
        String starredMoviesString = "[]";
        if (starredMovies != null) {
            starredMoviesString = flatMovieListToString(starredMovies);

        }
        return "Crew{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", crewRoles=" + crewRoles +
                ", directedMovies:" + directedMoviesString +
                ", starredMovies" + starredMoviesString +
                '}';
    }

    private String flatMovieListToString(List<Movie> directedMovies) {
        return directedMovies.stream().map(Movie::getTitle).collect(Collectors.joining(",", "{", "}"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crew crew = (Crew) o;
        return Objects.equals(id, crew.id) &&
                Objects.equals(name, crew.name) &&
                Objects.equals(surname, crew.surname) &&
                Objects.equals(crewRoles, crew.crewRoles) &&
                Objects.equals(directedMovies, crew.directedMovies) &&
                Objects.equals(starredMovies, crew.starredMovies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, crewRoles, directedMovies, starredMovies);
    }
}
