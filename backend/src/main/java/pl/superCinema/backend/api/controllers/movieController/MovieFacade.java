package pl.superCinema.backend.api.controllers.movieController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.controllers.movieController.MovieBuilderService;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.domain.exceptions.EntityNotFoundException;
import pl.superCinema.backend.domain.model.Movie;
import pl.superCinema.backend.domain.model.Type;
import pl.superCinema.backend.domain.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MovieFacade {

    private MovieRepository movieRepository;
    private MovieBuilderService movieBuilderService;

    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = movieBuilderService.entityFromDto(movieDto);
        movieRepository.save(movie);
        return movieBuilderService.dtoFromEntity(movie);
    }

    public MovieDto getMovieByTitle(String title) throws EntityNotFoundException {
        Movie movie = findMovieEntity(title);
        return  movieBuilderService.dtoFromEntity(movie);
    }

    public MovieDto getMovieById(Long id) throws EntityNotFoundException {
        Movie movie = getMovieEntityById(id);
        MovieDto movieDto = movieBuilderService.dtoFromEntity(movie);
        return movieDto;
    }

    private Movie getMovieEntityById(Long id) {
        return movieRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Movie with id: " + id + " not found"));
    }

    public MovieDto saveEditedMovie(Long id, MovieDto movieDto) throws EntityNotFoundException {
        Movie movie = getMovieEntityById(id);
        movie = editMovie(movie, movieDto);
        movieRepository.save(movie);
        return movieBuilderService.dtoFromEntity(movie);
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
        MovieDto movieDto = movieBuilderService.dtoFromEntity(movie);
        movieRepository.delete(movie);
        return movieDto;

    }

    private Movie findMovieEntity(String title) throws EntityNotFoundException {
        return movieRepository.findByTitle(title).orElseThrow(
                    () -> new EntityNotFoundException("Movie " + title + " not found"));
    }

    public List<MovieDto> getAllMovies() {
        List<Movie> allMovies = movieRepository.findAll();
        List<MovieDto> allMoviesDto = new ArrayList<>();
        if(!allMovies.isEmpty()){
            for(Movie movie : allMovies) {
                allMoviesDto.add(movieBuilderService.dtoFromEntity(movie));
            }
        }
        return allMoviesDto;
    }
}
