package org.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Derek-huang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @Test
    public void syncProduct(){
        productController.syncProduct();
    }

    @Test
    public void testAsyncResult(){
        productController.testAsyncResult();
    }

}