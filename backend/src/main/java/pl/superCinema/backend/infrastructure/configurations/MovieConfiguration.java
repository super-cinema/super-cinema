package pl.superCinema.backend.infrastructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.infrastructure.builders.CrewBuilder;
import pl.superCinema.backend.infrastructure.builders.MovieBuilder;
import pl.superCinema.backend.domain.facades.CrewFacade;
import pl.superCinema.backend.domain.facades.MovieFacade;
import pl.superCinema.backend.domain.ports.MovieRepository;

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
