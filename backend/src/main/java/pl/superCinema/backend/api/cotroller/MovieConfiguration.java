package pl.superCinema.backend.api.cotroller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.domain.model.CinemaRepository;

@Configuration
public class MovieConfiguration {

    @Bean
    MovieService movieService(CinemaRepository cinemaRepository) {
        return new MovieService(cinemaRepository);
    }


}
