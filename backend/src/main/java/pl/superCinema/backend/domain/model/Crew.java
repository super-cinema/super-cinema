package pl.superCinema.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value = { "directedMovies" })
public class Crew {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String surname;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = CrewRole.class)
    @Enumerated(EnumType.STRING)
    private List<CrewRole> crewRoles;


    @ManyToMany(mappedBy = "directors")
    List<Movie> directedMovies;

    @ManyToMany(mappedBy = "cast")
    List<Movie> starredMovies;

    @Override
    public String toString() {
        String directedMoviesString = "[]";
        if(directedMovies != null) {
            directedMoviesString = directedMovies.stream().map(directedMovie -> directedMovie.getTitle()).toString();
        }
        String starredMoviesString = "[]";
        if(starredMovies != null) {
            starredMoviesString = starredMovies.stream().map(starredMovie -> starredMovie.getTitle()).toString();
        }
        return "Crew{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", crewRoles=" + crewRoles +
                "directedMovies:" + directedMoviesString +
                "starredMovies" + starredMoviesString +
                '}';
    }
}
