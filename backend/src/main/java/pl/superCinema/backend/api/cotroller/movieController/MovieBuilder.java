package pl.superCinema.backend.api.cotroller.movieController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.cotroller.MovieShowController.MovieShowBuilder;
import pl.superCinema.backend.api.cotroller.crewController.CrewBuilder;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.api.dto.TypeDto;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.Movie;
import pl.superCinema.backend.domain.model.Type;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MovieBuilder {

    private CrewBuilder crewBuilder;
    private MovieShowBuilder movieShowBuilder;

    public Movie movieDtoToMovie(MovieDto movieDto){
        Movie movie = new Movie();

        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDuration(movieDto.getDuration());
        movie.setProductionCountry(movieDto.getProductionCountry());
        movie.setProductionYear(movieDto.getProductionYear());
        movie.setMovieShow(movieShowBuilder.movieShowDtoToMovieShow(movieDto.getMovieShowDto()));

        List<Type> types = movieDto.getTypeDtos()
                .stream()
                .map(x -> Type.valueOf(x.name()))
                .collect(Collectors.toList());
        movie.setTypes(types);

        List<Crew> directors = movieDto.getDirectors()
                .stream()
                .map(x -> crewBuilder.crewDtoToCrew(x))
                .collect(Collectors.toList());
        movie.setDirectors(directors);

        List<Crew> casts = movieDto.getCast()
                .stream()
                .map(x -> crewBuilder.crewDtoToCrew(x))
                .collect(Collectors.toList());
        movie.setCast(casts);

        return movie;
    }

    public MovieDto movieToMovieDto(Movie movie){
        MovieDto movieDto = new MovieDto();

        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setDuration(movieDto.getDuration());
        movieDto.setProductionCountry(movie.getProductionCountry());
        movieDto.setProductionYear(movie.getProductionYear());
        movieDto.setMovieShowDto(movieShowBuilder.movieShowToMovieShowDto(movie.getMovieShow()));

        List<TypeDto> typeDtos = movie.getTypes()
                .stream()
                .map(x -> TypeDto.valueOf(x.name()))
                .collect(Collectors.toList());
        movieDto.setTypeDtos(typeDtos);

        List<CrewDto> directorDtos = movie.getDirectors()
                .stream()
                .map(x -> crewBuilder.crewToCrewDto(x))
                .collect(Collectors.toList());
        movieDto.setDirectors(directorDtos);

        List<CrewDto> castDtos = movie.getCast()
                .stream()
                .map(x -> crewBuilder.crewToCrewDto(x))
                .collect(Collectors.toList());
        movieDto.setCast(castDtos);

        return movieDto;
    }

}
