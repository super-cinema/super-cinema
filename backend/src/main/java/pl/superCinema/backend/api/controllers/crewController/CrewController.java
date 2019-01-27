package pl.superCinema.backend.api.controllers.crewController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.domain.errors.ApiError;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/crew")
@CrossOrigin(origins = "*")

public class CrewController {

    private CrewFacade crewFacade;

    @GetMapping(params = "id")
    public ResponseEntity getCrew(@RequestParam Long id) {
        CrewDto result;
        try {
            result = crewFacade.getCrew(id);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCrew() {
        List<CrewDto> crewDtoList;
        try {
            crewDtoList = crewFacade.getAllCrew();
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(crewDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCrew(@RequestBody CrewDto crewDto) {
        CrewDto crewDtoSaved;
        try {
            crewDtoSaved = crewFacade.addCrew(crewDto);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(crewDtoSaved, HttpStatus.CREATED);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity deleteCrew(@RequestParam Long id) {
        try {
            crewFacade.deleteCrew(id);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAllCrew() {
        try {
            crewFacade.deleteAllCrew();
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping(params = "id")
    public ResponseEntity editCrew(@RequestParam Long id, @RequestBody CrewDto crewDto) {
        try {
            crewFacade.updateCrew(id, crewDto);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(crewDto, HttpStatus.OK);
    }

}
