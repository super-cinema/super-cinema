package pl.superCinema.backend.crew;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.infrastructure.builders.CrewBuilder;
import pl.superCinema.backend.domain.facades.CrewFacade;
import pl.superCinema.backend.domain.ports.CrewRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CrewFacadeTest extends AbstractTest {

    @Autowired
    CrewBuilder crewBuilder;
    @Autowired
    CrewRepository crewRepository;
    @Autowired
    CrewFacade crewFacade;


    @Test
    public void shouldAddCrewAndReturnSameCrewDtoWhichWasProvided() {
        //given
        CrewDto expectedCrewDto = createCrewDto();
        //when
        CrewDto crewDtoAfterSave = crewFacade.addCrew(expectedCrewDto);
        //then
        assertEquals(expectedCrewDto, crewDtoAfterSave);
    }

    @Test
    public void shouldUpdateSelectedCrew() {
        //given
        CrewDto crewDtoExpected = createCrewDto();
        crewDtoExpected.setName("Adam");
        CrewDto crewToSave = createCrewDto();
        crewFacade.addCrew(crewToSave);
        CrewDto crewToUpdate = crewFacade.getCrew(crewToSave.getId());
        crewToUpdate.setName("Adam");
        //when
        CrewDto crewDtoAfterUpdate = crewFacade.updateCrew(crewToSave.getId(), crewToUpdate);
        //then
        assertEquals(crewDtoExpected, crewDtoAfterUpdate);
    }

    @Test
    public void shouldGetAllCrew() {
        //given
        CrewDto crewDto1 = createCrewDto();
        CrewDto crewDto2 = createSecondCrewDto();
        List<CrewDto> expectedCrewDto = Arrays.asList(crewDto1, crewDto2);
        crewFacade.addCrew(crewDto1);
        crewFacade.addCrew(crewDto2);
        //when
        List<CrewDto> allCrew = crewFacade.getAllCrew();
        //then
        assertEquals(expectedCrewDto, allCrew);
    }

    @Test
    public void shouldGetSelectedCrew() {
        //given
        CrewDto expectedCrewDto = createCrewDto();
        crewFacade.addCrew(expectedCrewDto);
        //when
        CrewDto crewFromDataBase = crewFacade.getCrew(expectedCrewDto.getId());
        //then
        assertEquals(expectedCrewDto, crewFromDataBase);
    }

    @Test
    public void shouldDeleteSelectedCrew() {
        //given
        CrewDto crewDto1 = createCrewDto();
        crewFacade.addCrew(crewDto1);
        //when
        crewFacade.deleteCrew(crewDto1.getId());
        boolean isCrewPresentAfterDelete = crewRepository.findById(crewDto1.getId()).isPresent();
        //then
        assertFalse(isCrewPresentAfterDelete);
    }

    @Test
    public void shouldDeleteAllCrew() {
        //given
        CrewDto crewDto1 = createCrewDto();
        CrewDto crewDto2 = createSecondCrewDto();
        crewFacade.addCrew(crewDto1);
        crewFacade.addCrew(crewDto2);
        //when
        crewFacade.deleteAllCrew();
        boolean isNotCrewPresentAfterDelete = crewFacade.getAllCrew().isEmpty();
        //then
        assertTrue(isNotCrewPresentAfterDelete);
    }


    private CrewDto createCrewDto() {
        return super.prepareCrewDto();
    }

    CrewDto createSecondCrewDto() {
        return super.prepareSecondCrewDto();
    }


}