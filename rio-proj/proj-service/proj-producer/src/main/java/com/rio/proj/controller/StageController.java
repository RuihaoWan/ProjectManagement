package com.rio.proj.controller;

import com.alibaba.fastjson.JSON;
import com.rio.proj.exception.CommonException;
import com.rio.proj.service.IStageService;
import com.rio.proj.vo.StageRequest;
import com.rio.proj.vo.StageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StageController {
    private final IStageService stageService;

    @Autowired
    public StageController(IStageService stageService) {
        this.stageService = stageService;
    }

    @PostMapping("/create/stage")
    public StageResponse createStage(
            @RequestBody
            StageRequest request
    )throws CommonException{
        log.info("createStage -> {}", JSON.toJSONString(request));
        return stageService.createStage(request);
    }
}
