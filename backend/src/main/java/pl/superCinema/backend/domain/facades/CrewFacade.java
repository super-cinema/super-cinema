package pl.superCinema.backend.domain.facades;

import lombok.AllArgsConstructor;
import pl.superCinema.backend.infrastructure.builders.CrewBuilder;
import pl.superCinema.backend.infrastructure.dto.CrewDto;
import pl.superCinema.backend.domain.models.Crew;
import pl.superCinema.backend.domain.models.CrewRole;
import pl.superCinema.backend.domain.models.Movie;
import pl.superCinema.backend.domain.ports.CrewRepository;

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
        Crew crew = crewBuilder.dtoToEntity(crewDto);
        Crew savedCrew = crewRepository.save(crew);
        return crewBuilder.entityToDto(savedCrew);
    }

    public CrewDto updateCrew(Long id, CrewDto crewDto) {
        Crew crew = getMovieEntityById(id);
        crew = editCrew(crew, crewDto);
        crewRepository.save(crew);
        return crewBuilder.entityToDto(crew);
    }

    public List<CrewDto> getAllCrew() {
        List<Crew> allCrew = crewRepository.findAll();
        List<CrewDto> allCrewDtos = new ArrayList<>();
        if (!allCrew.isEmpty()) {
            allCrew.forEach(crew ->
                    allCrewDtos.add(crewBuilder.entityToDto(crew)));
        }
        return allCrewDtos;
    }

    public CrewDto getCrew(Long id) {
        Optional<Crew> crew = crewRepository.findById(id);
        return crewBuilder.entityToDto(crew.get());
    }

    public void deleteCrew(Long id) {
        if (crewRepository.existsById(id)) {
            Optional<Crew> crew = crewRepository.findById(id);
            crewRepository.deleteById(id);
            crewBuilder.entityToDto(crew.get());
        }
    }

    public void deleteAllCrew() {
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

    void setCrewListInMovie(List<CrewDto> crewDto, Movie movie, CrewRole crewRole) {
        List<Long> crewIds = crewDto.stream()
                .map(CrewDto::getId)
                .collect(Collectors.toList());
        setCrewInMovieById(crewIds, movie, crewRole);
    }

    void setCrewInMovieById(List<Long> crewIds, Movie movie, CrewRole crewRole) {
        if (crewIds == null || crewIds.size() == 0) {
            return;
        }
        List<Crew> directors = movie.getDirectors();
        List<Crew> cast = movie.getCast();
        List<Crew> castToSet = new ArrayList<>();
        List<Crew> directorsToSet = new ArrayList<>();

        crewIds.forEach(crewId -> crewRepository.findById(crewId).ifPresent(
                crewFounded -> addCrewFoundedToCrewListInMovie(movie, crewRole, directors, cast,
                        castToSet, directorsToSet, crewFounded)
        ));
    }

    void deleteCrewFromMovie(List<Long> actorsIdsToRemove, Movie existingMovie, CrewRole crewRole) {
        if (actorsIdsToRemove == null) {
            return;
        }
        List<Crew> cast = existingMovie.getCast();
        List<Crew> directors = existingMovie.getDirectors();
        actorsIdsToRemove
                .forEach(crewId -> crewRepository.findById(crewId).ifPresent(
                        crewFounded -> {
                            if (crewRole.equals(CrewRole.DIRECTOR)) {
                                directors.remove(crewFounded);
                                return;
                            }
                            cast.remove(crewFounded);
                        }
                ));
    }

    private void setMovieToCrewFounded(Movie movie, Crew crewFounded, CrewRole crewRole) {
        if (crewRole.equals(CrewRole.DIRECTOR)) {
            List<Movie> directedMovies = crewFounded.getDirectedMovies();
            if (!directedMovies.contains(movie)) {
                directedMovies.add(movie);
                crewFounded.setDirectedMovies(directedMovies);
                crewRepository.save(crewFounded);
            }
        }
        List<Movie> starredMovies = crewFounded.getStarredMovies();
        if (!starredMovies.contains(movie)) {
            starredMovies.add(movie);
            crewFounded.setStarredMovies(starredMovies);
            crewRepository.save(crewFounded);
        }

    }

    private void addCrewFoundedToCrewListInMovie(Movie movie, CrewRole crewRole, List<Crew> directors,
                                                 List<Crew> cast, List<Crew> castToSet, List<Crew> directorsToSet,
                                                 Crew crewFounded) {
        if (crewRole.equals(CrewRole.DIRECTOR)) {
            directorsToSet.add(crewFounded);
            if (directors == null) {
                movie.setDirectors(directorsToSet);
            } else {
                directors.add(crewFounded);
            }
            return;
        }
        castToSet.add(crewFounded);
        if (cast == null) {
            movie.setCast(castToSet);
        } else {
            cast.add(crewFounded);
        }
    }
}
