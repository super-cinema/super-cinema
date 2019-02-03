package pl.superCinema.backend.api.controllers.movieController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.api.dto.TypeDto;
import pl.superCinema.backend.domain.exceptions.EntityCouldNotBeFoundException;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerMockitoTest {
    @Mock
    private MovieFacade movieFacade;

    private MovieController movieController;

    @Before
    public void setUp(){
        movieController = new MovieController(movieFacade);
    }

    @Test
    public void shouldSaveMovie(){
        //given
        CrewDto actor = new CrewDto();
        MovieDto movieDtoMock = new MovieDto();
        movieDtoMock.setId(1L);
        movieDtoMock.setTitle("Aquaman");
        movieDtoMock.setDuration(120);
        movieDtoMock.setProductionCountry("USA");
        movieDtoMock.setProductionYear(2008);
        movieDtoMock.setTypes(Arrays.asList(TypeDto.COMEDY, TypeDto.ACTION));
        movieDtoMock.setCast(Arrays.asList(actor));
        when(movieFacade.saveMovie(any(MovieDto.class)))
                .thenReturn(movieDtoMock);
        when(movieFacade.saveMovie(any(MovieDto.class)))
                .thenThrow(new EntityCouldNotBeFoundException("not found"));
        //when
        ResponseEntity responseEntity = movieController.saveMovie(new MovieDto());
        //then

    }

}
