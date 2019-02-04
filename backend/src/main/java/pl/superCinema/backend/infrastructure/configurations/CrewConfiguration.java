package pl.superCinema.backend.infrastructure.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.superCinema.backend.infrastructure.builders.CrewBuilder;
import pl.superCinema.backend.domain.facades.CrewFacade;
import pl.superCinema.backend.domain.ports.CrewRepository;

@Configuration
public class CrewConfiguration {

    @Bean
    CrewBuilder crewBuilderService() {
        return new CrewBuilder();
    }

    @Bean
    CrewFacade crewFacade(CrewRepository crewRepository, CrewBuilder crewBuilder) {
        return new CrewFacade(crewRepository, crewBuilder);
    }
}

