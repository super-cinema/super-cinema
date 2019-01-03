package pl.superCinema.backend.api.controllers.cinemaHallController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.superCinema.backend.api.dto.CinemaHallDto;
import pl.superCinema.backend.domain.errors.ApiError;
import pl.superCinema.backend.domain.model.CinemaHall;

@RestController
@AllArgsConstructor
@RequestMapping("/cinemaHall")
public class CinemaHallController {

    @Autowired
    CinemaHallFacade cinemaHallFacade;

    @PostMapping
    public ResponseEntity saveCinemaHall(@RequestBody CinemaHallDto cinemaHallDto){
        CinemaHallDto savedCinemaHallDto;
        try {
            savedCinemaHallDto = cinemaHallFacade.saveCinemaHall(cinemaHallDto);
        } catch (Exception e){
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(savedCinemaHallDto, HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping(params = "id")
    public ResponseEntity getCinemaHall(@RequestParam Long id){
        CinemaHallDto cinemaHallDto;
        try {
            cinemaHallDto = cinemaHallFacade.getCinemaHallById(id);
        } catch (Exception e){
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cinemaHallDto, HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping(params = "idToEdit")
    public ResponseEntity editCinemaHall(@RequestParam Long idToEdit, @RequestBody CinemaHallDto cinemaHallDto){
        CinemaHallDto cinemaHallDtoEdited;
        try {
            cinemaHallDtoEdited = cinemaHallFacade.editCinemaHallById(idToEdit, cinemaHallDto);
        } catch (Exception e){
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cinemaHallDto, HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(params = "idToDelete")
    public ResponseEntity deleteCinemaHall(@RequestParam Long idToDelete){
        CinemaHallDto cinemaHallDtoEdited;
        try{
            cinemaHallDtoEdited = cinemaHallFacade.deleteCInemaHallById(idToDelete);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(cinemaHallDtoEdited, HttpStatus.OK);
    }
}
