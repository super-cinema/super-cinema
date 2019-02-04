package pl.superCinema.backend.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pl.superCinema.backend.domain.models.MovieShow;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class MovieDto {
    private Long id;
    private String title = "";
    private Integer duration;
    private String productionCountry = "";
    private Integer productionYear;

    @JsonProperty("types")
    private List<TypeDto> types;

    private List<CrewDto> directors;
    private List<CrewDto> cast;
    private MovieShow movieShow;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto movieDto = (MovieDto) o;
        return Objects.equals(id, movieDto.id) &&
                Objects.equals(title, movieDto.title) &&
                Objects.equals(duration, movieDto.duration) &&
                Objects.equals(productionCountry, movieDto.productionCountry) &&
                Objects.equals(productionYear, movieDto.productionYear) &&
                Objects.equals(types, movieDto.types) &&
                Objects.equals(directors, movieDto.directors) &&
                Objects.equals(cast, movieDto.cast) &&
                Objects.equals(movieShow, movieDto.movieShow);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, duration, productionCountry, productionYear, types, directors, cast, movieShow);
    }
}
