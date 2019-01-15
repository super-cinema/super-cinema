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

    CrewDto addCrew(CrewDto crewDto) {
        Crew crew = crewBuilder.dtoToEntity(crewDto);
        Crew savedCrew = crewRepository.save(crew);
        return crewBuilder.entityToDto(savedCrew);
    }

    CrewDto updateCrew(Long id, CrewDto crewDto) {
        Crew crew = getMovieEntityById(id);
        crew = editCrew(crew, crewDto);
        crewRepository.save(crew);
        return crewBuilder.entityToDto(crew);
    }

    List<CrewDto> getAllCrew() {
        List<Crew> allCrew = crewRepository.findAll();
        List<CrewDto> allCrewDtos = new ArrayList<>();
        if (!allCrew.isEmpty()) {
            allCrew.forEach(crew ->
                    allCrewDtos.add(crewBuilder.entityToDto(crew)));
        }
        return allCrewDtos;
    }

    CrewDto getCrew(Long id) {
        Optional<Crew> crew = crewRepository.findById(id);
        return crewBuilder.entityToDto(crew.get());
    }

    CrewDto deleteCrew(Long id) {
        if (crewRepository.existsById(id)) {
            Optional<Crew> crew = crewRepository.findById(id);
            crewRepository.deleteById(id);
            return crewBuilder.entityToDto(crew.get());
        } else return null;
    }

    void deleteAllCrew() {
        crewRepository.deleteAll();
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

    public void assignMovieToCrew(List<CrewDto> crewListDto, Movie movie, CrewRole crewRole) {
        if (crewListDto == null) {
            return;
        }
        crewListDto.stream()
                .forEach(director -> {
                    crewRepository.findById(director.getId()).ifPresent(
                            crewFounded -> {
                                setMovieToCrewFounded(movie, crewFounded, crewRole);
                            }
                    );
                });
        }

    private void setMovieToCrewFounded(Movie movie, Crew crewFounded, CrewRole crewRole) {
        if(crewRole.equals(CrewRole.DIRECTOR)){
            List<Movie> directedMovies = crewFounded.getDirectedMovies();
            if (!directedMovies.contains(movie)) {
                directedMovies.add(movie);
                crewFounded.setDirectedMovies(directedMovies);
                crewRepository.save(crewFounded);
            }
        }
        List<Movie> starredMovies = crewFounded.getStarredMovies();
        if(!starredMovies.contains(movie)){
            starredMovies.add(movie);
            crewFounded.setStarredMovies(starredMovies);
            crewRepository.save(crewFounded);
        }

    }

    public void setCrewListToMovie(List<CrewDto> crewDto, Movie movie, CrewRole crewRole) {
        if(crewDto == null){
            return;
        }
        if(crewRole.equals(CrewRole.DIRECTOR)){
            List<Crew> directors = new ArrayList<>();
            crewDto.stream()
                    .forEach(director -> {
                        crewRepository.findById(director.getId()).ifPresent(
                                directorFounded -> directors.add(directorFounded)
                        );
                    });
            movie.setDirectors(directors);
            return;
        }
        //if actors
        List<Crew> actors = new ArrayList<>();
        crewDto.stream()
                .forEach(actor -> {
                    crewRepository.findById(actor.getId()).ifPresent(
                            actorFounded -> actors.add(actorFounded)
                    );
                });
        movie.setCast(actors);
    }
}
