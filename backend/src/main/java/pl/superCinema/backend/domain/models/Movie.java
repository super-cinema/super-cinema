package pl.superCinema.backend.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
public class Movie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer duration;
    private String productionCountry;
    private Integer productionYear;

    @OneToOne
    MovieShow movieShow;

    @ElementCollection(targetClass = Type.class)
    @Enumerated(EnumType.STRING)
    private List<Type> types;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "MOVIE_DIRECTORS",
            joinColumns = {@JoinColumn(name = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CREW_ID")})
    private List<Crew> directors;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "MOVIE_CAST",
            joinColumns = {@JoinColumn(name = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STAR_ID")})
    private List<Crew> cast;


    @Override
    public String toString() {
        String directorsToString = "[]";
        if (directors != null) {
            directorsToString = directors.stream().map(Crew::getName).collect(Collectors.joining(",", "{", "}"));
        }
        String castToString = "[]";
        if (cast != null) {
            castToString = cast.stream().map(Crew::getName).collect(Collectors.joining(",", "{", "}"));
        }
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", productionCountry='" + productionCountry + '\'' +
                ", productionYear=" + productionYear +
                ", movieShow=" + "movie show" +
                ", types=" + "types" +
                ", directors=" + directorsToString +
                ", cast=" + castToString +
                '}';
    }
}
