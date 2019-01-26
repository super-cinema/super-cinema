package pl.superCinema.backend.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    public String toString() {
        return "CrewDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewDto crewDto = (CrewDto) o;
        return Objects.equals(name, crewDto.name) &&
                Objects.equals(surname, crewDto.surname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, surname);
    }
}
