package com.rio.proj.service;

import com.rio.proj.exception.CommonException;
import com.rio.proj.vo.StageRequest;
import com.rio.proj.vo.StageResponse;

public interface IStageService {

    StageResponse createStage(StageRequest request) throws CommonException;
}
