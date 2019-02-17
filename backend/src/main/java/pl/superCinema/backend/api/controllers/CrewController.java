package pl.superCinema.backend.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.api.errors.ApiError;
import pl.superCinema.backend.domain.facades.CrewFacade;

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

    @DeleteMapping
    @RequestMapping(params = "idToDel")
    public ResponseEntity deleteCrew(@RequestParam Long idToDel) {
        try {
            crewFacade.deleteCrew(idToDel);
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
    @RequestMapping(params = "idToEdit")
    public ResponseEntity editCrew(@RequestParam Long idToEdit, @RequestBody CrewDto crewDto) {
        try {
            crewFacade.updateCrew(idToEdit, crewDto);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(crewDto, HttpStatus.OK);
    }

}
