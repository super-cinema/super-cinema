package pl.superCinema.backend.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superCinema.backend.api.errors.ApiError;
import pl.superCinema.backend.infrastructure.dto.MovieDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.facades.MovieFacade;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
@CrossOrigin(origins = "*")
public class MovieController {

    private MovieFacade movieFacade;

    @PostMapping
    public ResponseEntity saveMovie(@RequestBody MovieDto movieDto) {
        MovieDto movieDtoAdded;
        try {
            movieDtoAdded = movieFacade.saveMovie(movieDto);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(movieDtoAdded, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllMovies() {
        List<MovieDto> allMoviesDto;
        try {
            allMoviesDto = movieFacade.getAllMovies();
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(allMoviesDto, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping(params = "title")
    public MovieDto getMovieByTitle(@RequestParam String title) {
        return movieFacade.getMovieByTitle(title);
    }

    @GetMapping
    @RequestMapping(params = "movieId")
    public ResponseEntity findMovieById(@RequestParam Long movieId) {
        MovieDto movieById;
        try {
            movieById = movieFacade.getMovieDtoById(movieId);
        } catch (EntityCouldNotBeFoundException e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(movieById, HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping(params = "id")
    public ResponseEntity editMovie(@RequestParam Long id, @RequestBody MovieDto movieDto) {
        MovieDto updatedMovie;
        try {
            updatedMovie = movieFacade.saveEditedMovie(id, movieDto);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(params = "titleToDelete")
    public ResponseEntity deleteMovie(@RequestParam String titleToDelete) {
        try {
            movieFacade.deleteMovieByTitle(titleToDelete);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(params = "idToDelete")
    public ResponseEntity deleteMovie(@RequestParam Long idToDelete) {
        try {
            movieFacade.deleteMovie(idToDelete);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }


    }

}
