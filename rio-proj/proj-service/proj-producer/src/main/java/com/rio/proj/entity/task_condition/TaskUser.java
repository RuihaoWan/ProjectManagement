package com.rio.proj.entity.task_condition;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_user")
public class TaskUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Basic
    @Column(name = "task_id",nullable = false)
    private Long taskId;

    @Basic
    @Column(name = "user_id",nullable = false)
    private Long userId;

    public TaskUser(Long taskId, Long userId){
        this.taskId = taskId;
        this.userId = userId;
    }
}
