package com.rio.proj.index.stage;

import com.rio.proj.index.project.ProjectObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StageObject {

    private Long id;

    private Long projectId;

    private Integer stageStatus;

    public void update(StageObject newObject) {
        if(newObject.getId() != null){
            this.id = newObject.getId();
        }
        if(newObject.getProjectId() != null){
            this.projectId = newObject.getProjectId();
        }
        if(newObject.getStageStatus() != null){
            this.stageStatus = newObject.getStageStatus();
        }
    }

}
