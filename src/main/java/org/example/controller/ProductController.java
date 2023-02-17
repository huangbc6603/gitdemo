package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Result;
import org.example.rest.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Derek-huang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    /**
     * 模拟同步商品  测试异步执行(无返回值)
     * @return
     */
    @GetMapping("/syncProduct")
    public Result<Void> syncProduct() {
        log.info("syncProduct invoke start");
        productService.syncProduct();
        log.info("syncProduct invoke end");
        return Result.success();
    }

    /**
     * 测试异步执行(有返回值)
     * @return
     */
    @GetMapping("/testAsyncResult")
    public Result<Void> testAsyncResult() {
        log.info("syncProduct invoke start");
        Future<String> stringFuture = productService.testAsyncResult();
        try {
            String result = stringFuture.get();
            log.info(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        log.info("syncProduct invoke end");
        return Result.success();
    }

}

