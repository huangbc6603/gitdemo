package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Derek-huang
 */
@Component
@SpringBootApplication
@RestController
@MapperScan(basePackages = {"org.**.dao"})
public class ApplicationDemo extends SpringBootServletInitializer {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDemo.class, args);
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        LOGGER.info("login_url{}", "http://localhost:8080/login?username=admin");
        LOGGER.info("Swagger访问URL{}", "http://localhost:8080/swagger-ui.html");
        return String.format("Hello %s!", name);
    }
}
