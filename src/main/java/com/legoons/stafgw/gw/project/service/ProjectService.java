package com.legoons.stafgw.gw.project.service;

import com.legoons.stafgw.gw.project.dto.ProjectDTO;
import com.legoons.stafgw.search.GatewaySearchRequest;
import org.springframework.data.domain.Page;


public interface ProjectService {

    Page<ProjectDTO> search(GatewaySearchRequest searchRequest);

    ProjectDTO findProjectById(Long id);
}
