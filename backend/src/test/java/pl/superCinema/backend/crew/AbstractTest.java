package pl.superCinema.backend.crew;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.infrastructure.dto.CrewRoleDto;
import pl.superCinema.backend.domain.models.Crew;
import pl.superCinema.backend.domain.models.CrewRole;

import java.util.Arrays;
import java.util.List;

@ActiveProfiles(profiles = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
public abstract class AbstractTest {

    private static final Long id = 1L;
    private final String name = "Andrzej";
    private final String surname = "Bonk";
    private final List<CrewRoleDto> expectedCrewRoles = Arrays.asList(CrewRoleDto.ACTOR, CrewRoleDto.DIRECTOR);


    Crew prepareCrew() {
        Crew crew = new Crew();
        crew.setId(id);
        crew.setName(name);
        crew.setSurname(surname);
        crew.setCrewRoles(Arrays.asList(CrewRole.ACTOR, CrewRole.DIRECTOR));
        return crew;
    }

    CrewDto prepareCrewDto() {
        CrewDto crewDto = new CrewDto();
        crewDto.setId(id);
        crewDto.setName(name);
        crewDto.setSurname(surname);
        crewDto.setCrewRoles(expectedCrewRoles);
        return crewDto;
    }

    CrewDto prepareSecondCrewDto() {
        CrewDto crewDto = new CrewDto();
        crewDto.setId(2L);
        crewDto.setName(name);
        crewDto.setSurname(surname);
        crewDto.setCrewRoles(expectedCrewRoles);
        return crewDto;
    }


}
