package pl.superCinema.backend.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrewDto {

    private String name;
    private String surname;

    @JsonProperty("crewRole")
    private CrewRoleDto crewRole;
}
