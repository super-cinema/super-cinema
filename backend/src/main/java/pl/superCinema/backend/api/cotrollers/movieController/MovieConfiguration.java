package pl.superCinema.backend.api.cotrollers.movieController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.superCinema.backend.api.cotrollers.MovieShowController.MovieShowBuilder;
import pl.superCinema.backend.api.cotrollers.crewController.CrewBuilder;
import pl.superCinema.backend.domain.repository.MovieRepository;

@Configuration
public class MovieConfiguration {

    @Bean
    MovieFacade movieFacade(@Lazy MovieRepository movieRepository,@Lazy MovieBuilder movieBuilder) {
        return new MovieFacade(movieRepository, movieBuilder);
    }

    @Bean
    MovieBuilder movieBuilder(@Lazy CrewBuilder crewBuilder,@Lazy MovieShowBuilder movieShowBuilder) {
        return new MovieBuilder(crewBuilder, movieShowBuilder);
    }









}
