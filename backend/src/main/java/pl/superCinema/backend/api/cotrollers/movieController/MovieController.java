package pl.superCinema.backend.api.cotrollers.movieController;

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

    private MovieFacade movieFacade;

    @PostMapping("/movies")
    public ResponseEntity addMovie(@RequestBody MovieDto movieDto){
        MovieDto result = movieFacade.addMovie(movieDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
