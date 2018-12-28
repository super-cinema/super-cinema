package pl.superCinema.backend.api.controllers.movieController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.domain.repository.MovieRepository;

@Configuration
public class MovieConfiguration {
    @Bean
    MovieFacade movieFacade(MovieRepository movieRepository, MovieBuilderService movieBuilderService) {
        return new MovieFacade(movieRepository, movieBuilderService);
    }

    @Bean
    MovieBuilderService movieBuilderService() {
        return new MovieBuilderService();
    }
}
