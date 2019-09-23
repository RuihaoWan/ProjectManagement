package com.rio.proj.handler;

import com.rio.proj.dump.table.*;
import com.rio.proj.index.DataTable;
import com.rio.proj.index.IndexAware;
import com.rio.proj.index.project.ProjectIndex;
import com.rio.proj.index.project.ProjectObject;
import com.rio.proj.index.stage.StageIndex;
import com.rio.proj.index.stage.StageObject;
import com.rio.proj.index.task.TaskIndex;
import com.rio.proj.index.task.TaskObject;
import com.rio.proj.index.taskuser.TaskUserIndex;
import com.rio.proj.index.taskuser.TaskUserObject;
import com.rio.proj.index.type.TaskTypeIndex;
import com.rio.proj.index.type.TaskTypeObject;
import com.rio.proj.index.user.UserIndex;
import com.rio.proj.index.user.UserObject;
import com.rio.proj.mysql.constant.OpType;
import com.rio.proj.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class LevelDataHandler {

    public static void handleLevel2(ProjectTable projectTable, OpType type){
        ProjectObject projectObject = new ProjectObject(
                projectTable.getId(),
                projectTable.getCreatorId(),
                projectTable.getProjectStatus(),
                projectTable.getStartDate(),
                projectTable.getEndDate()
        );
        handleBinlogEvent(
                DataTable.of(ProjectIndex.class),
                projectObject.getId(),
                projectObject,
                type
        );
    }

    public static void handleLevel2(UserTable userTable, OpType type){
        UserObject userObject = new UserObject(
                userTable.getId(),
                userTable.getUsername(),
                userTable.getToken(),
                userTable.getPosition()
        );
        handleBinlogEvent(
                DataTable.of(UserIndex.class),
                userObject.getId(),
                userObject,
                type
        );
    }

    public static void handleLevel3(StageTable stageTable, OpType type){

        ProjectObject projectObject = DataTable.of(
                ProjectIndex.class
        ).get(stageTable.getProjectId());
        if(projectObject == null) {
            log.error("handleLevel3 found ProjectObject error: {}",
                    stageTable.getProjectId());
            return;
        }


        StageObject stageObject = new StageObject(
                stageTable.getId(),
                stageTable.getProjectId(),
                stageTable.getStageStatus()
        );
        handleBinlogEvent(
                DataTable.of(StageIndex.class),
                stageObject.getId(),
                stageObject,
                type
        );
    }

    public static void handleLevel3(TaskTable taskTable, OpType type){

        ProjectObject projectObject = DataTable.of(
                ProjectIndex.class
        ).get(taskTable.getProjectId());

        StageObject stageObject = DataTable.of(
                StageIndex.class
        ).get(taskTable.getStageId());

        if(projectObject == null || stageObject == null) {
            log.error("handleLevel3 found ProjectObject or StageObject error: {}",
                    taskTable.getProjectId());
            return;
        }


        TaskObject taskObject = new TaskObject(
                taskTable.getId(),
                taskTable.getStageId(),
                taskTable.getProjectId(),
                taskTable.getStartTime(),
                taskTable.getEndTime()
        );
        handleBinlogEvent(
                DataTable.of(TaskIndex.class),
                taskObject.getId(),
                taskObject,
                type
        );
    }

    public static void handleLevel3(TaskUserTable taskUserTable, OpType type){

        if(type == OpType.UPDATE) {
            log.error("CreativeUnitIndex not support update");
        }

        TaskObject taskObject = DataTable.of(
                TaskIndex.class
        ).get(taskUserTable.getTaskId());

        UserObject userObject = DataTable.of(
                UserIndex.class
        ).get(taskUserTable.getUserId());

        if(taskObject == null || userObject == null) {
            log.error("handleLevel3 found TaskObject or UserObject error: {}",
                    taskUserTable.getTaskId());
            return;
        }

        TaskUserObject taskUserObject = new TaskUserObject(
                taskUserTable.getTaskId(),
                taskUserTable.getUserId()
        );
        Long key = taskUserTable.getTaskId();
        Set<Long> value = new HashSet<>(
                Collections.singleton(taskUserObject.getUserId()));

        handleBinlogEvent(
                DataTable.of(TaskUserIndex.class),
                key,value,
                type
        );
    }

    public static void handleLevel3(TaskTypeTable taskTypeTable, OpType type){
        if(type == OpType.UPDATE) {
            log.error("CreativeUnitIndex not support update");
        }

        TaskObject taskObject = DataTable.of(
                TaskIndex.class
        ).get(taskTypeTable.getTaskId());


        if(taskObject == null) {
            log.error("handleLevel3 found TaskObject error: {}",
                    taskTypeTable.getTaskId());
            return;
        }

        TaskTypeObject taskTypeObject = new TaskTypeObject(
                taskTypeTable.getTaskId(),
                taskTypeTable.getType()
        );
        Long key = taskTypeTable.getTaskId();
        Set<String> value = new HashSet<>(
                Collections.singleton(taskTypeTable.getType()));

        handleBinlogEvent(
                DataTable.of(TaskTypeIndex.class),
                key,value,
                type
        );
    }


    private static <K,V> void handleBinlogEvent(
            IndexAware<K,V> indexAware,
            K key,
            V value,
            OpType type
    ){
        switch (type) {
            case ADD:
                indexAware.add(key,value);
                break;
            case UPDATE:
                indexAware.update(key,value);
            case DELETE:
                indexAware.delete(key,value);
            default:
                break;
        }
    }
}
