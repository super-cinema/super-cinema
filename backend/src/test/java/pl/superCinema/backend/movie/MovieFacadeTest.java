package pl.superCinema.backend.movie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.infrastructure.dto.MovieDto;
import pl.superCinema.backend.infrastructure.dto.TypeDto;
import pl.superCinema.backend.domain.facades.MovieFacade;
import pl.superCinema.backend.domain.models.Crew;
import pl.superCinema.backend.domain.models.Movie;
import pl.superCinema.backend.domain.ports.CrewRepository;
import pl.superCinema.backend.domain.ports.MovieRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class MovieFacadeTest extends AbstractMovieTest {
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
        movieSavedBeforeTests = super.prepareMovie();
        movieRepository.save(movieSavedBeforeTests);
        savedMovieId = movieSavedBeforeTests.getId();
        Movie movieSavedBeforeTests2 = new Movie();
        movieSavedBeforeTests2.setTitle("Wonder Woman");
        movieSavedBeforeTests2.setDuration(99);
        movieRepository.save(movieSavedBeforeTests2);
        List<Crew> directors = super.prepareCrewList();
        List<CrewDto> directorsDto = super.prepareCrewDtoList();
        crewRepository.save(directors.get(0));
        crewRepository.save(directors.get(1));
        directorsDto.get(0).setId(directors.get(0).getId());
        directorsDto.get(1).setId(directors.get(1).getId());
        this.directorsDto = new ArrayList<>();
        this.directorsDto.addAll(directorsDto);

    }

    @Test
    public void shouldSaveMovie() {
        //given
        MovieDto expectedMovieDto = new MovieDto();
        expectedMovieDto.setTitle("title");
        expectedMovieDto.setDuration(120);
        expectedMovieDto.setTypes(Arrays.asList(TypeDto.ACTION));
        expectedMovieDto.setDirectors(directorsDto);
        //when
        MovieDto movieDtoSaved = movieFacade.saveMovie(expectedMovieDto);
        //then
        MovieDto foundMovie = movieFacade.getMovieDtoById(movieDtoSaved.getId());
        Assert.assertNotNull(foundMovie);
        expectedMovieDto.setId(foundMovie.getId());
        Assert.assertEquals(expectedMovieDto, foundMovie);
        Assert.assertEquals("title", foundMovie.getTitle());
        Assert.assertEquals(120, foundMovie.getDuration().intValue());
        Assert.assertEquals(expectedMovieDto.getTypes(), foundMovie.getTypes());
        Assert.assertNull(foundMovie.getCast());
        Assert.assertEquals(expectedMovieDto.getDirectors(), foundMovie.getDirectors());
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
    public void shouldGetAllMovies() {
        //given

        //when
        List<MovieDto> allMovies = movieFacade.getAllMovies();
        //then
        List<String> moviesTitleList = allMovies.stream()
                .map(movieDto -> movieDto.getTitle())
                .collect(Collectors.toList());
        Assert.assertEquals(2, allMovies.size());
        Assert.assertEquals(Arrays.asList("title", "Wonder Woman"), moviesTitleList);
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