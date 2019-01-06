package pl.superCinema.backend.api.cotrollers.crewController;

import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.CrewRoleDto;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.CrewRole;

import java.util.List;
import java.util.stream.Collectors;

public class CrewBuilder {

    public CrewDto crewToCrewDto(Crew crew) {
        CrewDto crewDto = new CrewDto();
        crewDto.setId(crew.getId());
        crewDto.setName(crew.getName());
        crewDto.setSurname(crew.getSurname());
        List<CrewRoleDto> crewRoleDtos = crew.getCrewRoles()
                .stream()
                .map(x -> CrewRoleDto.valueOf(x.name()))
                .collect(Collectors.toList());
        crewDto.setCrewRoleDtos(crewRoleDtos);
        return crewDto;
    }

    public Crew crewDtoToCrew(CrewDto crewDto){
        Crew crew = new Crew();
        crew.setId(crewDto.getId());
        crew.setName(crewDto.getName());
        crew.setSurname(crewDto.getSurname());
        List<CrewRole> crewRoles = crewDto.getCrewRoleDtos()
                .stream()
                .map(x -> CrewRole.valueOf(x.name()))
                .collect(Collectors.toList());
        crew.setCrewRoles(crewRoles);
        return crew;
    }
}
