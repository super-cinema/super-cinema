package pl.superCinema.backend.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
}
