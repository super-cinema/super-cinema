package pl.superCinema.backend.infrastructure.builders;


import lombok.AllArgsConstructor;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.infrastructure.dto.MovieDto;
import pl.superCinema.backend.infrastructure.dto.TypeDto;
import pl.superCinema.backend.domain.models.Crew;
import pl.superCinema.backend.domain.models.Movie;
import pl.superCinema.backend.domain.models.Type;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MovieBuilder {

    private CrewBuilder crewBuilder;

    public Movie dtoToEntity(MovieDto movieDto) {

        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDuration(movieDto.getDuration());
        movie.setProductionCountry(movieDto.getProductionCountry());
        movie.setProductionYear(movieDto.getProductionYear());
        //set types
        if (movieDto.getTypes() != null) {
            List<Type> typeList = movieDto.getTypes()
                    .stream()
                    .map(type -> Type.valueOf(type.name()))
                    .collect(Collectors.toList());
            movie.setTypes(typeList);
        }
        //set actors
        if (movieDto.getCast() != null) {
            List<Crew> crewList = movieDto.getCast()
                    .stream()
                    .map(actor -> crewBuilder.dtoToEntity(actor))
                    .collect(Collectors.toList());
            movie.setCast(crewList);
        }
        //set directors
        if (movieDto.getDirectors() != null) {
            List<Crew> directors = movieDto.getDirectors()
                    .stream()
                    .map(director -> crewBuilder.dtoToEntity(director))
                    .collect(Collectors.toList());
            movie.setDirectors(directors);
        }
        if (movieDto.getMovieShow() != null) {
            movie.setMovieShow(movieDto.getMovieShow());
        }

        return movie;
    }

    public Movie dtoToBasicEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDuration(movieDto.getDuration());
        movie.setProductionCountry(movieDto.getProductionCountry());
        movie.setProductionYear(movieDto.getProductionYear());
        //set types
        if (movieDto.getTypes() != null) {
            List<Type> typeList = movieDto.getTypes()
                    .stream()
                    .map(type -> Type.valueOf(type.name()))
                    .collect(Collectors.toList());
            movie.setTypes(typeList);
        }
        return movie;
    }

    public MovieDto entityToDto(Movie movie) {

        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDuration(movie.getDuration());
        movieDto.setProductionCountry(movie.getProductionCountry());
        movieDto.setProductionYear(movie.getProductionYear());

        //set types
        if (movie.getTypes() != null) {
            List<TypeDto> typeDtos = movie.getTypes()
                    .stream()
                    .map(type -> TypeDto.valueOf(type.name()))
                    .collect(Collectors.toList());
            movieDto.setTypes(typeDtos);
        }
        //set actors
        if (movie.getCast() != null) {
            List<CrewDto> actorsDtoList = movie.getCast()
                    .stream()
                    .map(actor -> crewBuilder.entityToDto(actor))
                    .collect(Collectors.toList());
            movieDto.setCast(actorsDtoList);
        }
        //set directors
        if (movie.getDirectors() != null) {
            List<CrewDto> directorsDto = movie.getDirectors()
                    .stream()
                    .map(director -> crewBuilder.entityToDto(director))
                    .collect(Collectors.toList());
            movieDto.setDirectors(directorsDto);
        }

        movieDto.setMovieShow(movie.getMovieShow());

        return movieDto;

    }

}
