package pl.superCinema.backend.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
