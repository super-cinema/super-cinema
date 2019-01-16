package pl.superCinema.backend.api.controllers.movieController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.controllers.crewController.CrewBuilder;
import pl.superCinema.backend.api.controllers.crewController.CrewFacade;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.CrewRole;
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
    private CrewBuilder crewBuilder;
    private CrewFacade crewFacade;


    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = movieBuilder.basicEntityFromDto(movieDto);
        Movie movieSaved = movieRepository.save(movie);
        List<CrewDto> directorsDto = movieDto.getDirectors();
        crewFacade.setCrewListInMovie(directorsDto, movieSaved, CrewRole.DIRECTOR);
        movieRepository.save(movieSaved);
        List<CrewDto> castDto = movieDto.getCast();
        crewFacade.setCrewListInMovie(castDto, movieSaved, CrewRole.ACTOR);
        movieRepository.save(movieSaved);
        return movieBuilder.dtoFromEntity(movieSaved);
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
        //update actors
        updateCrewListInMovie(movie, movieDto, CrewRole.ACTOR);
        //update directors
        updateCrewListInMovie(movie, movieDto, CrewRole.DIRECTOR);
        return movie;
    }

    private void updateCrewListInMovie(Movie existingMovie, MovieDto movieToSet, CrewRole crewRole) {
        List<Crew> existingCrew;
        List<CrewDto> crewToSetInMovie;
        if(crewRole.equals(CrewRole.ACTOR)){
            existingCrew = existingMovie.getCast();
            crewToSetInMovie = movieToSet.getCast();
        } else {
            existingCrew = existingMovie.getDirectors();
            crewToSetInMovie = movieToSet.getDirectors();
        }
        List<Long> existingCrewIds = existingCrew.stream()
                .map(crew -> crew.getId())
                .collect(Collectors.toList());
        List<Long> crewIdsToSetInMovie = crewToSetInMovie.stream()
                .map(crewDto -> crewDto.getId())
                .collect(Collectors.toList());

        //crew to add
        List<Long> crewIdsToAdd = new ArrayList<>(crewIdsToSetInMovie);
        crewIdsToAdd.removeAll(existingCrewIds);
        if(crewRole.equals(CrewRole.ACTOR)){
            crewFacade.setCrewInMovieById(crewIdsToAdd, existingMovie, CrewRole.ACTOR);
        }else{
            crewFacade.setCrewInMovieById(crewIdsToAdd, existingMovie, CrewRole.DIRECTOR);
        }

        //crew to delete
        List<Long> crewIdsToRemove = new ArrayList<>(existingCrewIds);
        crewIdsToRemove.removeAll(crewIdsToSetInMovie);
        if(crewRole.equals(CrewRole.ACTOR)){
            crewFacade.deleteCrewFromMovie(crewIdsToRemove, existingMovie, CrewRole.ACTOR);
        }else{
            crewFacade.deleteCrewFromMovie(crewIdsToRemove, existingMovie, CrewRole.DIRECTOR);
        }
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
