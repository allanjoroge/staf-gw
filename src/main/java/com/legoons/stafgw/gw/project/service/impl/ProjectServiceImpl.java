package com.legoons.stafgw.gw.project.service.impl;

import com.legoons.stafgw.gw.project.dto.ProjectDTO;
import com.legoons.stafgw.gw.project.service.ProjectService;
import com.legoons.stafgw.search.GatewaySearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    @Value("${project.ms.url}")
    public String projectUrl;

    private RestTemplate restTemplate;

    @Autowired
    public ProjectServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProjectDTO findProjectById(Long id) {

        return restTemplate.getForObject(projectUrl + id,
                ProjectDTO.class);
    }

    //make call to localhost:8081/projects with restTemplate
    @Override
    public Page<ProjectDTO> search(GatewaySearchRequest searchRequest) {

        String uri = projectUrl;

        if (searchRequest != null) {
            uri = uri + "?businessUnitId=" + searchRequest;
        }

        Sort sortOrder = createSortOrder(searchRequest);
        PageRequest paging = PageRequest.of(searchRequest.getPageNo(), searchRequest.getPageSize(), sortOrder);

        Page<ProjectDTO> entity = restTemplate.getForEntity(uri,
                ProjectDTO[].class);

        return entity.getContent() != null ? entity.getContent() : Collections.emptyList();
    }

    protected Sort createSortOrder(GatewaySearchRequest searchRequest) {

        List<Sort.Order> orderList = new ArrayList<>();

        if ((searchRequest == null) || (searchRequest.getSortColumns() == null)) {
            //return the default sort
            orderList.add(createSortOrder(null, null));
        } else {

            for (int i = 0; i < searchRequest.getSortColumns().length; i++) {
                String col = searchRequest.getSortColumns()[i];
                String dir = searchRequest.getSortDirections()[i];
                orderList.add(createSortOrder(col, dir));
            }
        }

        log.debug("sort by list is {}", orderList.toString());

        return Sort.by(orderList);
    }

    private Sort.Order createSortOrder(String column, String direction) {

        log.debug("BEFORE column is {} and direction is {}", column, direction);

        if (StringUtils.isEmpty(column)) {
            column = "projectName";
        }
        if (StringUtils.isEmpty(direction)) {
            direction = "ASC";
        }

        log.debug("AFTER column is {} and direction is {}", column, direction);

        if ("DESC".equalsIgnoreCase(direction)) {
            return Sort.Order.desc(column);
        }

        return Sort.Order.asc(column);
    }
}
