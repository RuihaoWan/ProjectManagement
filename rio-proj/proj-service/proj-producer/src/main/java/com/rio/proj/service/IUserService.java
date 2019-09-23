package com.rio.proj.service;

import com.rio.proj.exception.CommonException;
import com.rio.proj.vo.UserRequest;
import com.rio.proj.vo.UserResponse;

public interface IUserService {
    UserResponse createUser(UserRequest request) throws CommonException;
}
