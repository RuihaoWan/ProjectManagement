package com.rio.proj.service;

import com.rio.proj.entity.Project;
import com.rio.proj.exception.CommonException;
import com.rio.proj.vo.ProjectGetRequest;
import com.rio.proj.vo.ProjectRequest;
import com.rio.proj.vo.ProjectResponse;

import java.util.List;

public interface IProjectService {
        ProjectResponse createProject(ProjectRequest request) throws CommonException;

        List<Project> getProjectByIds(ProjectGetRequest request) throws CommonException;
}
