package com.rio.proj.service.Impl;

import com.rio.proj.constant.Constants;
import com.rio.proj.dao.UserRepository;
import com.rio.proj.entity.User;
import com.rio.proj.exception.CommonException;
import com.rio.proj.service.IUserService;
import com.rio.proj.util.CommonUtils;
import com.rio.proj.vo.UserRequest;
import com.rio.proj.vo.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) throws CommonException {
        if(!request.createValidate()){
            throw new CommonException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        User oldUser = userRepository.findByUsername(request.getUsername());
        if(oldUser != null){
            throw new CommonException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        User user = userRepository.save(
                new User(request.getUsername(),
                        CommonUtils.md5(request.getUsername())
                        ,request.getPosition())
        );
        return new UserResponse(
                user.getId(),
                user.getUsername()
        );
    }
}
