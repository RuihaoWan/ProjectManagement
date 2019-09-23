package com.rio.proj.entity.task_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proj_task_type")
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "task_id",nullable = false)
    private Long taskId;

    @Basic
    @Column(name = "type",nullable = false)
    private String type;

    public TaskType(Long taskId,String type){
        this.taskId = taskId;
        this.type = type;
    }
}
