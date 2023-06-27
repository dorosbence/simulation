package se.task.objectmovingsimulation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SimulationInitDto {

    @NotNull
    @JsonProperty("header")
    private String header;

    @NotNull
    @JsonProperty("actions")
    private String actions;
}
