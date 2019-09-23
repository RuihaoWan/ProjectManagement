package com.rio.proj.service.Impl;

import com.rio.proj.constant.Constants;
import com.rio.proj.dao.ProjectRepository;
import com.rio.proj.dao.StageRepository;
import com.rio.proj.dao.TaskRepository;
import com.rio.proj.dao.UserRepository;
import com.rio.proj.dao.task_condition.TaskTypeRepository;
import com.rio.proj.dao.task_condition.TaskUserRepository;
import com.rio.proj.entity.Project;
import com.rio.proj.entity.Stage;
import com.rio.proj.entity.Task;
import com.rio.proj.entity.User;
import com.rio.proj.entity.task_condition.TaskType;
import com.rio.proj.entity.task_condition.TaskUser;
import com.rio.proj.exception.CommonException;
import com.rio.proj.service.ITaskService;
import com.rio.proj.util.CommonUtils;
import com.rio.proj.vo.TaskRequest;
import com.rio.proj.vo.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;
    private final StageRepository stageRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final TaskUserRepository taskUserRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, StageRepository stageRepository, ProjectRepository projectRepository, UserRepository userRepository, TaskTypeRepository taskTypeRepository, TaskUserRepository taskUserRepository) {
        this.taskRepository = taskRepository;
        this.stageRepository = stageRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskTypeRepository = taskTypeRepository;
        this.taskUserRepository = taskUserRepository;
    }

    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest request) throws CommonException {
        if(!request.createValidate()){
            throw new CommonException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        Optional<Project> project = projectRepository.findById(request.getProjectId());
        Optional<Stage> stage = stageRepository.findById(request.getStageId());
        if(!project.isPresent() || !stage.isPresent()){
            throw new CommonException(Constants.ErrorMsg.CAN_NOT_FIND_ERROR);
        }
        if(request.getIds()!=null){
            for(int i = 0;i<request.getIds().size();i++){
                Long id = request.getIds().get(i);
                Optional<User> user = userRepository.findById(id);
                if(!user.isPresent()){
                    throw new CommonException(Constants.ErrorMsg.CAN_NOT_FIND_ERROR);
                }
            }
        }
        Task task = taskRepository.save(
                new Task(request.getName(),request.getDesc(),
                        CommonUtils.parseStringDate(request.getStartTime()),
                        CommonUtils.parseStringDate(request.getEndTime()),
                        request.getUrl(),
                        request.getProjectId(),
                        request.getStageId())
        );
        if(request.getTypes()!=null){
            for(int i = 0;i<request.getTypes().size();i++){
                String type = request.getTypes().get(i);
                taskTypeRepository.save(
                        new TaskType(
                                task.getId(),
                                type
                        )
                );
            }
        }
        if(request.getIds()!=null){
            for (int i = 0;i<request.getIds().size();i++){
                Long id = request.getIds().get(i);
                taskUserRepository.save(
                        new TaskUser(
                                task.getId(),
                                id
                        )
                );
            }
        }
        return new TaskResponse(
                task.getId(),
                task.getName()
        );
    }
}
