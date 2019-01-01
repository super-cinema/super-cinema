package pl.superCinema.backend.api.cotroller.crewController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superCinema.backend.api.dto.CrewDto;

@RestController
@AllArgsConstructor
@RequestMapping("/crew")
@CrossOrigin(origins = "http://localhost:4200")
public class CrewController {

   private CrewFacade crewFacade;

    @GetMapping("/crew/{id}")
    public ResponseEntity getMovies(@PathVariable Long id) {
        CrewDto result = crewFacade.getCrew(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/crew")
    public ResponseEntity addCrew(@RequestBody CrewDto crewDto){
        CrewDto result = crewFacade.addCrew(crewDto);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @DeleteMapping("/crew{id}")
    public ResponseEntity deleteCrew(@PathVariable Long id){
        CrewDto result = crewFacade.deleteCrew(id);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    //todo putMapping for Crew
//   @PutMapping("")

}
