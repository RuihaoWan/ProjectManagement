package com.rio.proj.service.Impl;

import com.rio.proj.constant.Constants;
import com.rio.proj.dao.ProjectRepository;
import com.rio.proj.dao.StageRepository;
import com.rio.proj.entity.Project;
import com.rio.proj.entity.Stage;
import com.rio.proj.exception.CommonException;
import com.rio.proj.service.IStageService;
import com.rio.proj.vo.StageRequest;
import com.rio.proj.vo.StageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StageServiceImpl implements IStageService {


    private final ProjectRepository projectRepository;
    private final StageRepository stageRepository;

    @Autowired
    public StageServiceImpl(ProjectRepository projectRepository, StageRepository stageRepository) {
        this.projectRepository = projectRepository;
        this.stageRepository = stageRepository;
    }

    @Override
    @Transactional
    public StageResponse createStage(StageRequest request) throws CommonException {
        if(request.createValidate()){
            throw new CommonException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        Optional<Project> project = projectRepository.findById(request.getProjectId());
        if(!project.isPresent()){
            throw new CommonException(Constants.ErrorMsg.CAN_NOT_FIND_ERROR);
        }
        Stage stage = stageRepository.save(
                new Stage(request.getProjectId(),request.getStageName())
        );
        return new StageResponse(stage.getId(),stage.getStageName());
    }
}
