package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Derek-huang
 */
@SpringBootApplication
@MapperScan(basePackages = {"org.**.dao"})
public class ApplicationDemo extends SpringBootServletInitializer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDemo.class, args);
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.info("login_url{}", "http://localhost:8088/login?username=Derek.huang&password=8888");
        logger.info("Swagger访问URL{}", "http://localhost:8088/swagger-ui.html");
        return String.format("Hello %s!", name);
    }
}
