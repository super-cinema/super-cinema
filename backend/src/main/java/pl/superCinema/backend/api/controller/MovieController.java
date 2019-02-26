package pl.superCinema.backend.api.controller;

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
@RequestMapping("/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    private MovieFacade movieFacade;

    @PostMapping
    public ResponseEntity saveMovie(@RequestBody MovieDto movieDto) {
        return new ResponseEntity(movieFacade.saveMovie(movieDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllMovies() {
        return new ResponseEntity(movieFacade.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping(params = "title")
    public MovieDto getMovieByTitle(@RequestParam String title) {
        return movieFacade.getMovieByTitle(title);
    }

    @GetMapping
    @RequestMapping(params = "movieId")
    public ResponseEntity findMovieById(@RequestParam Long movieId) {
        return new ResponseEntity<>(movieFacade.getMovieDtoById(movieId), HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping(params = "id")
    public ResponseEntity editMovie(@RequestParam Long id, @RequestBody MovieDto movieDto) {
        return new ResponseEntity(movieFacade.saveEditedMovie(id, movieDto), HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(params = "titleToDelete")
    public ResponseEntity deleteMovie(@RequestParam String titleToDelete) {
        movieFacade.deleteMovieByTitle(titleToDelete);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(params = "idToDelete")
    public ResponseEntity deleteMovie(@RequestParam Long idToDelete) {
        movieFacade.deleteMovie(idToDelete);
        return new ResponseEntity(HttpStatus.OK);
    }

}
