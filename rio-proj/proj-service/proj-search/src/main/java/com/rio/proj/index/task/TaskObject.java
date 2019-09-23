package com.rio.proj.index.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskObject {
    private Long id;

    private Long stageId;

    private Long projectId;

    private Date startTime;

    private Date endTime;

    void update(TaskObject newObject) {

        if(newObject.getId() != null){
            this.id = newObject.getId();
        }
        if(newObject.getStageId() != null){
            this.stageId = newObject.getStageId();
        }
        if(newObject.getProjectId() != null){
            this.projectId = newObject.getProjectId();
        }
        if(newObject.getStartTime() !=null){
            this.startTime = newObject.getStartTime();
        }
        if(newObject.getEndTime() !=null){
            this.endTime = newObject.getEndTime();
        }
    }

}
