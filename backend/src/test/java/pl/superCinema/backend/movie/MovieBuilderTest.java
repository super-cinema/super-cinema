package pl.superCinema.backend.movie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.superCinema.backend.infrastructure.builders.CrewBuilder;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.infrastructure.dto.MovieDto;
import pl.superCinema.backend.infrastructure.builders.MovieBuilder;
import pl.superCinema.backend.domain.models.Crew;
import pl.superCinema.backend.domain.models.Movie;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MovieBuilderTest extends AbstractMovieTest {

    @Autowired
    MovieBuilder movieBuilder;

    List<CrewDto> directorsDto;
    List<CrewDto> actorsDto;
    Movie movie;
    MovieDto movieDto;
    List<Crew> directors;
    List<Crew> actors;

    @MockBean
    CrewBuilder crewBuilder;

    @Before
    public void setUp() {
        actors = super.prepareCrewList();
        directors = super.prepareCrewList();
        when(crewBuilder.dtoToEntity(any(CrewDto.class)))
                .thenReturn(actors.get(0))
                .thenReturn(actors.get(1))
                .thenReturn(directors.get(0))
                .thenReturn(directors.get(1));

        actorsDto = super.prepareCrewDtoList();
        directorsDto = super.prepareCrewDtoList();
        when(crewBuilder.entityToDto(any(Crew.class)))
                .thenReturn(actorsDto.get(0))
                .thenReturn(actorsDto.get(1))
                .thenReturn(directorsDto.get(0))
                .thenReturn(directorsDto.get(1));
    }

    @Test
    public void shouldReturnMovieEntityFromDto() {
        //given
        this.movieDto = super.prepareMovieDto();
        movieDto.setDirectors(directorsDto);
        movieDto.setCast(actorsDto);
        //when
        Movie movieFromDto = movieBuilder.dtoToEntity(movieDto);
        //then
        Assert.assertEquals(movieDto.getTitle(), movieFromDto.getTitle());
        Assert.assertEquals(movieDto.getDuration(), movieFromDto.getDuration());
        Assert.assertEquals(movieDto.getProductionYear(), movieFromDto.getProductionYear());
        Assert.assertEquals(movieDto.getProductionCountry(), movieFromDto.getProductionCountry());
        Assert.assertEquals(actors, movieFromDto.getCast());
        Assert.assertEquals(directors, movieFromDto.getDirectors());
    }

    @Test
    public void shouldReturnMovieDtoFromEntity() {
        //given
        this.movie = super.prepareMovie();
        this.movie.setDirectors(directors);
        this.movie.setCast(actors);
        //when
        MovieDto movieDtoFromMovie = movieBuilder.entityToDto(this.movie);
        //then
        Assert.assertEquals(this.movie.getId(), movieDtoFromMovie.getId());
        Assert.assertEquals(this.movie.getTitle(), movieDtoFromMovie.getTitle());
        Assert.assertEquals(this.movie.getDuration(), movieDtoFromMovie.getDuration());
        Assert.assertEquals(this.movie.getProductionYear(), movieDtoFromMovie.getProductionYear());
        Assert.assertEquals(this.movie.getProductionCountry(), movieDtoFromMovie.getProductionCountry());
        Assert.assertEquals(actorsDto, movieDtoFromMovie.getCast());
        Assert.assertEquals(directorsDto, movieDtoFromMovie.getDirectors());
    }


}