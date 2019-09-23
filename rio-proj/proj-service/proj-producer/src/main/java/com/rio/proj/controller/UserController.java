package com.rio.proj.controller;

import com.alibaba.fastjson.JSON;
import com.rio.proj.exception.CommonException;
import com.rio.proj.service.IUserService;
import com.rio.proj.vo.UserRequest;
import com.rio.proj.vo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public UserResponse createUser(
            @RequestBody
            UserRequest request
    ) throws CommonException
    {
        log.info("createUser -> {]", JSON.toJSONString(request));
        return userService.createUser(request);
    }
}
