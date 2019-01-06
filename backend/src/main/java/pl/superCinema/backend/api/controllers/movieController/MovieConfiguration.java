package pl.superCinema.backend.api.controllers.movieController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.domain.repository.MovieRepository;

@Configuration
public class MovieConfiguration {
    @Bean
    MovieFacade movieFacadeAg(MovieRepository movieRepository, MovieBuilder movieBuilder) {
        return new MovieFacade(movieRepository, movieBuilder);
    }

    @Bean
    MovieBuilder movieBuilderService() {
        return new MovieBuilder();
    }
}
