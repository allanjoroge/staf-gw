package com.legoons.stafgw.gw.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private String accountDeveloper;
    private String comments;
    private Date endDate;
    private String engagementDirector;
    private Long id;
    private String projectName;
    private Date startDate;
    private Integer travelFlag;
    private BusinessUnitDTO businessUnit;
    private TypeDTO projectType;
    private StatusDTO projectStatus;
    private ClientDTO client;
}
