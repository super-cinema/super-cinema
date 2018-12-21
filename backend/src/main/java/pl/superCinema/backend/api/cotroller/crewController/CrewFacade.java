package pl.superCinema.backend.api.cotroller.crewController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.domain.repository.CrewRepository;
import pl.superCinema.backend.domain.model.Crew;

import java.util.Optional;

@AllArgsConstructor
public class CrewFacade {

    private CrewRepository crewRepository;
    private CrewBuilder crewBuilder;

    public CrewDto addCrew(CrewDto crewDto) {
        Crew crew = crewBuilder.crewDtoToCrew(crewDto);
        Crew savedCrew = crewRepository.save(crew);
        return crewBuilder.crewToCrewDto(savedCrew);
    }

    public CrewDto getCrew(Long id) {
        Optional<Crew> crew = crewRepository.findById(id);
        return crewBuilder.crewToCrewDto(crew.get());
    }

    public CrewDto deleteCrew(Long id) {
        if (crewRepository.existsById(id)) {
            Optional<Crew> crew = crewRepository.findById(id);
            crewRepository.deleteById(id);
            return crewBuilder.crewToCrewDto(crew.get());
        } else return null;
    }

    //todo
//    public CrewDto updateCrew(Long id){
//        if (crewRepository.existsById(id)){
//            Optional<Crew> crew = crewRepository.findById(id);
//
//    }
//}

}
