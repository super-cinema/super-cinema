package pl.superCinema.backend.api.controllers.movieController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.domain.model.Movie;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class MovieBuilderTest {


    MovieBuilder movieBuilder;
    Movie movie;
    MovieDto movieDto;

    @Before
    public void setUp(){

        movie = new Movie();
        movieDto = new MovieDto();



    }

    @Test
    public void shouldReturnMovieEntityFromDto() {
        //given
        movieDto = new MovieDto();
        movieDto.setId((long) 1);
        movieDto.setDuration(120);
        movieDto.setProductionCountry("USA");
        movieDto.setProductionYear(2008);



        //when

        //then
    }

    @Test
    public void dtoFromEntity() {
    }
}