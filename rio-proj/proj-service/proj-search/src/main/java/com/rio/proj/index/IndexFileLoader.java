package com.rio.proj.index;

import com.alibaba.fastjson.JSON;
import com.rio.proj.dump.DConstant;
import com.rio.proj.dump.table.*;
import com.rio.proj.handler.LevelDataHandler;
import com.rio.proj.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
@DependsOn("dataTable")
public class IndexFileLoader {
    @PostConstruct
    public void init() {
        List<String> projectStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.Project));
        projectStrings.forEach(p-> LevelDataHandler.handleLevel2(
                JSON.parseObject(p, ProjectTable.class),
                OpType.ADD
        ));
//
//        List<String> userStrings = loadDumpData(
//                String.format("%s%s",
//                        DConstant.DATA_ROOT_DIR,
//                        DConstant.User));
//        userStrings.forEach(p-> LevelDataHandler.handleLevel2(
//                JSON.parseObject(p, UserTable.class),
//                OpType.ADD
//        ));
//
//        List<String> stageStrings = loadDumpData(
//                String.format("%s%s",
//                        DConstant.DATA_ROOT_DIR,
//                        DConstant.Stage));
//        stageStrings.forEach(p-> LevelDataHandler.handleLevel3(
//                JSON.parseObject(p, StageTable.class),
//                OpType.ADD
//        ));
//
//        List<String> taskStrings = loadDumpData(
//                String.format("%s%s",
//                        DConstant.DATA_ROOT_DIR,
//                        DConstant.Task));
//        taskStrings.forEach(p-> LevelDataHandler.handleLevel3(
//                JSON.parseObject(p, TaskTable.class),
//                OpType.ADD
//        ));
//
//        List<String> taskUserStrings = loadDumpData(
//                String.format("%s%s",
//                        DConstant.DATA_ROOT_DIR,
//                        DConstant.TaskUser));
//        taskUserStrings.forEach(p-> LevelDataHandler.handleLevel3(
//                JSON.parseObject(p, TaskUserTable.class),
//                OpType.ADD
//        ));
//
//        List<String> taskTypeStrings = loadDumpData(
//                String.format("%s%s",
//                        DConstant.DATA_ROOT_DIR,
//                        DConstant.TaskType));
//        taskTypeStrings.forEach(p-> LevelDataHandler.handleLevel3(
//                JSON.parseObject(p, TaskTypeTable.class),
//                OpType.ADD
//        ));
    }


    private List<String> loadDumpData(String fileName) {
        try (BufferedReader br = Files.newBufferedReader(
                Paths.get(fileName)
        )) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
