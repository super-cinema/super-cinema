package pl.superCinema.backend.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @ElementCollection(targetClass = Type.class)
    @Enumerated(EnumType.STRING)
    private List<Type> types;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MOVIE_DIRECTORS",
            joinColumns = {@JoinColumn(name = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CREW_ID")})
    private List<Crew> directors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MOVIE_CAST",
            joinColumns = {@JoinColumn(name = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STAR_ID")})
    private List<Crew> cast;

    @OneToOne
    private MovieShow movieShow;

}
