package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.example.dao.SysUserBaseMapper;
import org.example.dto.Result;
import org.example.dto.UserDTO;
import org.example.entity.SysUser;
import org.example.rest.SelectService;
import org.example.utils.JsonUtils;
import org.example.utils.PageResp;
import org.example.utils.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Derek-huang
 */
@Api("SpringBootcontroller")
@RestController
@RequestMapping("user")
public class SysUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SelectService selectService;

    @Autowired
    private SysUserBaseMapper sysUserBaseMapper;

    /**
     * 分页请求参数{"pageSize": 2, "startIndex": 0}
     * @param user
     * @return
     */
    @PostMapping("/select")
    public Result<PageResp<SysUser>> demo(@RequestBody UserDTO user) {
        final PageResp<SysUser> pageResp = selectService.selectAllUser(user);
        String msg = JsonUtils.toJson(pageResp.getDatas());
        logger.info("SysUserController sysUser{}", msg);
        return Result.success(pageResp);
    }

    /**
     * 登录shiro http://localhost:8080/login?username=admin&password=admin123
     * @param user
     * @return
     */

    @PostMapping("/login")
    public Result<List<SysUser>> selectUser(@RequestBody UserDTO user) {
        String userName = ShiroUtil.whoAmI();
        logger.info("获取当前登录人：{}", userName);
        List<SysUser> sysUsers = sysUserBaseMapper.selectUserByName(user.getName());
        if (CollectionUtils.isNotEmpty(sysUsers) && !ShiroUtil.isMe(sysUsers.get(0).getName())) {
            return Result.failureMsg("非当前登录人,无权限,请联系管理员！");
        }
        String users = JsonUtils.toJson(sysUsers);
        logger.info("SysUserController selectUser{}", users);
        return Result.success(sysUsers);
    }

    /**
     * easypoi文件导入
     * 注意commons-lang3版本过低会报错
     * Caused by: java.lang.NoSuchMethodError: org.apache.commons.lang3.StringUtils.isNoneEmpty
     * ([Ljava/lang/CharSequence;)
     * @param file
     * @return
     */
    @PostMapping("/import")
    public Result<Void> importTemplateField(
            @ApiParam(value = "上传文件", required = true) @RequestParam MultipartFile file) {
        String userName = ShiroUtil.whoAmI();
        logger.info("获取当前登录人：{}", userName);
        return Result.successMsg(selectService.importTemplateField(file, userName));
    }

    /**
     * 测试事务
     */
    @GetMapping("/testTransaction")
    public Result<Void> testTransaction() {
        try {
            selectService.testTransaction();
        } catch (Exception e) {
            return Result.failureMsg(e.getMessage());
        }
        return Result.success();
    }
}
