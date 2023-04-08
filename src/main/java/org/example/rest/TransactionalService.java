package org.example.rest;

/**
 * @Author: Derek.huang on 2023/4/8 22:01.
 */
public interface TransactionalService {

    void before();

    void after();

}
