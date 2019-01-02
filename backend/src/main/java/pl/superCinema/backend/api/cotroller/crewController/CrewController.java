package pl.superCinema.backend.api.cotroller.crewController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superCinema.backend.api.dto.CrewDto;

@RestController
@AllArgsConstructor
@RequestMapping("/crew")
@CrossOrigin(origins = "*")
public class CrewController {

   private CrewFacade crewFacade;

    @GetMapping(params = "id")
    public ResponseEntity getMovies(@RequestParam Long id) {
        CrewDto result = crewFacade.getCrew(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCrew(@RequestBody CrewDto crewDto){
        CrewDto result = crewFacade.addCrew(crewDto);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity deleteCrew(@RequestParam Long id){
        CrewDto result = crewFacade.deleteCrew(id);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    //todo putMapping for Crew
//   @PutMapping("")

}
