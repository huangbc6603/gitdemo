package org.example.controller;

import io.swagger.annotations.Api;
import org.example.dao.SysUserBaseMapper;
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
public class SysUserController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SelectService selectService;

    @Autowired
    private SysUserBaseMapper sysUserBaseMapper;

    @GetMapping("/select")
    public Result<List<SysUser>> demo(){
        List<SysUser> sysUsers = selectService.selectAllUser();
        LOGGER.info("SysUserController sysUser{}", JsonUtils.toJson(sysUsers));
        return Result.success(sysUsers);
    }

    @GetMapping("/selectUser")
    public Result<List<SysUser>> selectUser(){
        List<SysUser> sysUsers = sysUserBaseMapper.selectUserByName("test");
        LOGGER.info("SysUserController selectUser{}", JsonUtils.toJson(sysUsers));
        return Result.success(sysUsers);
    }
}
