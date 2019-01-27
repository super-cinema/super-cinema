package pl.superCinema.backend.api.controllers.crewController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.CrewRoleDto;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.CrewRole;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@ActiveProfiles(profiles = "test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
public class CrewBuilderTest {

    private final Long id = 1L;
    private final String name = "Andrzej";
    private final String surname = "Bonk";


    @Autowired
    CrewBuilder crewBuilder;

    private Crew createCrew() {
        Crew crew = new Crew();
        crew.setId(id);
        crew.setName(name);
        crew.setSurname(surname);
        crew.setCrewRoles(Arrays.asList(CrewRole.ACTOR, CrewRole.DIRECTOR));
        return crew;
    }

    private CrewDto createCrewDto(){
        CrewDto crewDto = new CrewDto();
        crewDto.setId(id);
        crewDto.setName(name);
        crewDto.setSurname(surname);
        crewDto.setCrewRoles(Arrays.asList(CrewRoleDto.ACTOR, CrewRoleDto.DIRECTOR));
        return crewDto;
    }


    @Test
    public void shouldReturnCrewDtoFromEntity() {
        //given
        CrewDto expectedDto = new CrewDto();
        expectedDto.setId(id);
        expectedDto.setName(name);
        expectedDto.setSurname(surname);
        expectedDto.setCrewRoles(Arrays.asList(CrewRoleDto.ACTOR, CrewRoleDto.DIRECTOR));
        //when
        CrewDto crewDtoToCheck = crewBuilder.entityToDto(createCrew());
        //then
        assertEquals(expectedDto, crewDtoToCheck);
    }

    @Test
    public void shouldReturnEntityFromCrewDto() {
        // given
        Crew expectedCrewDto = new Crew();
        expectedCrewDto.setId(id);
        expectedCrewDto.setName(name);
        expectedCrewDto.setSurname(surname);
        expectedCrewDto.setCrewRoles(Arrays.asList(CrewRole.ACTOR, CrewRole.DIRECTOR));
        //when
        Crew crewDtoToCheck = crewBuilder.dtoToEntity(createCrewDto());
        //then
        assertEquals(expectedCrewDto,crewDtoToCheck);
    }
}