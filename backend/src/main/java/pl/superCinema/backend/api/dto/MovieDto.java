package pl.superCinema.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

    private String title;
    private int duration;
    private GenreDto genre;

    private String productionCountry;
    private int productionYear;
}
