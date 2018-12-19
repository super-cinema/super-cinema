package pl.superCinema.backend.api.cotroller;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.domain.model.CinemaRepository;
import pl.superCinema.backend.domain.model.Movie;

@AllArgsConstructor
public class MovieService {

    private CinemaRepository cinemaRepository;

    public MovieDto addMovie(MovieDto movieDto){
//todo narazie tylko tytul
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());

        Movie movieToSave = cinemaRepository.save(movie);
        return movieToMovieDto(movieToSave);
    }

    private MovieDto movieToMovieDto(Movie movie){
        MovieDto movieDto = new MovieDto();
        movie.setTitle(movie.getTitle());
        return movieDto;
    }


}
