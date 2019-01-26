package pl.superCinema.backend.api.controllers.movieController;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.api.dto.TypeDto;
import pl.superCinema.backend.domain.model.Crew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
@ActiveProfiles(profiles = "test")
public class MovieControllerTest {
    @LocalServerPort
    int localPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    MovieFacade movieFacade;

    CrewDto actor = new CrewDto();
    CrewDto director = new CrewDto();
    ArrayList<CrewDto> actors = new ArrayList<>();
    ArrayList<CrewDto> directors = new ArrayList<>();
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
        when(movieFacade.saveMovie(any(MovieDto.class)))
                .thenReturn(movieDtoMock);
    }


    @Test
    public void shouldSaveMovie() {
        //given
        MovieDto movieDto = new MovieDto();

        //when
        ResponseEntity<MovieDto> movieDtoResponseEntity =
                this.testRestTemplate.postForEntity("http://localhost:" + localPort + "/movie", movieDto, MovieDto.class);
        MovieDto savedMovieDto = movieDtoResponseEntity.getBody();
        //then
        Assert.assertEquals(movieDtoMock.getTitle(), savedMovieDto.getTitle());
        Assert.assertEquals(movieDtoMock.getDuration(), savedMovieDto.getDuration());
        Assert.assertEquals(movieDtoMock.getProductionYear(), savedMovieDto.getProductionYear());
        Assert.assertEquals(movieDtoMock.getProductionCountry(), savedMovieDto.getProductionCountry());
        Assert.assertEquals(movieDtoMock.getTypes(), savedMovieDto.getTypes());
        Assert.assertEquals(movieDtoMock.getCast(), savedMovieDto.getCast());
        Assert.assertEquals(movieDtoMock.getDirectors(), savedMovieDto.getDirectors());
    }

    @Test
    public void shouldFindMovieById() {
        //given
        when(movieFacade.getMovieDtoById(1L))
                .thenReturn(movieDtoMock);
        //when

        //then

    }
}
