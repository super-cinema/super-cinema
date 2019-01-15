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
        Crew crew = crewBuilder.dtoToEntity(crewDto);
        Crew savedCrew = crewRepository.save(crew);
        return crewBuilder.entityToDto(savedCrew);
    }

    public CrewDto getCrew(Long id) {
        Optional<Crew> crew = crewRepository.findById(id);
        return crewBuilder.entityToDto(crew.get());
    }

    public CrewDto deleteCrew(Long id) {
        if (crewRepository.existsById(id)) {
            Optional<Crew> crew = crewRepository.findById(id);
            crewRepository.deleteById(id);
            return crewBuilder.entityToDto(crew.get());
        } else return null;
    }

    public List<CrewDto> getAllCrew() {
        List<Crew> allCrew = crewRepository.findAll();
        List<CrewDto> allCrewDtos = new ArrayList<>();
        if (!allCrew.isEmpty()) {
            for (Crew crew : allCrew) {
                allCrewDtos.add(crewBuilder.entityToDto(crew));
            }
        }
        return allCrewDtos;
    }


    public CrewDto updateCrew(Long id, CrewDto crewDto) {
        Crew crew = getMovieEntityById(id);
        crew = editCrew(crew,crewDto);
        crewRepository.save(crew);
        return crewBuilder.entityToDto(crew);
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

    public void setCrewListInMovie(List<CrewDto> crewDto, Movie movie, CrewRole crewRole) {
        List<Long> crewIds = crewDto.stream()
                .map(crew -> crew.getId())
                .collect(Collectors.toList());
        setCrewInMovieById(crewIds, movie, crewRole);
    }

    public void setCrewInMovieById(List<Long> crewIds, Movie movie, CrewRole crewRole) {
        if(crewIds == null){
            return;
        }
        List<Crew> cast = movie.getCast();
        List<Crew> directors = movie.getDirectors();
        crewIds.stream()
                .forEach(crewId -> {
                    crewRepository.findById(crewId).ifPresent(
                            crewFounded -> {
                                if(crewRole.equals(CrewRole.DIRECTOR)){
                                    directors.add(crewFounded);
                                    return;
                                }
                                cast.add(crewFounded);
                            }
                    );
                });
    }
    //TODO consider if useful
    public void deleteMovieFromCrew(Movie movie){
        List<Crew> directors = movie.getDirectors();
        directors.stream()
                .forEach(director -> director.getDirectedMovies().remove(movie));
        List<Crew> actors = movie.getCast();
        actors.stream()
                .forEach(actor -> actor.getStarredMovies().remove(movie));

    }

    public void deleteMovieFromCrewById(List<Long> crewIdsToRemove, Movie movie, CrewRole crewRole) {
        crewIdsToRemove.stream()
                .forEach(crewId -> {
                    crewRepository.findById(crewId).ifPresent(
                            crewFounded -> {
                                if (crewRole.equals("DIRECTOR")) {
                                    crewFounded.getDirectedMovies().remove(movie);
                                    return;
                                }
                                crewFounded.getStarredMovies().remove(movie);
                            }
                    );
                });
    }

    public void deleteCrewFromMovie(List<Long> actorsIdsToRemove, Movie existingMovie, CrewRole crewRole) {
        if(actorsIdsToRemove == null){
            return;
        }
        List<Crew> cast = existingMovie.getCast();
        List<Crew> directors = existingMovie.getDirectors();
        actorsIdsToRemove.stream()
                .forEach(crewId -> {
                    crewRepository.findById(crewId).ifPresent(
                            crewFounded -> {
                                if(crewRole.equals(CrewRole.DIRECTOR)){
                                    directors.remove(crewFounded);
                                    return;
                                }
                                cast.remove(crewFounded);
                            }
                    );
                });
    }
}
