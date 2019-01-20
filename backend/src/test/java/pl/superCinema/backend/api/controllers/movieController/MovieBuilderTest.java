package pl.superCinema.backend.api.controllers.movieController;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.api.controllers.crewController.CrewBuilder;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.Movie;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
public class MovieBuilderTest {

    @Autowired
    MovieBuilder movieBuilder;
    CrewDto director1;
    CrewDto director2;
    CrewDto actor1;
    CrewDto actor2;
    List<CrewDto> directorsDto;
    List<CrewDto> castDto;

    Movie movie;
    MovieDto movieDto;

    @MockBean
    CrewBuilder crewBuilder;

    @Before
    public void setUp(){

        movie = new Movie();
        movieDto = new MovieDto();
        director1 = new CrewDto();
        director2 = new CrewDto();
        actor1 = new CrewDto();
        actor2 = new CrewDto();
        ArrayList<Object> directors = new ArrayList<>();
        ArrayList<Object> cast = new ArrayList<>();

        when(crewBuilder.entityToDto(any(Crew.class)))
                .thenReturn(director1)
                .thenReturn(director2)
                .thenReturn(actor1)
                .thenReturn(actor2);

    }

    @Test
    public void shouldReturnMovieEntityFromDto() {
        //given
        movieDto = new MovieDto();
        movieDto.setId((long) 1);
        movieDto.setDuration(120);
        movieDto.setProductionCountry("USA");
        movieDto.setProductionYear(2008);
        movieDto.setDirectors(directorsDto);
        movieDto.setCast(castDto);

        //when
        Movie movieFromDto = movieBuilder.dtoToEntity(movieDto);

        //then
        Assert.assertEquals(movieDto.getId(), movieFromDto.getId());
        Assert.assertEquals(movieDto.getDuration(), movieFromDto.getDuration());
        Assert.assertEquals(movieDto.getProductionYear(), movieFromDto.getProductionYear());
        Assert.assertEquals(movieDto.getProductionCountry(), movieFromDto.getProductionCountry());
        Assert.assertEquals(movieDto.getCast(), movieFromDto.getCast());
        Assert.assertEquals(movieDto.getDirectors(), movieFromDto.getDirectors());
    }

    @Test
    public void dtoFromEntity() {
    }
}