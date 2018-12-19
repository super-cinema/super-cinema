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
    private String title;
    private Integer duration;
    private String productionCountry;
    private Integer productionYear;

    //chyba jak jest relacja cos do wielu to musi tak byc
    @ElementCollection(targetClass = Genre.class)
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MOVIE_DIRECTORS",
    joinColumns = {@JoinColumn(name = "MOVIE_ID")},
    inverseJoinColumns = {@JoinColumn(name = "CREW_ID")})
    List<Crew> directors;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MOVIE_CAST",
            joinColumns = {@JoinColumn(name = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STAR_ID")})
    List<Crew> cast; //ta zmienna musi byc taka sama  jak przy mapped by w Crew

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MOVIE_AND_CINEMA_HALLS",
            joinColumns = {@JoinColumn(name = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CINEMA_HALL_ID")})
    List<CinemaHall> cinemaHalls;

}
