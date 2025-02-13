package com.java.bom.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProjectRequest {

    @NotBlank(message = "Project name cannot be empty")
    private String name;

    @NotNull(message = "Config type is required")
    private String configType;
}
