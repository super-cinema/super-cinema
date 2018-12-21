package pl.superCinema.backend.api.cotroller.movieController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.domain.repository.MovieRepository;
import pl.superCinema.backend.domain.model.Movie;

@AllArgsConstructor
public class MovieFacade {

    private MovieRepository movieRepository;
    private MovieBuilder movieBuilder;

    public MovieDto addMovie(MovieDto movieDto) {
        Movie movie = movieBuilder.movieDtoToMovie(movieDto);
        Movie movieToSave = movieRepository.save(movie);
        return movieBuilder.movieToMovieDto(movieToSave);
    }




}
