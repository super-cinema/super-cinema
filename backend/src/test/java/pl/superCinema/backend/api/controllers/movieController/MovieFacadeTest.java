package pl.superCinema.backend.api.controllers.movieController;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.api.dto.TypeDto;
import pl.superCinema.backend.domain.model.Movie;
import pl.superCinema.backend.domain.repository.MovieRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
@ActiveProfiles(profiles = "test")
@Transactional
public class MovieFacadeTest {

    @Autowired
    private MovieFacade movieFacade;

    @Autowired
    private MovieRepository movieRepository;

    private Movie movieSavedBeforeTests;
    private  Long savedMovieId;

    @Before
    public void setUp(){
        movieSavedBeforeTests = new Movie();
        movieSavedBeforeTests.setTitle("Iron Man");
        movieSavedBeforeTests.setDuration(100);
        movieRepository.save(movieSavedBeforeTests);
        savedMovieId = movieSavedBeforeTests.getId();
    }

    @Test
    public void shouldSaveMovie() {
        //given
        CrewDto directorDto1 = new CrewDto();
        CrewDto directorDto2 = new CrewDto();
        List<CrewDto> directorsDto = new ArrayList<>();
        directorsDto.addAll(Arrays.asList(directorDto1, directorDto2));

        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("title");
        movieDto.setDuration(120);
        movieDto.setTypes(Arrays.asList(TypeDto.ACTION));
        movieDto.setDirectors(directorsDto);
        //when
        MovieDto movieDto1 = movieFacade.saveMovie(movieDto);
        //then
        MovieDto foundMovie = movieFacade.getMovieDtoById(1L);
        Assert.assertNotNull(foundMovie);
        Assert.assertEquals("title", foundMovie.getTitle());
        Assert.assertEquals(120, foundMovie.getDuration().intValue());
        Assert.assertEquals(movieDto.getTypes(),foundMovie.getTypes());
        Assert.assertNull(foundMovie.getCast());
}

    @Test
    public void shouldGetMovieById() {
        //given

        //when
        MovieDto movieFoundById = movieFacade.getMovieDtoById(savedMovieId);
        //then
        Assert.assertNotNull(movieFoundById);
        Assert.assertEquals(movieSavedBeforeTests.getTitle(), movieFoundById.getTitle());
        Assert.assertEquals(movieSavedBeforeTests.getDuration(), movieFoundById.getDuration());
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