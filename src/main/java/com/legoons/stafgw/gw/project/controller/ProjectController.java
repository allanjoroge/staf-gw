package com.legoons.stafgw.gw.project.controller;

import com.legoons.stafgw.gw.project.dto.ProjectDTO;
import com.legoons.stafgw.gw.project.service.ProjectService;
import com.legoons.stafgw.search.GatewaySearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ProjectController {


    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectGetService) {
        this.projectService = projectGetService;
    }

    @GetMapping("/projects")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProjectDTO> search(@RequestParam @Nullable GatewaySearchRequest searchRequest) {

        return projectService.search(searchRequest);

    }

    @GetMapping("/projects/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO findProjectById(@PathVariable Long id) {

        return projectService.findProjectById(id);
    }
}
