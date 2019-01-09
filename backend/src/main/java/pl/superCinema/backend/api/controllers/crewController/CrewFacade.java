package pl.superCinema.backend.api.controllers.crewController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.CrewRole;
import pl.superCinema.backend.domain.repository.CrewRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CrewFacade {

    private CrewRepository crewRepository;
    private CrewBuilder crewBuilder;

    CrewDto addCrew(CrewDto crewDto) {
        Crew crew = crewBuilder.dtoToEntity(crewDto);
        Crew savedCrew = crewRepository.save(crew);
        return crewBuilder.entityToDto(savedCrew);
    }

    CrewDto updateCrew(Long id, CrewDto crewDto) {
        Crew crew = getMovieEntityById(id);
        crew = editCrew(crew, crewDto);
        crewRepository.save(crew);
        return crewBuilder.entityToDto(crew);
    }

    List<CrewDto> getAllCrew() {
        List<Crew> allCrew = crewRepository.findAll();
        List<CrewDto> allCrewDtos = new ArrayList<>();
        if (!allCrew.isEmpty()) {
            allCrew.forEach(crew ->
                    allCrewDtos.add(crewBuilder.entityToDto(crew)));
        }
        return allCrewDtos;
    }

    CrewDto getCrew(Long id) {
        Optional<Crew> crew = crewRepository.findById(id);
        return crewBuilder.entityToDto(crew.get());
    }

    CrewDto deleteCrew(Long id) {
        if (crewRepository.existsById(id)) {
            Optional<Crew> crew = crewRepository.findById(id);
            crewRepository.deleteById(id);
            return crewBuilder.entityToDto(crew.get());
        } else return null;
    }

    void deleteAllCrew() {
        crewRepository.deleteAll();
    }


    private Crew getMovieEntityById(Long id) {
        return crewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Movie with id: " + id + " not found"));
    }

    private Crew editCrew(Crew crew, CrewDto crewDto) {
        crew.setName(crewDto.getName());
        crew.setSurname(crewDto.getSurname());
        List<CrewRole> crewRoles = crewDto.getCrewRoles()
                .stream()
                .map(x -> CrewRole.valueOf(x.name()))
                .collect(Collectors.toList());
        crew.setCrewRoles(crewRoles);

        return crew;
    }
}
