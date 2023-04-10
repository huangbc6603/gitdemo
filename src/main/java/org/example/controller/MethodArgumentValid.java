package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * 全局异常类
 * @Author: Derek.huang on 2023/4/10 13:25.
 */
@Slf4j
@ControllerAdvice
public class MethodArgumentValid {

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Result<String> handleValidException(Exception e) {

        log.error("全局异常{}", Objects.requireNonNull(e.getMessage()));
        return Result.failureMsg(Objects.requireNonNull(e.getMessage()));

    }
}
