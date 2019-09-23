package com.rio.proj.service.Impl;

import com.rio.proj.constant.Constants;
import com.rio.proj.dao.ProjectRepository;
import com.rio.proj.dao.UserRepository;
import com.rio.proj.entity.Project;
import com.rio.proj.entity.User;
import com.rio.proj.exception.CommonException;
import com.rio.proj.service.IProjectService;
import com.rio.proj.util.CommonUtils;
import com.rio.proj.vo.ProjectGetRequest;
import com.rio.proj.vo.ProjectRequest;
import com.rio.proj.vo.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements IProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectRequest request) throws CommonException {
        if(!request.createValidate()){
            throw new CommonException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        Optional<User> user = userRepository.findById(request.getCreatorId());
        if(!user.isPresent()){
            throw new CommonException(Constants.ErrorMsg.CAN_NOT_FIND_ERROR);
        }
        Project oldProj = projectRepository.findByCreatorIdAndProjectName(
                request.getCreatorId(),request.getProjectName());
        if(oldProj != null){
            throw new CommonException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        Project project = projectRepository.save(
                new Project(request.getCreatorId(),request.getProjectName(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getEndDate()))
        );
        return new ProjectResponse(project.getId(),
                project.getProjectName());
    }

    @Override
    public List<Project> getProjectByIds(ProjectGetRequest request) throws CommonException {
        if(!request.validate()){
            throw new CommonException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        return projectRepository.findAllByIdAndCreatorId(request.getIds(),request.getCreatorId());
    }
}
