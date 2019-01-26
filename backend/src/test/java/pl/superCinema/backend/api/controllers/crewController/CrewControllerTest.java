package pl.superCinema.backend.api.controllers.crewController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.superCinema.backend.BackendApplication;

import static org.junit.Assert.*;
@ActiveProfiles(profiles = "test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
public class CrewControllerTest {

    @Test
    public void getMovies() {

    }

    @Test
    public void getAllCrew() {
    }

    @Test
    public void addCrew() {
    }

    @Test
    public void deleteCrew() {
    }

    @Test
    public void deleteAllCrew() {
    }

    @Test
    public void editMovie() {
    }
}