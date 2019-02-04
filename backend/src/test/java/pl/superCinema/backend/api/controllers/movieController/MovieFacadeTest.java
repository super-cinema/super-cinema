package pl.superCinema.backend.api.controllers.movieController;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.api.dto.MovieDto;
import pl.superCinema.backend.api.dto.TypeDto;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.Movie;
import pl.superCinema.backend.domain.repository.CrewRepository;
import pl.superCinema.backend.domain.repository.MovieRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@ActiveProfiles(profiles = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
public class MovieFacadeTest {
    @Autowired
    private MovieFacade movieFacade;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CrewRepository crewRepository;

    private Movie movieSavedBeforeTests;
    private Long savedMovieId;
    private List<CrewDto> directorsDto;

    @Before
    public void setUp() {
        movieSavedBeforeTests = new Movie();
        movieSavedBeforeTests.setTitle("Iron Man");
        movieSavedBeforeTests.setDuration(100);
        movieRepository.save(movieSavedBeforeTests);
        savedMovieId = movieSavedBeforeTests.getId();
        Movie movieSavedBeforeTests2 = new Movie();
        movieSavedBeforeTests2.setTitle("Wonder Woman");
        movieSavedBeforeTests2.setDuration(99);
        movieRepository.save(movieSavedBeforeTests2);
        CrewDto directorDto1 = new CrewDto();
        CrewDto directorDto2 = new CrewDto();
        Crew director1 = new Crew();
        Crew director2 = new Crew();
        crewRepository.save(director1);
        crewRepository.save(director2);
        directorDto1.setId(director1.getId());
        directorDto2.setId(director2.getId());
        directorsDto = new ArrayList<>();
        directorsDto.addAll(Arrays.asList(directorDto1, directorDto2));

    }

    @Test
    public void shouldSaveMovie() {
        //given
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("title");
        movieDto.setDuration(120);
        movieDto.setTypes(Arrays.asList(TypeDto.ACTION));
        movieDto.setDirectors(directorsDto);
        //when
        MovieDto movieDtoSaved = movieFacade.saveMovie(movieDto);
        //then
        MovieDto foundMovie = movieFacade.getMovieDtoById(movieDtoSaved.getId());
        Assert.assertNotNull(foundMovie);
        Assert.assertEquals("title", foundMovie.getTitle());
        Assert.assertEquals(120, foundMovie.getDuration().intValue());
        Assert.assertEquals(movieDto.getTypes(), foundMovie.getTypes());
        Assert.assertNull(foundMovie.getCast());
        Assert.assertEquals(movieDto.getDirectors(), foundMovie.getDirectors());

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
        //given
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Iron Man 2");
        movieDto.setDuration(120);
        movieDto.setCast(directorsDto);
        //when
        MovieDto movieDtoSaved = movieFacade.saveEditedMovie(savedMovieId, movieDto);
        //then
        Assert.assertNotNull(movieDtoSaved);
        Assert.assertEquals(movieDto.getTitle(), movieDtoSaved.getTitle());
        Assert.assertEquals(movieDto.getDuration(), movieDtoSaved.getDuration());
        Assert.assertEquals(movieDto.getDirectors(), movieDtoSaved.getDirectors());
        Assert.assertEquals(movieDto.getCast(), movieDtoSaved.getCast());

    }

    @Test
    public void getAllMovies() {
        //given

        //when
        List<MovieDto> allMovies = movieFacade.getAllMovies();
        //then
        List<String> moviesTitleList = allMovies.stream()
                .map(movieDto -> movieDto.getTitle())
                .collect(Collectors.toList());
        Assert.assertEquals(2, allMovies.size());
        Assert.assertEquals(Arrays.asList("Iron Man", "Wonder Woman"), moviesTitleList);
    }

    @Test
    public void shouldDeleteMovieById() {
        //given
        boolean isMoviePresentBeforeDelete = movieRepository.findById(savedMovieId).isPresent();
        //when
        movieFacade.deleteMovie(savedMovieId);
        //then
        boolean isMoviePresentAfterDelete = movieRepository.findById(savedMovieId).isPresent();
        Assert.assertTrue(isMoviePresentBeforeDelete);
        Assert.assertFalse(isMoviePresentAfterDelete);

    }
}