package pl.superCinema.backend.api.cotroller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.superCinema.backend.api.dto.MovieDto;

@RestController
@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity addMovie(@RequestBody MovieDto movieDto){
        MovieDto result = movieService.addMovie(movieDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
