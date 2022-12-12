package org.example.controller;

import io.swagger.annotations.Api;
import org.example.dto.Result;
import org.example.entity.SysUser;
import org.example.rest.SelectService;
import org.example.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Derek-huang
 */
@Api("SpringBootcontroller")
@RestController
@RequestMapping
public class controller {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SelectService selectService;

    @GetMapping("/select")
    public Result<List<SysUser>> demo(){
        List<SysUser> sysUsers = selectService.selectAllUser();
        LOGGER.info("select sysUser{}", JsonUtils.toJson(sysUsers));
        return Result.success(sysUsers);
    }
}
