package pl.superCinema.backend.api.controllers.movieController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.api.dto.TypeDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.model.Movie;
import pl.superCinema.backend.domain.model.Type;
import pl.superCinema.backend.domain.repository.MovieRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class MovieControllerTest {
    @LocalServerPort
    int localPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @MockBean
    private MovieFacade movieFacade;

    @InjectMocks
    private MovieController movieController;

    private Movie movie;
    private Long movieSavedId;
    @Autowired
    private MockMvc mockMvc;

    private CrewDto actor = new CrewDto();
    private CrewDto director = new CrewDto();
    private ArrayList<CrewDto> actors = new ArrayList<>();
    private ArrayList<CrewDto> directors = new ArrayList<>();
    private MovieDto movieDtoMock;

    @Before
    public void setUp(){
        actor.setName("actor");
        director.setName("director");
        actors.add(actor);
        directors.add(director);
        movieDtoMock = new MovieDto();
        movieDtoMock.setTitle("Aquaman");
        movieDtoMock.setDuration(120);
        movieDtoMock.setProductionCountry("USA");
        movieDtoMock.setProductionYear(2008);
        movieDtoMock.setTypes(Arrays.asList(TypeDto.COMEDY, TypeDto.ACTION));
        movieDtoMock.setCast(actors);
        movieDtoMock.setDirectors(directors);
        //movie entity
        movie = new Movie();
        movie.setTitle("another");
        movie.setDuration(100);
        movie.setProductionCountry("PL");
        movie.setProductionYear(2010);
        movie.setTypes(Arrays.asList(Type.HORROR, Type.HISTORICAL));
        Movie movieSaved = movieRepository.save(movie);
        movieSavedId = movieSaved.getId();

        //mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }


    @Test
    public void shouldReturnStatusOkAndSavedMovieDtoWhenSavingMovie() {
        //given
        MovieDto movieDto = new MovieDto();
        when(movieFacade.saveMovie(any(MovieDto.class)))
                .thenReturn(movieDtoMock);
        //when
        ResponseEntity<MovieDto> movieDtoResponseEntity =
                this.testRestTemplate.postForEntity("http://localhost:" + localPort + "/movie", movieDto, MovieDto.class);
        MovieDto savedMovieDto = movieDtoResponseEntity.getBody();
        HttpStatus statusCode = movieDtoResponseEntity.getStatusCode();
        //then
        Assert.assertEquals(HttpStatus.CREATED, statusCode);
        Assert.assertEquals(movieDtoMock.getTitle(), savedMovieDto.getTitle());
        Assert.assertEquals(movieDtoMock.getDuration(), savedMovieDto.getDuration());
        Assert.assertEquals(movieDtoMock.getProductionYear(), savedMovieDto.getProductionYear());
        Assert.assertEquals(movieDtoMock.getProductionCountry(), savedMovieDto.getProductionCountry());
        Assert.assertEquals(movieDtoMock.getTypes(), savedMovieDto.getTypes());
        Assert.assertEquals(movieDtoMock.getCast(), savedMovieDto.getCast());
        Assert.assertEquals(movieDtoMock.getDirectors(), savedMovieDto.getDirectors());
    }

    @Test
    public void shouldReturnStatusCodeOkWhenGettingMovieById() {
        //given
        when(movieFacade.getMovieDtoById(any(Long.class)))
                .thenReturn(movieDtoMock);
        //when
        ResponseEntity<MovieDto> responseEntity =
                testRestTemplate.getForEntity("http://localhost:" + localPort + "/movie?movieId=10", MovieDto.class);

        //then
        HttpStatus statusCode = responseEntity.getStatusCode();
        Assert.assertEquals(HttpStatus.OK, statusCode);
    }

    @Test
    @Transactional
    public void shouldReturnStatusCodeOkWhenDeletingMovie() throws Exception{
        //given
       doNothing().when(movieFacade).deleteMovie(any(Long.class));
        //when
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("http://localhost:" + localPort + "/movie?idToDelete=100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusCodeBadRequestWhenDeletingMovie() throws Exception {
        //given
        doThrow(new EntityCouldNotBeFoundException("entity couldn't be deleted"))
                .when(movieFacade)
                .deleteMovie(any(Long.class));
        //when
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("http://localhost:" + localPort + "/movie?idToDelete=100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        //then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnStatusCodeOkWhenEditingMovie() throws Exception{
        //given
        when(movieFacade.saveEditedMovie(any(Long.class), any(MovieDto.class)))
                .thenReturn(movieDtoMock);
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("title");
        movieDto.setDuration(120);
        ObjectMapper objectMapper = new ObjectMapper();
        String string = "http://localhost:" + localPort + "/movie?id=" + movieSavedId;
        //when
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/movie?id=" + movieSavedId)
                .content(objectMapper.writeValueAsString(movieDto))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusCodeOkWhenGettingAllMovies() {
        //given
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Wonder woman");
        ArrayList<MovieDto> movieDtoList = new ArrayList<>();
        movieDtoList.add(movieDtoMock);
        movieDtoList.add(movieDto);
        when(movieFacade.getAllMovies())
                .thenReturn(movieDtoList);
        //when

        ResponseEntity<List> responseEntity =
                testRestTemplate.getForEntity("http://localhost:" + localPort + "/movie", List.class);
        //then
        List<MovieDto> allMoviesList = (List<MovieDto>)responseEntity.getBody().stream()
                .map(movieDtoMap -> mapToMovieDto((Map<String, Object>) movieDtoMap))
                .collect(Collectors.toList());
        List<String> titlesList = allMoviesList.stream()
                .map(movieDto1 -> movieDto1.getTitle())
                .collect(Collectors.toList());
        Assert.assertEquals(2, allMoviesList.size());
        Assert.assertEquals(Arrays.asList("Aquaman", "Wonder woman"), titlesList);
    }

    private MovieDto mapToMovieDto(Map<String, Object> map){
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(map.get("title").toString());
        return movieDto;
    }
}
