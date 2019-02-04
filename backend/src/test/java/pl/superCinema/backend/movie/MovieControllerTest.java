package pl.superCinema.backend.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.infrastructure.dto.MovieDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;
import pl.superCinema.backend.domain.facades.MovieFacade;
import pl.superCinema.backend.domain.models.Movie;
import pl.superCinema.backend.domain.ports.MovieRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
public class MovieControllerTest  extends AbstractMovieTest {
    public static final String URL = "http://localhost:";
    @LocalServerPort
    int localPort;
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    private MovieRepository movieRepository;
    @MockBean
    private MovieFacade movieFacade;
    private Movie movie;
    private Long movieSavedId;
    @Autowired
    private MockMvc mockMvc;

    private List<CrewDto> actors;
    private List<CrewDto> directors;
    private MovieDto movieDtoMock;

    @Before
    public void setUp() {
        this.actors = super.prepareCrewDtoList();
        this.directors = super.prepareCrewDtoList();
        this.movieDtoMock = super.prepareMovieDto();
        this.movie = super.prepareMovie();
        Movie movieSaved = movieRepository.save(movie);
        movieSavedId = movieSaved.getId();

    }


    @Test
    public void shouldReturnStatusCreatedWhenSavingMovie() {
        //given
        MovieDto movieDto = new MovieDto();
        when(movieFacade.saveMovie(any(MovieDto.class)))
                .thenReturn(movieDtoMock);
        //when
        ResponseEntity<MovieDto> movieDtoResponseEntity =
                this.testRestTemplate.postForEntity(URL + localPort + "/movie", movieDto, MovieDto.class);
        MovieDto savedMovieDto = movieDtoResponseEntity.getBody();
        HttpStatus statusCode = movieDtoResponseEntity.getStatusCode();
        //then
        movieDtoMock.setId(savedMovieDto.getId());
        Assert.assertEquals(movieDtoMock, savedMovieDto);
        Assert.assertEquals(HttpStatus.CREATED, statusCode);
    }

    @Test
    public void shouldReturnStatusCodeOkWhenGettingMovieById() {
        //given
        when(movieFacade.getMovieDtoById(any(Long.class)))
                .thenReturn(movieDtoMock);
        //when
        ResponseEntity<MovieDto> responseEntity =
                testRestTemplate.getForEntity(URL + localPort + "/movie?movieId=10", MovieDto.class);

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
                .delete(URL + localPort + "/movie?idToDelete=100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        //then
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnStatusCodeOkWhenEditingMovie() throws Exception {
        //given
        when(movieFacade.saveEditedMovie(any(Long.class), any(MovieDto.class)))
                .thenReturn(movieDtoMock);
        MovieDto movieDto = super.prepareMovieDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String string = URL + localPort + "/movie?id=" + movieSavedId;
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
    public void shouldReturnStatusCodeOkAndReturnMovieListWhenGettingAllMovies() {
        //given
        ArrayList<MovieDto> movieDtoList = new ArrayList<>();
        movieDtoList.add(movieDtoMock);
        movieDtoList.add(super.prepareMovieDto());
        when(movieFacade.getAllMovies())
                .thenReturn(movieDtoList);
        //when

        ResponseEntity<List> responseEntity =
                testRestTemplate.getForEntity("http://localhost:" + localPort + "/movie", List.class);
        //then
        List<MovieDto> allMoviesList = (List<MovieDto>)responseEntity.getBody().stream()
                .map(movieDtoMap -> mapToMovieDto((Map<String, Object>) movieDtoMap))
                .collect(Collectors.toList());
        HttpStatus statusCode = responseEntity.getStatusCode();
        Assert.assertEquals(2, allMoviesList.size());
        Assert.assertEquals(HttpStatus.OK, statusCode);
    }

    private MovieDto mapToMovieDto(Map<String, Object> map){
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(map.get("title").toString());
        return movieDto;
    }
}
