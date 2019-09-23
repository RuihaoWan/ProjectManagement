package com.rio.proj.dao.task_condition;

import com.rio.proj.entity.task_condition.TaskUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskUserRepository extends JpaRepository<TaskUser,Long> {
}
