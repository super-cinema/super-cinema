package pl.superCinema.backend.api.controllers.movieController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.controllers.crewController.CrewBuilder;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.Movie;
import pl.superCinema.backend.domain.model.Type;
import pl.superCinema.backend.domain.repository.CrewRepository;
import pl.superCinema.backend.domain.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MovieFacade {

    private MovieRepository movieRepository;
    private MovieBuilder movieBuilder;
    private CrewBuilder crewBuilder;
    private CrewRepository crewRepository;

    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = movieBuilder.entityFromDto(movieDto);
        try {
            movieRepository.save(movie);
        } catch (Exception e) {
            System.out.println(e);
        }
        List<Crew> directors = movie.getDirectors();
        if(directors != null){
            directors.stream()
                    .forEach(director -> {
                        crewRepository.findById(director.getId()).ifPresent(
                                directorFounded -> {
                                    List<Movie> directedMovies = directorFounded.getDirectedMovies();
                                    if(!directedMovies.contains(movie)){
                                        directedMovies.add(movie);
                                        directorFounded.setDirectedMovies(directedMovies);
                                        crewRepository.save(directorFounded);
                                    }
                                }
                        );
                    });
        }
        return movieBuilder.dtoFromEntity(movie);
    }

    public MovieDto getMovieByTitle(String title) throws EntityCouldNotBeFoundException {
        Movie movie = findMovieEntity(title);
        return  movieBuilder.dtoFromEntity(movie);
    }

    public MovieDto getMovieById(Long id) throws EntityCouldNotBeFoundException {
        Movie movie = getMovieEntityById(id);
        MovieDto movieDto = movieBuilder.dtoFromEntity(movie);
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
        return movieBuilder.dtoFromEntity(movie);
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

        //set actors
        if(movieDto.getCast() != null) {
            List<Crew> crewList = movieDto.getCast()
                    .stream()
                    .map(actor -> crewBuilder.crewDtoToCrew(actor))
                    .collect(Collectors.toList());
            movie.setCast(crewList);
        }
        //set directors
        if(movieDto.getDirectors() != null) {
            List<Crew> directors = movieDto.getDirectors()
                    .stream()
                    .map(director -> crewBuilder.crewDtoToCrew(director))
                    .collect(Collectors.toList());
            movie.setDirectors(directors);
        }
        movie.setMovieShow(movieDto.getMovieShow());

        return movie;
    }

    public MovieDto deleteMovieByTitle(String title) {
        Movie movie = findMovieEntity(title);
        MovieDto movieDto = movieBuilder.dtoFromEntity(movie);
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
                allMoviesDto.add(movieBuilder.dtoFromEntity(movie));
            }
        }
        return allMoviesDto;
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);

    }
}
