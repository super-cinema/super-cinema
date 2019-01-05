package pl.superCinema.backend.api.controllers.movieController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.domain.errors.ApiError;
import pl.superCinema.backend.domain.exceptions.EntityNotFoundException;
import pl.superCinema.backend.domain.model.Movie;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
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
    public List<MovieDto> getAllMovies() {
        return movieFacade.getAllMovies();
    }

    @GetMapping
    @RequestMapping(params = "title")
    public MovieDto getMovieByTitle(@RequestParam String title) {
        return movieFacade.getMovieByTitle(title);
    }

    @GetMapping
    @RequestMapping(params = "movieId")
    public ResponseEntity findMovieById(@RequestParam Long movieId){
        MovieDto movieById;
        try{
            movieById = movieFacade.getMovieById(movieId);
        } catch (EntityNotFoundException e){
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(movieById, HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping(params = "id")
    public MovieDto editMovie(@RequestParam Long id, @RequestBody MovieDto movieDto) {
        return movieFacade.saveEditedMovie(id, movieDto);
    }

    @DeleteMapping
    @RequestMapping(params = "titleToDelete")
    public MovieDto deleteMovie(@RequestParam String titleToDelete) {
        return movieFacade.deleteMovieByTitle(titleToDelete);
    }

    @DeleteMapping
    @RequestMapping(params = "idToDelete")
    public ResponseEntity deleteMovie(@RequestParam Long idToDelete){
        try{
            movieFacade.deleteMovie(idToDelete);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
        }


    }

}
