package org.example.controller;

import org.example.rest.TransactionalService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: Derek.huang on 2023/4/8 21:54.
 */
public class IInvocationHander implements InvocationHandler {

    //增强类
    private TransactionalService transactionalService;

    //目标类
    private Object object;

    public IInvocationHander(TransactionalService transactionalService, Object object) {
        this.transactionalService = transactionalService;
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = null;
        if (method.getName().equals("save")) {
            transactionalService.before();
            invoke = method.invoke(object, args);
            transactionalService.after();
        } else {
            invoke = method.invoke(object, args);
        }
        return invoke;
    }
}
