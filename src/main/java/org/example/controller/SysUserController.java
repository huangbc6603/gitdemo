package org.example.controller;

import io.swagger.annotations.Api;
import org.apache.commons.collections4.CollectionUtils;
import org.example.dto.Result;
import org.example.dto.UserDTO;
import org.example.entity.SysUser;
import org.example.rest.SelectService;
import org.example.utils.JsonUtils;
import org.example.utils.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Derek-huang
 */
@Api("SpringController")
@RestController
@RequestMapping("user")
public class SysUserController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SelectService selectService;


    @GetMapping("/select")
    public Result<List<SysUser>> demo(){
        List<SysUser> sysUsers = selectService.selectAllUser();
        LOGGER.info("SysUserController sysUser{}", JsonUtils.toJson(sysUsers));
        return Result.success(sysUsers);
    }

    @PostMapping("/login")
    public Result<List<SysUser>> selectUser(@RequestBody UserDTO user) {
        LOGGER.info("获取当前登录人：{}", ShiroUtil.whoAmI());
        List<SysUser> sysUsers = selectService.selectUserByName(user.getName());
        if (CollectionUtils.isNotEmpty(sysUsers)){
            if (!ShiroUtil.isMe(sysUsers.get(0).getUserName())){
                return Result.failureMsg("请登录后尝试访问,谢谢");
            }
        }
        LOGGER.info("SysUserController selectUser{}", JsonUtils.toJson(sysUsers));
        return Result.success(sysUsers);
    }
}
