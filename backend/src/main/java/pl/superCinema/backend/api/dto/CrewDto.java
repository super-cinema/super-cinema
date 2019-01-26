package pl.superCinema.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class CrewDto {
    private Long id;
    private String name;
    private String surname;

    private List<CrewRoleDto> crewRoles;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewDto crewDto = (CrewDto) o;
        return Objects.equals(id, crewDto.id) &&
                Objects.equals(name, crewDto.name) &&
                Objects.equals(surname, crewDto.surname) &&
                Objects.equals(crewRoles, crewDto.crewRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, crewRoles);
    }
}
