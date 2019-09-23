package com.rio.proj.entity;

import com.rio.proj.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proj_project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "creator_id" ,nullable = false)
    private Long creatorId;

    @Basic
    @Column(name = "project_name",nullable = false)
    private String projectName;

    @Basic
    @Column(name = "project_status",nullable = false)
    private Integer projectStatus;

    @Basic
    @Column(name = "start_date",nullable = false)
    private Date startDate;

    @Basic
    @Column(name = "end_date",nullable = false)
    private Date endDate;

    @Basic
    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Basic
    @Column(name = "update_time",nullable = false)
    private Date updateTime;

    public Project(Long creatorId,String projectName,Date startDate,Date endDate){
        this.creatorId = creatorId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
