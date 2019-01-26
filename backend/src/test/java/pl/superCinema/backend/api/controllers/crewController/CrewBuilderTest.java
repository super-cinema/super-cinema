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


    @Autowired
    CrewBuilder crewBuilder;

    Crew createCrew() {
        Crew crew = new Crew();
        crew.setId(1L);
        crew.setName("Andrzej");
        crew.setSurname("Bonk");
        crew.setCrewRoles(Arrays.asList(CrewRole.ACTOR, CrewRole.DIRECTOR));
        return crew;
    }

    CrewDto createCrewDto(){
        CrewDto crewDto = new CrewDto();
        crewDto.setId(1L);
        crewDto.setName("Andrzej");
        crewDto.setSurname("Bonk");
        crewDto.setCrewRoles(Arrays.asList(CrewRoleDto.ACTOR, CrewRoleDto.DIRECTOR));
        return crewDto;
    }


    @Test
    public void entityToDto() {
        //given
        CrewDto expectedDto = new CrewDto();
        expectedDto.setId(1L);
        expectedDto.setName("Andrzej");
        expectedDto.setSurname("Bonk");
        expectedDto.setCrewRoles(Arrays.asList(CrewRoleDto.ACTOR, CrewRoleDto.DIRECTOR));
        //when
        CrewDto crewDtoToCheck = crewBuilder.entityToDto(createCrew());
        //then
        assertEquals(expectedDto, crewDtoToCheck);
    }

    @Test
    public void dtoToEntity() {
        // given
        Crew expectedCrewDto = new Crew();
        expectedCrewDto.setId(1L);
        expectedCrewDto.setName("Andrzej");
        expectedCrewDto.setSurname("Bonk");
        expectedCrewDto.setCrewRoles(Arrays.asList(CrewRole.ACTOR, CrewRole.DIRECTOR));
        //when
        Crew crewDtoToCheck = crewBuilder.dtoToEntity(createCrewDto());
        //then
        assertEquals(expectedCrewDto,crewDtoToCheck);
    }
}