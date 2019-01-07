package pl.superCinema.backend.api.controllers.crewController;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.api.dto.CrewDto;
import pl.superCinema.backend.domain.model.Crew;
import pl.superCinema.backend.domain.model.CrewRole;
import pl.superCinema.backend.domain.model.Movie;
import pl.superCinema.backend.domain.repository.CrewRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CrewFacade {

    private CrewRepository crewRepository;
    private CrewBuilder crewBuilder;

    public CrewDto addCrew(CrewDto crewDto) {
        Crew crew = crewBuilder.crewDtoToCrew(crewDto);
        Crew savedCrew = crewRepository.save(crew);
        return crewBuilder.crewToCrewDto(savedCrew);
    }

    public CrewDto getCrew(Long id) {
        Optional<Crew> crew = crewRepository.findById(id);
        return crewBuilder.crewToCrewDto(crew.get());
    }

    public CrewDto deleteCrew(Long id) {
        if (crewRepository.existsById(id)) {
            Optional<Crew> crew = crewRepository.findById(id);
            crewRepository.deleteById(id);
            return crewBuilder.crewToCrewDto(crew.get());
        } else return null;
    }

    public List<CrewDto> getAllCrew() {
        List<Crew> allCrew = crewRepository.findAll();
        List<CrewDto> allCrewDtos = new ArrayList<>();
        if (!allCrew.isEmpty()) {
            for (Crew crew : allCrew) {
                allCrewDtos.add(crewBuilder.crewToCrewDto(crew));
            }
        }
        return allCrewDtos;
    }


    public CrewDto updateCrew(Long id, CrewDto crewDto) {
        Crew crew = getMovieEntityById(id);
        crew = editCrew(crew,crewDto);
        crewRepository.save(crew);
        return crewBuilder.crewToCrewDto(crew);
    }

    private Crew getMovieEntityById(Long id) {
        return crewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Movie with id: " + id + " not found"));
    }

    private Crew editCrew(Crew crew, CrewDto crewDto) {
        crew.setName(crewDto.getName());
        crew.setSurname(crewDto.getSurname());
        List<CrewRole> crewRoles = crewDto.getCrewRoles()
                .stream()
                .map(x -> CrewRole.valueOf(x.name()))
                .collect(Collectors.toList());
        crew.setCrewRoles(crewRoles);

        return crew;
    }

    public void assignMovieToCrew(List<Crew> crewList, Movie movie) {
        if (crewList == null) {
            return;
        }

        crewList.stream()
                .forEach(director -> {
                    crewRepository.findById(director.getId()).ifPresent(
                            directorFounded -> {
                                List<Movie> directedMovies = directorFounded.getDirectedMovies();
                                if (!directedMovies.contains(movie)) {
                                    directedMovies.add(movie);
                                    directorFounded.setDirectedMovies(directedMovies);
                                    crewRepository.save(directorFounded);
                                }
                            }
                    );
                });
    }
}
