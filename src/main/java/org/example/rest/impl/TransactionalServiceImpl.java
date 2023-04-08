package org.example.rest.impl;

import org.example.rest.TransactionalService;

/**
 * @Author: Derek.huang on 2023/4/8 22:02.
 */
public class TransactionalServiceImpl implements TransactionalService {


    @Override
    public void before() {
        System.out.println("before 增强了");
    }

    @Override
    public void after() {
        System.out.println("after 增强了");
    }
}
