package pl.superCinema.backend.api.controllers.seatController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.dto.SeatDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.model.Seat;
import pl.superCinema.backend.domain.repository.SeatRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SeatFacade {
    private SeatRepository seatRepository;
    private SeatBuilder seatBuilder;

    public SeatDto saveSeat(SeatDto seatDto) {
        Seat seat = seatBuilder.entityFromDto(seatDto);
        Seat savedSeat= seatRepository.save(seat);
        return seatBuilder.dtoFromEntity(savedSeat);
    }

    public SeatDto getSeatById(Long id){
        Seat seat = seatRepository.findById(id).orElseThrow(
                () -> new EntityCouldNotBeFoundException("Seat by id: " + id + " couldn't be found")
        );
        return seatBuilder.dtoFromEntity(seat);
    }

    public List<SeatDto> getAllSeats(){

        return  seatRepository.findAll()
                .stream()
                .map(seat -> seatBuilder.dtoFromEntity(seat))
                .collect(Collectors.toList());
    }

    public SeatDto getSeatDtoByNumber(Long number){
        Seat seat = seatRepository.findBySeatNumber(number).orElseThrow(
                () -> new EntityCouldNotBeFoundException("Seat by number: " + number + "couldn't be found")
        );
        return seatBuilder.dtoFromEntity(seat);
    }

    public SeatDto editSeatById(Long id, SeatDto seatDto){

        Seat seat = seatRepository.findById(id).orElseThrow(
                () -> new EntityCouldNotBeFoundException("Seat by id: " + id + " couldn't be found")
        );
        Seat seatSaved = seatRepository.save(seat);
        return seatBuilder.dtoFromEntity(seatSaved);
    }

    public void deleteSeatById(Long id){
        seatRepository.deleteById(id);
    }
}
