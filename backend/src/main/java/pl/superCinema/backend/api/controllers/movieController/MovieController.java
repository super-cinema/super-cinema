package pl.superCinema.backend.api.controllers.movieController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.superCinema.backend.api.dto.MovieDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movie")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    private MovieFacade movieFacade;

    @PostMapping
    public MovieDto saveMovie(@RequestBody MovieDto movieDto) {
        return movieFacade.saveMovie(movieDto);
    }

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieFacade.getAllMovies();
    }

    @GetMapping
    @RequestMapping(params = "title")
    public MovieDto getMovie(@RequestParam String title) {
        return movieFacade.getMovieByTitle(title);
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

}
