package pl.superCinema.backend.api.controllers.cinemaHallController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.domain.repository.CinemaHallRepository;

@Configuration
public class CinemaHallConfiguration {

    @Bean
    CinemaHallFacade cinemaHallFacade(CinemaHallRepository cinemaHallRepository, CinemaHallBuilderService cinemaHallBuilderService) {
        return new CinemaHallFacade(cinemaHallRepository, cinemaHallBuilderService);
    }

    @Bean
    CinemaHallBuilderService cinemaHallBuilderService(){
        return new CinemaHallBuilderService();
    }

}
