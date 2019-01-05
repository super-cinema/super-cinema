package pl.superCinema.backend.api.cotroller.crewController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.superCinema.backend.domain.repository.CrewRepository;

@Configuration
public class CrewConfiguration {

    @Bean
    CrewBuilder crewBuilderService() {
        return new CrewBuilder();
    }

    @Bean
    CrewFacade crewService(@Lazy CrewRepository crewRepository,@Lazy CrewBuilder crewBuilder){
        return new CrewFacade(crewRepository, crewBuilder);
    }
}
