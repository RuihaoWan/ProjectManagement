package com.rio.proj.advice;

import com.rio.proj.exception.CommonException;
import com.rio.proj.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(value = CommonException.class)
    public CommonResponse<String> handlerCommonException(HttpServletRequest req,
                                                         CommonException ex){
        CommonResponse<String> response = new CommonResponse<>(-1,"error");
        response.setData(ex.getMessage());
        return response;
    }
}
