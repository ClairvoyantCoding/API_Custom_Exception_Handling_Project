package com.customexceptionhandling.models;

import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
public class ProjectDto {
    private UUID id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String deployedLink;
    private String documentationLink;
    private String codeLink;
}
