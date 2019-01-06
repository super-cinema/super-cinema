package pl.superCinema.backend.api.controllers.movieController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.model.Movie;
import pl.superCinema.backend.domain.model.Type;
import pl.superCinema.backend.domain.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MovieFacade {

    private MovieRepository movieRepository;
    private MovieBuilder movieBuilder;

    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = movieBuilder.dtoToEntity(movieDto);
        movieRepository.save(movie);
        return movieBuilder.entityToDto(movie);
    }

    public MovieDto getMovieByTitle(String title) throws EntityCouldNotBeFoundException {
        Movie movie = findMovieEntity(title);
        return  movieBuilder.entityToDto(movie);
    }

    public MovieDto getMovieById(Long id) throws EntityCouldNotBeFoundException {
        Movie movie = getMovieEntityById(id);
        MovieDto movieDto = movieBuilder.entityToDto(movie);
        return movieDto;
    }

    private Movie getMovieEntityById(Long id) {
        return movieRepository.findById(id).orElseThrow(
                    () -> new EntityCouldNotBeFoundException("Movie with id: " + id + " not found"));
    }

    public MovieDto saveEditedMovie(Long id, MovieDto movieDto) throws EntityCouldNotBeFoundException {
        Movie movie = getMovieEntityById(id);
        movie = editMovie(movie, movieDto);
        movieRepository.save(movie);
        return movieBuilder.entityToDto(movie);
    }

    private Movie editMovie(Movie movie, MovieDto movieDto) {
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

    public MovieDto deleteMovieByTitle(String title) {
        Movie movie = findMovieEntity(title);
        MovieDto movieDto = movieBuilder.entityToDto(movie);
        movieRepository.delete(movie);
        return movieDto;

    }

    private Movie findMovieEntity(String title) throws EntityCouldNotBeFoundException {
        return movieRepository.findByTitle(title).orElseThrow(
                    () -> new EntityCouldNotBeFoundException("Movie " + title + " not found"));
    }

    public List<MovieDto> getAllMovies() {
        List<Movie> allMovies = movieRepository.findAll();
        List<MovieDto> allMoviesDto = new ArrayList<>();
        if(!allMovies.isEmpty()){
            for(Movie movie : allMovies) {
                allMoviesDto.add(movieBuilder.entityToDto(movie));
            }
        }
        return allMoviesDto;
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);

    }
}
