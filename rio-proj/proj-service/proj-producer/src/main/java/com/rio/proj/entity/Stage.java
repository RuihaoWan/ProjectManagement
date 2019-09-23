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
@Table(name = "proj_stage")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "project_id",nullable = false)
    private Long projectId;

    @Basic
    @Column(name = "stage_name",nullable = false)
    private String stageName;

    @Basic
    @Column(name = "stage_status",nullable = false)
    private Integer stageStatus;

    @Basic
    @Column(name = "create_time",nullable = false)
    private Date create_time;

    @Basic
    @Column(name = "update_time",nullable = false)
    private Date update_time;

    public Stage(Long projectId,
                 String stageName){
        this.projectId = projectId;
        this.stageName = stageName;
        this.create_time = new Date();
        this.update_time = this.create_time;
    }
}
