package com.rio.proj.dao;

import com.rio.proj.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByCreatorIdAndProjectName(Long creatorId, String projectName);

    List<Project> findAllByIdAndCreatorId(List<Long> ids,Long creatorId);
}
