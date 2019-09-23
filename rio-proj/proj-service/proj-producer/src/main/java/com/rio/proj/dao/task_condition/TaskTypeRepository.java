package com.rio.proj.dao.task_condition;

import com.rio.proj.entity.task_condition.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTypeRepository extends JpaRepository<TaskType,Long> {
}
