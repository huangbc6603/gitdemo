package org.example.rest;

import java.util.concurrent.Future;

/**
 * @author Derek-huang
 */
public interface ProductService {
    void syncProduct();
    Future<String> testAsyncResult();
}

