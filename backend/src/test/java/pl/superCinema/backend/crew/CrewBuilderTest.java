package pl.superCinema.backend.crew;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.infrastructure.dto.CrewRoleDto;
import pl.superCinema.backend.infrastructure.builders.CrewBuilder;
import pl.superCinema.backend.domain.models.Crew;
import pl.superCinema.backend.domain.models.CrewRole;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class CrewBuilderTest extends AbstractTest {

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