package com.rio.proj.service;

import com.rio.proj.exception.CommonException;
import com.rio.proj.vo.TaskRequest;
import com.rio.proj.vo.TaskResponse;

public interface ITaskService {
    TaskResponse createTask(TaskRequest request) throws CommonException;
}
