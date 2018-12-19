package pl.superCinema.backend.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class SeatAvailability {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    boolean isSeatTaken;

    @ManyToOne
    MovieShow movieShow;


}
