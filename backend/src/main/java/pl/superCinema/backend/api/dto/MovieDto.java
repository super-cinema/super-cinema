package pl.superCinema.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDto {
    private Long id;
    private String title;
    private Integer duration;
    private TypeDto genre;

    private String productionCountry;
    private Integer productionYear;
    private MovieShowDto movieShowDto;

    private List<TypeDto> typeDtos;
    private List<CrewDto> directors;
    private List<CrewDto> cast;

}
