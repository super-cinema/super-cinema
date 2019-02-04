package pl.superCinema.backend.domain.facades;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.infrastructure.builders.SeatBuilder;
import pl.superCinema.backend.infrastructure.dto.SeatDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.models.Seat;
import pl.superCinema.backend.domain.ports.SeatRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SeatFacade {
    private SeatRepository seatRepository;
    private SeatBuilder seatBuilder;

    public SeatDto saveSeat(SeatDto seatDto) {
        Seat seat = seatBuilder.dtoToEntity(seatDto);
        Seat savedSeat = seatRepository.save(seat);
        return seatBuilder.entityToDto(savedSeat);
    }

    public SeatDto getSeatById(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(
                () -> new EntityCouldNotBeFoundException("Seat by id: " + id + " couldn't be found")
        );
        return seatBuilder.entityToDto(seat);
    }

    public List<SeatDto> getAllSeats() {

        return seatRepository.findAll()
                .stream()
                .map(seat -> seatBuilder.entityToDto(seat))
                .collect(Collectors.toList());
    }

    public SeatDto getSeatDtoByNumber(Long number) {
        Seat seat = seatRepository.findBySeatNumber(number).orElseThrow(
                () -> new EntityCouldNotBeFoundException("Seat by number: " + number + "couldn't be found")
        );
        return seatBuilder.entityToDto(seat);
    }

    public SeatDto editSeatById(Long id, SeatDto seatDto) {

        Seat seat = seatRepository.findById(id).orElseThrow(
                () -> new EntityCouldNotBeFoundException("Seat by id: " + id + " couldn't be found")
        );
        Seat seatSaved = seatRepository.save(seat);
        return seatBuilder.entityToDto(seatSaved);
    }

    public void deleteSeatById(Long id) {
        seatRepository.deleteById(id);
    }
}
