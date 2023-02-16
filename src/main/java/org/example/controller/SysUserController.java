package org.example.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.dao.SysUserBaseMapper;
import org.example.dto.FinanceInterestStatementListVo;
import org.example.dto.QueryFinanceInterestStatementDto;
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

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Derek-huang
 */
@Api("SpringBootcontroller")
@RestController
@RequestMapping("user")
public class SysUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String PARAM_ERROR = "参数异常";

    @Autowired
    private SelectService selectService;

    @Autowired
    private SysUserBaseMapper sysUserBaseMapper;

//    @Resource
//    private RedissonClient redissonClient;

    /**
     * 分页请求参数{"pageSize": 2, "startIndex": 0}
     *
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
     *
     * @param user
     * @return
     */

    @PostMapping("/login")
    public Result<List<SysUser>> selectUser(@RequestBody UserDTO user) {
        if (user == null) {
            return Result.failureMsg(PARAM_ERROR);
        }
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
     *
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

    /**
     * 导出
     */
    @ApiOperation(value = "导出(财务中心-报表统计-利息情况)列表", httpMethod = "GET", notes = "dev - Ming")
    @GetMapping("/exportExcel")
    public void export(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "10") Integer size,
                       QueryFinanceInterestStatementDto dto, HttpServletResponse response) {
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("利息情况数据表", "UTF-8") + ".xls");
            //编码
            response.setCharacterEncoding("UTF-8");
            //ExcelExportUtil.exportExcel()方法的第二个参数为对应实体class对象，第三个参数为对应实体的list集合
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), FinanceInterestStatementListVo.class, selectService.getInterestList(current, size, dto));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * redisson锁测试
//     */
//    @ApiOperation(value = "redisson锁测试", httpMethod = "GET", notes = "dev - Ming")
//    @GetMapping("/testRedisLock")
//    public Result<String> testRedisLock() {
//        String lockName = "TEST-REDIS-LOCK";
//        RLock rLock = redissonClient.getLock(lockName);
//        try {
//            boolean isLocked = rLock.tryLock(0, 1, TimeUnit.MINUTES);
//            if (isLocked) {
//                // TODO 业务逻辑
//                logger.info("拿到锁，哈哈哈哈哈哈{}", lockName);
//                return Result.failureMsg("拿到锁，哈哈哈哈哈哈");
//            } else {
//                logger.info("没有拿到锁.....{}", lockName);
//                return Result.failureMsg("没有拿到锁...");
//            }
//        } catch (Exception e) {
//            logger.info("异常", e);
//        } finally {
//            try {
//                rLock.unlock();
//            } catch (Exception e) {
//                logger.info("锁释放异常", e);
//            }
//        }
//        return Result.success("测试成功");
//    }
//
//    /**
//     * redisson锁测试
//     */
//    @ApiOperation(value = "redisson锁测试", httpMethod = "GET", notes = "dev - Ming")
//    @GetMapping("/testRedisLock1")
//    public Result<String> testRedisLock1() {
//        String lockName = "TEST-REDIS-LOCK";
//        RLock rLock = redissonClient.getLock(lockName);
//        try {
//            boolean isLocked = rLock.tryLock(0, 1, TimeUnit.MINUTES);
//            if (isLocked) {
//                // TODO 业务逻辑
//                logger.info("拿到锁，哈哈哈哈哈哈{}", lockName);
//                return Result.failureMsg("拿到锁，哈哈哈哈哈哈");
//            } else {
//                logger.info("没有拿到锁.....{}", lockName);
//                return Result.failureMsg("没有拿到锁...");
//            }
//        } catch (Exception e) {
//            logger.info("异常", e);
//        } finally {
//            try {
//                rLock.unlock();
//            } catch (Exception e) {
//                logger.info("锁释放异常", e);
//            }
//        }
//        return Result.success("测试成功");
//    }

}
