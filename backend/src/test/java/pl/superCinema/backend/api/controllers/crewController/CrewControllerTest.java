package pl.superCinema.backend.api.controllers.crewController;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.superCinema.backend.api.controllers.AbstractTest;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.CrewRoleDto;
import pl.superCinema.backend.domain.errors.ApiError;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class CrewControllerTest extends AbstractTest {

    private static final String URL = "http://localhost:";
    private static final String CREW_PARAM = "/crew?id=";
    private final Long expectedId = 1L;
    private final String expectedName = "Andrzej";
    private final String expectedSurname = "Bonk";
    private final List<CrewRoleDto> expectedCrewRoles = Arrays.asList(CrewRoleDto.ACTOR, CrewRoleDto.DIRECTOR);

    @LocalServerPort
    private int localServerPort;

    @MockBean
    private CrewFacade crewFacade;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void shouldAddCrewAndReturnCreatedStatusCode() {
        //given
        CrewDto crewDto = createCrewDto1();
        when(crewFacade.addCrew(any(CrewDto.class))).thenReturn(crewDto);
        //when
        ResponseEntity<CrewDto> responseEntity = testRestTemplate
                .postForEntity(URL + localServerPort + "/crew",
                        crewDto, CrewDto.class);
        //then
        HttpStatus expectedStatusCode = responseEntity.getStatusCode();
        CrewDto crewDtoToCheck = responseEntity.getBody();
        Assert.assertEquals(HttpStatus.CREATED, expectedStatusCode);
        assertEquals(crewDtoToCheck.getId(), expectedId);
        assertEquals(expectedName, responseEntity.getBody().getName());
        assertEquals(expectedSurname, responseEntity.getBody().getSurname());
        assertEquals(expectedCrewRoles, responseEntity.getBody().getCrewRoles());
    }

    @Test
    public void shouldNotAddCrewAndReturnBadRequestStatusCode() throws Exception {
        //given
        CrewDto crewDtoToAdd = createCrewDto1();
        doThrow(new EntityCouldNotBeFoundException("entity couldn't be added"))
                .when(crewFacade)
                .addCrew(crewDtoToAdd);
        //when
        mockMvc.perform(post(URL + localServerPort + "/crew")
                .content(String.valueOf(crewDtoToAdd))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetSelectedCrewAndReturnOkStatusCode() {
        //given
        CrewDto crewToGet = createCrewDto1();
        when(crewFacade.getCrew(any(Long.class))).thenReturn(crewToGet);

        //when
        ResponseEntity<CrewDto> responseEntity = testRestTemplate.getForEntity(
                URL + localServerPort + CREW_PARAM + expectedId,
                CrewDto.class);
        //then

        CrewDto crewDto = responseEntity.getBody();
        HttpStatus expectedStatusCode = responseEntity.getStatusCode();
        Assert.assertEquals(HttpStatus.OK, expectedStatusCode);
        assertEquals(expectedId, crewDto.getId());
        assertEquals(expectedName, crewDto.getName());
        assertEquals(expectedSurname, crewDto.getSurname());
    }

    @Test
    public void shouldNotGetSelectedCrewAndReturnBadRequestStatusCode() {
        //given
        doThrow(new EntityCouldNotBeFoundException("entity couldn't be found"))
                .when(crewFacade)
                .getCrew(expectedId);
        //when
        ResponseEntity<CrewDto> responseEntity = testRestTemplate.getForEntity(
                URL + localServerPort + CREW_PARAM + expectedId,
                CrewDto.class);
        //then
        HttpStatus expectedStatusCode = responseEntity.getStatusCode();
        Assert.assertEquals(HttpStatus.BAD_REQUEST, expectedStatusCode);
    }

    @Test
    public void shouldGetAllCrewAndReturnOkStatusCode() {
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
        HttpStatus expectedStatusCode = responseEntity.getStatusCode();
        Assert.assertEquals(HttpStatus.OK, expectedStatusCode);
        assertEquals(expectedList, list);
    }

    @Test
    public void shouldNotGetAllCrewAndReturnBadRequestStatusCode() {
        //given
        doThrow(new EntityCouldNotBeFoundException("entities couldn't be found"))
                .when(crewFacade)
                .getAllCrew();
        //when
        ResponseEntity<ApiError> responseEntity = testRestTemplate.getForEntity(
                URL + localServerPort + "/crew",
                ApiError.class);
        //then
        HttpStatus expectedStatusCode = responseEntity.getStatusCode();
        Assert.assertEquals(HttpStatus.BAD_REQUEST, expectedStatusCode);
    }


    @Test
    public void shouldUpdateSelectedCrewAndReturnOkStatusCode() throws Exception {
        //given
        CrewDto crewDto = createCrewDto1();
        String expectedResult = "{\"id\":1,\"name\":\"Andrzej\",\"surname\":\"Bonk\",\"crewRoles\":[\"ACTOR\",\"DIRECTOR\"]}";
        String inputJson = super.mapToJson(crewDto);
        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(CREW_PARAM + expectedId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        //then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResult, content);
    }

    @Test
    public void shouldReturnedBadRequestStatusCodeWhenUpdatingSelectedCrew() throws Exception {
        //given
        CrewDto crewDto = createCrewDto1();
        String inputJson = super.mapToJson(crewDto);
        doThrow(new EntityCouldNotBeFoundException("entities couldn't be found"))
                .when(crewFacade)
                .updateCrew(expectedId, crewDto);
        //when
        mvc.perform(MockMvcRequestBuilders.put(CREW_PARAM + expectedId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                //then
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void shouldReturnStatusCodeOkWhenDeletingMovie() throws Exception {
        //given
        doNothing().when(crewFacade).deleteCrew(any(Long.class));
        //when
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(URL + localServerPort + CREW_PARAM + expectedId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestStatusCodeWhenDeletingCrewById() throws Exception {
        //given
        doThrow(new EntityCouldNotBeFoundException("entities couldn't be found"))
                .when(crewFacade)
                .deleteCrew(any(Long.class));
        //when
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(URL + localServerPort + CREW_PARAM + expectedId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status()
                        //then
                        .isBadRequest());
    }


    @Test
    @Transactional
    public void shouldDeleteAllCrewAndReturnOkStatusCode() {

        //when
        ResponseEntity responseEntity = testRestTemplate
                .exchange(URL + localServerPort + CREW_PARAM,
                        HttpMethod.DELETE, null, ResponseEntity.class, expectedId);
        //then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void shouldReturnBadRequestStatusCodeWhenDeletingAllCrew() throws Exception {
        //given
        doNothing().when(crewFacade).deleteAllCrew();
        //when
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(URL + localServerPort + "/crew")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))


        //then
                .andExpect(status().isBadRequest());
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
}