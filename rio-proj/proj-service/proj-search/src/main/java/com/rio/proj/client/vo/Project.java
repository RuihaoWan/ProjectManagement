package com.rio.proj.client.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    private Long id;
    private Long creatorId;
    private String projectName;
    private Integer projectStatus;
    private Date startDate;
    private Date endDate;
    private Date createTime;
    private Date updateTime;
}
