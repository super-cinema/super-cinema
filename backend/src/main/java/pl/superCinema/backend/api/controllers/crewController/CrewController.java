package pl.superCinema.backend.api.controllers.crewController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superCinema.backend.api.dto.CrewDto;

import java.util.List;

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

    @GetMapping
    public List<CrewDto> getAllCrew() {
        return crewFacade.getAllCrew();
    }

    @PostMapping
    public CrewDto addCrew(@RequestBody CrewDto crewDto) {
     return crewFacade.addCrew(crewDto);
    }

    @DeleteMapping(params = "id")
    public CrewDto deleteCrew(@RequestParam Long id) {
       return crewFacade.deleteCrew(id);
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteAllCrew(){
        crewFacade.deleteAllCrew();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping(params = "id")
    public CrewDto editMovie(@RequestParam Long id, @RequestBody CrewDto crewDto) {
        return crewFacade.updateCrew(id, crewDto);
    }

}
