package pl.superCinema.backend.api.controllers.seatController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.superCinema.backend.api.dto.SeatDto;
import pl.superCinema.backend.domain.errors.ApiError;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatFacade seatFacade;

    @PostMapping
    public ResponseEntity saveSeat(@RequestBody SeatDto seatDto){
        SeatDto seatDtoSaved;
        try {
            seatDtoSaved = seatFacade.saveSeat(seatDto);
        }catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(seatDtoSaved, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping(params = "id")
    public ResponseEntity getSeatById(@RequestParam Long id){
        SeatDto seatDto;
        try {
           seatDto = seatFacade.getSeatById(id);
        }catch (Exception e){
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(seatDto, HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping(params = "idToDelete")
    public ResponseEntity deleteSeatById(@RequestParam Long idToDelete){
        try {
            seatFacade.deleteSeatById(idToDelete);
        }catch (Exception e){
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping(params = "idToUpdate")
    public ResponseEntity updateSeat(@RequestParam Long idToUpdate, @RequestBody SeatDto seatDto){
        SeatDto seatDtoUpdated;
        try {
            seatDtoUpdated = seatFacade.editSeatById(idToUpdate, seatDto);
        }catch (Exception e){
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.getClass().getSimpleName());
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(seatDtoUpdated, HttpStatus.OK);
    }
}
