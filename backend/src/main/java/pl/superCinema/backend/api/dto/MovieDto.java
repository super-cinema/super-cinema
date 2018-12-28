package pl.superCinema.backend.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.MovieShow;
import pl.superCinema.backend.domain.model.Type;

import java.lang.reflect.Field;
import java.util.List;

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
    private List<Crew> directors;
    private List<Crew> cast;
    private MovieShow movieShow;

}
