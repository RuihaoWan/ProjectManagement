package com.rio.proj.controller;

import com.alibaba.fastjson.JSON;
import com.rio.proj.entity.Project;
import com.rio.proj.exception.CommonException;
import com.rio.proj.service.IProjectService;
import com.rio.proj.service.IStageService;
import com.rio.proj.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ProjectController {
    private final IProjectService projectService;

    @Autowired
    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create/project")
    public ProjectResponse createProject(
            @RequestBody
                    ProjectRequest request
    )throws CommonException{
        log.info("createProject -> {}", JSON.toJSONString(request));
        return projectService.createProject(request);
    }

    @PostMapping("/get/project")
    public List<Project> getProjectByIds(
            @RequestBody ProjectGetRequest request
            ) throws CommonException {
        log.info("proj-producer: getProjectByIds -> {}",
                JSON.toJSONString(request));
        return projectService.getProjectByIds(request);
    }
}
