package com.rio.proj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proj_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "stage_id",nullable = false)
    private Long stageId;

    @Basic
    @Column(name = "project_id",nullable = false)
    private Long projectId;

    @Basic
    @Column(name = "name",nullable = false)
    private String name;

    @Basic
    @Column(name = "desc",nullable = false)
    private String desc;

    @Basic
    @Column(name = "start_time",nullable = false)
    private Date startTime;

    @Basic
    @Column(name = "end_time",nullable = false)
    private Date endTime;

    @Basic
    @Column(name = "url",nullable = false)
    private String url;

    @Basic
    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Basic
    @Column(name = "update_time",nullable = false)
    private Date updateTime;

    public Task(String name,
                String desc,
                Date startTime,
                Date endTime,
                String url,
                Long projectId,
                Long stageId){
        this.name = name;
        this.desc = desc;
        this.startTime = startTime;
        this.endTime = endTime;
        this.url = url;
        this.createTime = new Date();
        this.updateTime = this.createTime;
        this.projectId = projectId;
        this.stageId = stageId;
    }
}
