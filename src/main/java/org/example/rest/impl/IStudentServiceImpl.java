package org.example.rest.impl;

import org.example.rest.IStudentService;

/**
 * @Author: Derek.huang on 2023/4/8 21:52.
 */
public class IStudentServiceImpl implements IStudentService {
    @Override
    public void save() {
        System.out.println("我是目标类,save方法");
    }

    @Override
    public void query() {
        System.out.println("我是目标类，query方法");
    }
}
