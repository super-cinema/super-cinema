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
    public ResponseEntity getCrew(@RequestParam Long id) {
        CrewDto result = crewFacade.getCrew(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCrew() {
        List<CrewDto> crewDtoList = crewFacade.getAllCrew();
        return new ResponseEntity(crewDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCrew(@RequestBody CrewDto crewDto) {
        crewFacade.addCrew(crewDto);
        return new ResponseEntity(crewDto, HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public CrewDto deleteCrew(@RequestParam Long id) {
        return crewFacade.deleteCrew(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAllCrew() {
        crewFacade.deleteAllCrew();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping(params = "id")
    public ResponseEntity editCrew(@RequestParam Long id, @RequestBody CrewDto crewDto) {
         crewFacade.updateCrew(id, crewDto);
        return new ResponseEntity(crewDto, HttpStatus.OK);
    }

}
