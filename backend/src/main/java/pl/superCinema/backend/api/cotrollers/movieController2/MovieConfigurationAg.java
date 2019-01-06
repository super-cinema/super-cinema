package pl.superCinema.backend.api.cotrollers.movieController2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.domain.repository.MovieRepository;

@Configuration
public class MovieConfigurationAg {
    @Bean
    MovieFacadeAg movieFacadeAg(MovieRepository movieRepository, MovieBuilderServiceAg movieBuilderServiceAg) {
        return new MovieFacadeAg(movieRepository, movieBuilderServiceAg);
    }

    @Bean
    MovieBuilderServiceAg movieBuilderService() {
        return new MovieBuilderServiceAg();
    }
}
