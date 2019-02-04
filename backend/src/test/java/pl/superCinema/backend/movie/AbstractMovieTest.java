package pl.superCinema.backend.movie;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.superCinema.backend.BackendApplication;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.infrastructure.dto.MovieDto;
import pl.superCinema.backend.infrastructure.dto.TypeDto;
import pl.superCinema.backend.domain.models.Crew;
import pl.superCinema.backend.domain.models.Movie;
import pl.superCinema.backend.domain.models.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@ActiveProfiles(profiles = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
public abstract class AbstractMovieTest {

    List<Crew> prepareCrewList(){
        Crew crew1 = new Crew();
        Crew crew2 = new Crew();
        List<Crew> crew = new ArrayList<>();
        crew.addAll(Arrays.asList(crew1, crew2));
        return crew;
    }

    List<CrewDto> prepareCrewDtoList(){
        CrewDto crewDto1 = new CrewDto();
        CrewDto crewDto2 = new CrewDto();
        List<CrewDto> crewDto = new ArrayList<>();
        crewDto.addAll(Arrays.asList(crewDto1, crewDto2));
        return crewDto;
    }

    Movie prepareMovie(){
        Movie movie = new Movie();
        movie.setTitle("title");
        movie.setDuration(100);
        movie.setProductionCountry("PL");
        movie.setProductionYear(2010);
        movie.setTypes(new LinkedList<>(Arrays.asList(Type.HORROR, Type.HISTORICAL)));
        return movie;
    }

    MovieDto prepareMovieDto(){
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("title");
        movieDto.setDuration(100);
        movieDto.setProductionCountry("PL");
        movieDto.setProductionYear(2010);
        movieDto.setTypes(new LinkedList<>(Arrays.asList(TypeDto.HORROR, TypeDto.HISTORICAL)));
        return movieDto;
    }

}
