package org.example.controller;

import org.example.rest.IStudentService;
import org.example.rest.TransactionalService;
import org.example.rest.impl.IStudentServiceImpl;
import org.example.rest.impl.TransactionalServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @Author: Derek.huang on 2023/4/8 22:13.
 */
public class IInvocationHanderTest {

    public static void main(String[] args) {

        //增强类对象
        TransactionalService transactionalService = new TransactionalServiceImpl();

        //目标类对象
        IStudentService iStudentService = new IStudentServiceImpl();

        //方法拦截处理器
        IInvocationHander iInvocationHander = new IInvocationHander(transactionalService, iStudentService);

        //获取目标实例对象
        IStudentService proxyInstance = (IStudentService)Proxy.newProxyInstance(IStudentServiceImpl.class.getClassLoader(), IStudentServiceImpl.class.getInterfaces()
                , iInvocationHander);

        proxyInstance.save();

        proxyInstance.query();



    }


}
