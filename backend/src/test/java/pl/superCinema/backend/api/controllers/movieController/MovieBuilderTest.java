package pl.superCinema.backend.api.controllers.movieController;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.api.controllers.crewController.CrewBuilder;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.api.dto.TypeDto;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.Movie;
import pl.superCinema.backend.domain.model.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
@ActiveProfiles(profiles = "test")
public class MovieBuilderTest {

    @Autowired
    MovieBuilder movieBuilder;

    //dtos
    MovieDto movieDto;
    CrewDto directorDto1;
    CrewDto directorDto2;
    CrewDto actorDto1;
    CrewDto actorDto2;
    List<CrewDto> directorsDto;
    List<CrewDto> castDto;
    //entities
    Movie movie;
    Crew director2;
    Crew director1;
    Crew actor1;
    Crew actor2;
    List<Crew> directors;
    List<Crew> cast;




    @MockBean
    CrewBuilder crewBuilder;

    @Before
    public void setUp(){
        //dtos
        movieDto = new MovieDto();
        directorDto1 = new CrewDto();
        directorDto2 = new CrewDto();
        actorDto1 = new CrewDto();
        actorDto2 = new CrewDto();
        directorsDto = new ArrayList<>();
        castDto = new ArrayList<>();
        directorsDto.addAll(Arrays.asList(directorDto1, directorDto2));
        castDto.addAll(Arrays.asList(actorDto1, actorDto2));

        when(crewBuilder.dtoToEntity(any(CrewDto.class)))
                .thenReturn(director1)
                .thenReturn(director2)
                .thenReturn(actor1)
                .thenReturn(actor2);
        //entities
        movie = new Movie();
        director2 = new Crew();
        director1 = new Crew();
        actor1 = new Crew();
        actor2 = new Crew();
        directors = new ArrayList<>();
        cast = new ArrayList<>();
        directors.addAll(Arrays.asList(director1, director2));
        cast.addAll(Arrays.asList(actor1, actor2));
        when(crewBuilder.entityToDto(any(Crew.class)))
                .thenReturn(directorDto1)
                .thenReturn(directorDto2)
                .thenReturn(actorDto1)
                .thenReturn(actorDto2);
    }

    @Test
    public void shouldReturnMovieEntityFromDto() {
        //given
        movieDto = new MovieDto();
        movieDto.setId((long) 1);
        movie.setTitle("Arizona");
        movieDto.setDuration(120);
        movieDto.setProductionCountry("USA");
        movieDto.setProductionYear(2008);
        movieDto.setTypes(Arrays.asList(TypeDto.COMEDY, TypeDto.ACTION));
        movieDto.setDirectors(directorsDto);
        movieDto.setCast(castDto);

        //when
        Movie movieFromDto = movieBuilder.dtoToEntity(movieDto);

        //then
        Assert.assertEquals(movieDto.getId(), movieFromDto.getId());
        Assert.assertEquals(movieDto.getTitle(), movieFromDto.getTitle());
        Assert.assertEquals(movieDto.getDuration(), movieFromDto.getDuration());
        Assert.assertEquals(movieDto.getProductionYear(), movieFromDto.getProductionYear());
        Assert.assertEquals(movieDto.getProductionCountry(), movieFromDto.getProductionCountry());
        Assert.assertEquals(Arrays.asList(actor1, actor2), movieFromDto.getCast());
        Assert.assertEquals(Arrays.asList(director1, director2), movieFromDto.getDirectors());
    }

    @Test
    public void shouldReturnMovieDtoFromEntity() {
        //given
        movie.setId((long) 2);
        movie.setTitle("title");
        movie.setDuration(100);
        movie.setProductionCountry("PL");
        movie.setProductionYear(2010);
        movie.setTypes(Arrays.asList(Type.HORROR, Type.HISTORICAL));
        movie.setCast(cast);
        movie.setDirectors(directors);
         //when
        MovieDto movieDtoFromMovie = movieBuilder.entityToDto(movie);
        //then
        Assert.assertEquals(movie.getId(), movieDtoFromMovie.getId());
        Assert.assertEquals(movie.getTitle(), movieDtoFromMovie.getTitle());
        Assert.assertEquals(movie.getDuration(), movieDtoFromMovie.getDuration());
        Assert.assertEquals(movie.getProductionYear(), movieDtoFromMovie.getProductionYear());
        Assert.assertEquals(movie.getProductionCountry(), movieDtoFromMovie.getProductionCountry());
        Assert.assertEquals(Arrays.asList(actorDto1, actorDto2), movieDtoFromMovie.getCast());
        Assert.assertEquals(Arrays.asList(directorDto1, directorDto2), movieDtoFromMovie.getDirectors());   }





}