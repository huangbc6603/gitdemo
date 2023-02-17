package org.example.rest.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.BizServiceException;
import org.example.rest.ProductService;
import org.example.test.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author Derek-huang
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Async(value = "AsyncConfigTaskExecutor")
    @Override
    public void syncProduct() {
        log.info("同步商品开始");
        try {
            Thread.sleep(5000);
            User user = new User("derek","10");
            user.setWeight(1/0);
        } catch (Exception e) {
            throw new BizServiceException("400", "同步商品异常");
        }
        log.info("同步商品结束");
    }

    @Async(value = "AsyncConfigTaskExecutor")
    @Override
    public Future<String> testAsyncResult() {
        log.info("testAsyncResult开始");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testAsyncResult结束");
        return new AsyncResult<>("执行完成咯!");
    }
}

