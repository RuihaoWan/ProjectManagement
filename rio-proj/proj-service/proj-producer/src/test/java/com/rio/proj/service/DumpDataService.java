package com.rio.proj.service;

import com.alibaba.fastjson.JSON;
import com.rio.proj.Application;
import com.rio.proj.dao.ProjectRepository;
import com.rio.proj.dao.StageRepository;
import com.rio.proj.dao.TaskRepository;
import com.rio.proj.dao.UserRepository;
import com.rio.proj.dao.task_condition.TaskTypeRepository;
import com.rio.proj.dao.task_condition.TaskUserRepository;
import com.rio.proj.dump.DConstant;
import com.rio.proj.dump.table.ProjectTable;
import com.rio.proj.entity.Project;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
    webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskUserRepository taskUserRepository;
    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Test
    public void dumpProjTableData() {
        dumpProjTable(
                String.format("%s%s",DConstant.DATA_ROOT_DIR,
                        DConstant.Project)
        );
    }

    private void dumpProjTable(String filename) {
        List<Project> projects = projectRepository.findAll();
        if(CollectionUtils.isEmpty(projects)) {
            return;
        }
        List<ProjectTable> projectTables = new ArrayList<>();
        Path path = Paths.get(filename);
        projects.forEach(
                p->projectTables.add(
                        new ProjectTable(
                            p.getId(),
                            p.getCreatorId(),
                            p.getProjectStatus(),
                            p.getStartDate(),
                            p.getEndDate()
                ))
        );
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            System.out.println(projectTables.size());
            for (ProjectTable projectTable : projectTables) {
                writer.write(JSON.toJSONString(projectTable));
                writer.newLine();
            }
        } catch (IOException ex) {
            log.error("dumpProject error");
        }
    }

}
