package pl.superCinema.backend.api.controllers.movieController;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.api.dto.TypeDto;

import javax.transaction.Transactional;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
@ActiveProfiles(profiles = "test")
@Transactional
public class MovieFacadeTest {

    @Autowired
    MovieFacade movieFacade;

    @Test
    public void saveMovie() {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("title");
        movieDto.setDuration(120);
        movieDto.setTypes(Arrays.asList(TypeDto.ACTION));
        MovieDto movieDto1 = movieFacade.saveMovie(movieDto);
        MovieDto movieByTitle = movieFacade.getMovieDtoById(1L);
        Assert.assertEquals("title", movieByTitle.getTitle());
        System.out.println("");
    }

    @Test
    public void getMovieByTitle() {
    }

    @Test
    public void getMovieById() {
    }

    @Test
    public void saveEditedMovie() {
    }

    @Test
    public void deleteMovieByTitle() {
    }

    @Test
    public void getAllMovies() {
    }

    @Test
    public void deleteMovie() {
    }
}