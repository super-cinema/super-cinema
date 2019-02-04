package pl.superCinema.backend.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class SeatAvailability {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    boolean isSeatTaken;

    @ManyToOne
    private MovieShow movieShow;


}
