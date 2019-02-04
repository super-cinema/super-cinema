package pl.superCinema.backend.domain.facades;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.infrastructure.builders.MovieBuilder;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.infrastructure.dto.MovieDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.models.Crew;
import pl.superCinema.backend.domain.models.CrewRole;
import pl.superCinema.backend.domain.models.Movie;
import pl.superCinema.backend.domain.models.Type;
import pl.superCinema.backend.domain.ports.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MovieFacade {

    private MovieRepository movieRepository;
    private MovieBuilder movieBuilder;
    private CrewFacade crewFacade;


    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = movieBuilder.dtoToBasicEntity(movieDto);
        Movie movieSaved = movieRepository.save(movie);
        List<CrewDto> directorsDto = movieDto.getDirectors();
        if (directorsDto != null) {
            crewFacade.setCrewListInMovie(directorsDto, movieSaved, CrewRole.DIRECTOR);
            movieRepository.save(movieSaved);
        }
        List<CrewDto> castDto = movieDto.getCast();
        if (castDto != null) {
            crewFacade.setCrewListInMovie(castDto, movieSaved, CrewRole.ACTOR);
            movieRepository.save(movieSaved);
        }
        return movieBuilder.entityToDto(movieSaved);
    }

    //TODO not used?
    public MovieDto getMovieByTitle(String title) {
        Movie movie = findMovieEntity(title);
        return movieBuilder.entityToDto(movie);
    }

    public MovieDto getMovieDtoById(Long id) {
        Movie movie = getMovieEntityById(id);
        return movieBuilder.entityToDto(movie);
    }

    private Movie getMovieEntityById(Long id) {
        return movieRepository.findById(id).orElseThrow(
                () -> new EntityCouldNotBeFoundException("Movie with id: " + id + " not found"));
    }

    public MovieDto saveEditedMovie(Long id, MovieDto movieDto) {
        Movie movie = getMovieEntityById(id);
        editMovie(movie, movieDto);
        movieRepository.save(movie);
        MovieDto movieDto1 = movieBuilder.entityToDto(movie);
        return movieDto1;
    }

    private void editMovie(Movie movie, MovieDto movieDto) {
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
        //update actors
        updateCrewListInMovie(movie, movieDto, CrewRole.ACTOR);
        //update directors
        updateCrewListInMovie(movie, movieDto, CrewRole.DIRECTOR);
    }

    private void updateCrewListInMovie(Movie existingMovie, MovieDto movieToSet, CrewRole crewRole) {
        List<Crew> existingCrew = crewRole.equals(CrewRole.ACTOR) ? existingMovie.getCast()
                : existingMovie.getDirectors();
        List<CrewDto> crewToSetInMovie = crewRole.equals(CrewRole.ACTOR) ? movieToSet.getCast()
                : movieToSet.getDirectors();

        List<Long> existingCrewIds = new ArrayList<>();
        if (existingCrew != null) {
            existingCrewIds = existingCrew.stream()
                    .map(Crew::getId)
                    .collect(Collectors.toList());
        }
        List<Long> crewIdsToSetInMovie = new ArrayList<>();
        if (crewToSetInMovie != null) {
            crewIdsToSetInMovie = crewToSetInMovie.stream()
                    .map(CrewDto::getId)
                    .collect(Collectors.toList());
        }
        makeCrewListToAddToExistingCrewList(existingMovie, crewRole, existingCrewIds, crewIdsToSetInMovie);
        makeCrewListToDeleteFromExistingCrewList(existingMovie, crewRole, existingCrewIds, crewIdsToSetInMovie);
    }

    public MovieDto deleteMovieByTitle(String title) {
        Movie movie = findMovieEntity(title);
        MovieDto movieDto = movieBuilder.entityToDto(movie);
        movieRepository.delete(movie);
        return movieDto;

    }

    private Movie findMovieEntity(String title) {
        return movieRepository.findByTitle(title).orElseThrow(
                () -> new EntityCouldNotBeFoundException("Movie " + title + " not found"));
    }

    public List<MovieDto> getAllMovies() {
        List<Movie> allMovies = movieRepository.findAll();
        List<MovieDto> allMoviesDto = new ArrayList<>();
        if (!allMovies.isEmpty()) {
            for (Movie movie : allMovies) {
                allMoviesDto.add(movieBuilder.entityToDto(movie));
            }
        }
        return allMoviesDto;
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    private void makeCrewListToDeleteFromExistingCrewList(Movie existingMovie, CrewRole crewRole, List<Long> existingCrewIds, List<Long> crewIdsToSetInMovie) {
        List<Long> crewIdsToRemove = new ArrayList<>(existingCrewIds);
        crewIdsToRemove.removeAll(crewIdsToSetInMovie);
        if (crewRole.equals(CrewRole.ACTOR)) {
            crewFacade.deleteCrewFromMovie(crewIdsToRemove, existingMovie, CrewRole.ACTOR);
        } else {
            crewFacade.deleteCrewFromMovie(crewIdsToRemove, existingMovie, CrewRole.DIRECTOR);
        }
    }

    private void makeCrewListToAddToExistingCrewList(Movie existingMovie, CrewRole crewRole, List<Long> existingCrewIds, List<Long> crewIdsToSetInMovie) {
        List<Long> crewIdsToAdd = new ArrayList<>(crewIdsToSetInMovie);
        crewIdsToAdd.removeAll(existingCrewIds);
        if (crewRole.equals(CrewRole.ACTOR)) {
            crewFacade.setCrewInMovieById(crewIdsToAdd, existingMovie, CrewRole.ACTOR);
        } else {
            crewFacade.setCrewInMovieById(crewIdsToAdd, existingMovie, CrewRole.DIRECTOR);
        }
    }

}
