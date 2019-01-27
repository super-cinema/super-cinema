package pl.superCinema.backend.api.controllers.crewController;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.CrewRoleDto;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.CrewRole;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles(profiles = "test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
public class CrewControllerTest extends AbstractTest {

    private static final String URL = "http://localhost:";
    private final Long expectedId = 1L;
    private final String expectedName = "Andrzej";
    private final String expectedSurname = "Bonk";
    private final List<CrewRoleDto> expectedCrewRoles = Arrays.asList(CrewRoleDto.ACTOR, CrewRoleDto.DIRECTOR);

    @LocalServerPort
    private int localServerPort;

    @MockBean
    private CrewFacade crewFacade;


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private CrewDto createCrewDto1() {
        CrewDto crewDto = new CrewDto();
        crewDto.setId(expectedId);
        crewDto.setName(expectedName);
        crewDto.setSurname(expectedSurname);
        crewDto.setCrewRoles(expectedCrewRoles);
        return crewDto;

    }

    private CrewDto createCrewDto2() {
        CrewDto crewDto = new CrewDto();
        crewDto.setId(2L);
        crewDto.setName(expectedName);
        crewDto.setSurname(expectedSurname);
        crewDto.setCrewRoles(expectedCrewRoles);
        return crewDto;
    }


    @Test
    public void shouldGetChoicesCrew() {
        CrewDto crewToGet = createCrewDto1();
        //given
        when(crewFacade.getCrew(any(Long.class))).thenReturn(crewToGet);

        //when
        ResponseEntity<CrewDto> responseEntity = testRestTemplate.getForEntity(
                URL + localServerPort + "/crew?id=" + expectedId,
                CrewDto.class);
        //then
        CrewDto crewDto = responseEntity.getBody();
        assertEquals(crewDto.getId(), expectedId);
        assertEquals(crewDto.getName(), expectedName);
        assertEquals(crewDto.getSurname(), expectedSurname);
    }

    @Test
    public void getAllCrew() {
        //given
        CrewDto crewToGet1 = createCrewDto1();
        CrewDto crewToGet2 = createCrewDto2();
        List<CrewDto> expectedList = new ArrayList<>();
        expectedList.add(crewToGet1);
        expectedList.add(crewToGet2);

        when(crewFacade.getAllCrew()).thenReturn(expectedList);

        //when
        ResponseEntity<List> responseEntity = testRestTemplate.getForEntity(
                URL + localServerPort + "/crew",
                List.class);
        //then
        List<CrewDto> list = (List<CrewDto>) responseEntity.getBody().stream().map(
                x -> mapToDto((Map<String, Object>) x)
        ).collect(Collectors.toList());
        assertEquals(expectedList, list);
    }

    @Test
    public void updateCrew() throws Exception {
        //given
        CrewDto crewDto = createCrewDto1();
        String expectedResult = "{\"id\":1,\"name\":\"Andrzej\",\"surname\":\"Bonk\",\"crewRoles\":[\"ACTOR\",\"DIRECTOR\"]}";
        String inputJson = super.mapToJson(crewDto);
        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/crew?id=" + expectedId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        //then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals( expectedResult,content );
    }

    @Test
    public void shouldAddCrew() {
        //given
        CrewDto crewDto = createCrewDto1();
        when(crewFacade.addCrew(any(CrewDto.class))).thenReturn(crewDto);
        //when
        ResponseEntity<CrewDto> responseEntity = testRestTemplate
                .postForEntity(URL + localServerPort + "/crew",
                        crewDto, CrewDto.class);
        //then
        CrewDto crewDtoToCheck = responseEntity.getBody();
        assertEquals(crewDtoToCheck.getId(), expectedId);
        assertEquals(responseEntity.getBody().getName(), expectedName);
        assertEquals(responseEntity.getBody().getSurname(), expectedSurname);
        assertEquals(responseEntity.getBody().getCrewRoles(), expectedCrewRoles);
    }

    @Test
    public void deleteCrew() {
    }

    @Test
    public void deleteAllCrew() {
    }

    private CrewDto mapToDto(Map<String, Object> mapToConvert) {
        CrewDto crewDto = new CrewDto();
        crewDto.setId(Long.parseLong(mapToConvert.get("id").toString()));
        crewDto.setName(mapToConvert.get("name").toString());
        crewDto.setSurname(mapToConvert.get("surname").toString());
        List<CrewRoleDto> crewRoles = ((List<String>) mapToConvert.get("crewRoles")).stream().map(
                x -> CrewRoleDto.valueOf(x)
        ).collect(Collectors.toList());
        crewDto.setCrewRoles(crewRoles);
        return crewDto;
    }

}