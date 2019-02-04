package pl.superCinema.backend.api.controllers.crewController;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.CrewRoleDto;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.CrewRole;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class CrewBuilderCrewTest extends AbstractCrewTest {

    @Autowired
    CrewBuilder crewBuilder;

    @Test
    public void shouldReturnCrewDtoFromEntity() {
        //given
        CrewDto expectedDto = createCrewDto();
        expectedDto.setCrewRoles(Arrays.asList(CrewRoleDto.ACTOR, CrewRoleDto.DIRECTOR));
        //when
        CrewDto crewDtoToCheck = crewBuilder.entityToDto(createCrew());
        //then
        assertEquals(expectedDto, crewDtoToCheck);
    }

    @Test
    public void shouldReturnEntityFromCrewDto() {
        // given
        Crew expectedCrew = createCrew();
        expectedCrew.setCrewRoles(Arrays.asList(CrewRole.ACTOR, CrewRole.DIRECTOR));
        //when
        Crew crewDtoToCheck = crewBuilder.dtoToEntity(prepareCrewDto());
        //then
        assertEquals(expectedCrew, crewDtoToCheck);
    }

    private Crew createCrew() {
        return super.prepareCrew();
    }

    private CrewDto createCrewDto() {
        return super.prepareCrewDto();
    }
}