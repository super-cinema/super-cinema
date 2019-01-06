package pl.superCinema.backend.api.controllers.movieController;


import lombok.NoArgsConstructor;
import pl.superCinema.backend.api.dto.TypeDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.domain.model.Movie;
import pl.superCinema.backend.domain.model.Type;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class MovieBuilder {

    public MovieDto entityToDto(Movie movie) {

        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDuration(movie.getDuration());
        movieDto.setProductionCountry(movie.getProductionCountry());
        movieDto.setProductionYear(movie.getProductionYear());

        //set types
        List<TypeDto> typeDtos = movie.getTypes()
                .stream()
                .map(type -> TypeDto.valueOf(type.name()))
                .collect(Collectors.toList());
        movieDto.setTypes(typeDtos);

        //TODO change when implemantation of theses classes will be ready
        movieDto.setDirectors(movie.getDirectors());
        movieDto.setCast(movie.getCast());
        movieDto.setMovieShow(movie.getMovieShow());

        return movieDto;
    }

    public Movie dtoToEntity(MovieDto movieDto){

        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDuration(movieDto.getDuration());
        movie.setProductionCountry(movieDto.getProductionCountry());
        movie.setProductionYear(movieDto.getProductionYear());
        //set types
        List<Type> typeList = movieDto.getTypes()
                .stream()
                .map(type -> Type.valueOf(type.name()))
                .collect(Collectors.toList());
        movie.setTypes(typeList);
        movie.setDirectors(movieDto.getDirectors());
        movie.setCast(movieDto.getCast());
        movie.setMovieShow(movieDto.getMovieShow());

        return movie;
    }



}
