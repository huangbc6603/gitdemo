package org.example.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Derek.huang on 2023/4/11 14:50.
 */
@Aspect
@Component
public class NoRepeatSubmitAop {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 切入点
     */
    @Pointcut("@annotation(org.example.utils.NoRepeatSubmit)")
    public void pt() {
    }

    @Around("pt()")
    public Result<Void> arround(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        //这里是唯一标识 根据情况而定
        String key = "1" + "-" + request.getServletPath();
        logger.info("url{}", key);
       /* // 如果缓存中有这个url视为重复提交
        if (!redisService.haskey(key)) {
            //通过，执行下一步
            Object o = joinPoint.proceed();
            //然后存入redis 并且设置15s倒计时
            redisService.setCacheObject(key, 0, 15, TimeUnit.SECONDS);
            //返回结果
            return o;
        } else {
            return Result.fail(400, "请勿重复提交或者操作过于频繁！");
        }*/
        //通过，执行下一步
        Object o = joinPoint.proceed();
        return Result.failureMsg("请勿重复提交或者操作过于频繁！");

    }
}
