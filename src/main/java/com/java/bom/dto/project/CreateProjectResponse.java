package com.java.bom.dto.project;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectResponse {
    private String name;
    private String configType;
    private String projectId;

}
