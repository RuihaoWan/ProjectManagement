package com.rio.proj.index.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectObject {
    private Long id;

    private Long creatorId;


    private Integer projectStatus;

    private Date startDate;

    private Date endDate;

    public void update(ProjectObject newObject){
        if(newObject.getId() != null){
            this.id = newObject.getId();
        }
        if(newObject.getCreatorId() != null){
            this.creatorId = newObject.getCreatorId();
        }
        if(newObject.getProjectStatus() != null){
            this.projectStatus = newObject.getProjectStatus();
        }
        if(newObject.getStartDate()!=null){
            this.startDate = newObject.getStartDate();
        }
        if(newObject.getEndDate()!=null){
            this.endDate = newObject.getEndDate();
        }
    }
}
