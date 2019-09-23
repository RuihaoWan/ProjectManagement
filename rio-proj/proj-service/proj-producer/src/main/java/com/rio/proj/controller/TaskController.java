package com.rio.proj.controller;

import com.alibaba.fastjson.JSON;
import com.rio.proj.exception.CommonException;
import com.rio.proj.service.ITaskService;
import com.rio.proj.vo.TaskRequest;
import com.rio.proj.vo.TaskResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TaskController {
    private final ITaskService taskService;
    @Autowired
    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping("/create/task")
    public TaskResponse createTask(
            @RequestBody
                TaskRequest taskRequest)
    throws CommonException
    {
        log.info("createTask -> {]", JSON.toJSONString(taskRequest));
        return taskService.createTask(taskRequest);
    }
}
