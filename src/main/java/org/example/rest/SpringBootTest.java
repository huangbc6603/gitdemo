package org.example.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.dto.Result;
import org.example.test.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Derek-huang
 */
@Api("SpringBootTest")
@RestController
@RequestMapping
public class SpringBootTest {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final String template = "template/AAA.xlsx";

    @ApiOperation("测试")
    @GetMapping("/test")
    public Result<User> test() {
        User user = new User("Derek", "18");
        return Result.success(user);
    }

    @ApiOperation("模板下载")
    @GetMapping("/template")
    public void downloadTemplate(HttpServletResponse response) {
        Resource res = new ClassPathResource(template);
        InputStream in = null;
        try {
            in = res.getInputStream();
        } catch (Exception ignore) {
        }
        writeDataToResponse(response, "SpePurchaseApplication.xlsx", in);
    }

    public void writeDataToResponse(HttpServletResponse response, String fileName, InputStream in) {
        if (in == null) {
            throw new IllegalStateException("下载数据为空");
        }
        try {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
            ServletOutputStream out = response.getOutputStream();
            int readLen = 0;
            byte[] buf = new byte[1024 * 10];
            while ((readLen = in.read(buf)) != -1) {
                out.write(buf, 0, readLen);
            }
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @ApiOperation("SpringBootDemo")
    @GetMapping("/demo")
    public Result<User> demo() {
        User user = new User("Derek", "18");
        return Result.success(user);
    }

}
