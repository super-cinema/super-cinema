package pl.superCinema.backend.api.controllers.movieController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.api.controllers.crewController.CrewBuilder;
import pl.superCinema.backend.api.controllers.crewController.CrewFacade;
import pl.superCinema.backend.domain.repository.MovieRepository;

@Configuration
public class MovieConfiguration {
    @Bean
    MovieFacade movieFacade(MovieRepository movieRepository, MovieBuilder movieBuilder, CrewFacade crewFacade) {
        return new MovieFacade(movieRepository, movieBuilder, crewFacade);
    }

    @Bean
    MovieBuilder movieBuilder(CrewBuilder crewBuilder) {
        return new MovieBuilder(crewBuilder);
    }
}
