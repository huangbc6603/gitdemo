package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Objects;

/**
 * 全局异常类
 */
@Slf4j
@ControllerAdvice
public class MethodArgumentValid {

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<String> handleValidException(MethodArgumentNotValidException e) {

        log.error("全局异常{}", Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return Result.failureMsg(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());

    }
}
