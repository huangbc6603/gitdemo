package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.util.Assert;
import org.example.dto.Result;
import org.example.dto.UserDTO;
import org.example.entity.SysUser;
import org.example.rest.SelectService;
import org.example.test.User;
import org.example.utils.JsonUtils;
import org.example.utils.NoRepeatSubmit;
import org.example.utils.ShiroUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Derek-huang
 */
@Api("SpringController")
@RestController
@RequestMapping("user")
public class SysUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SelectService selectService;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @NoRepeatSubmit
    @GetMapping("/select")
    public Result<List<SysUser>> demo() {
        List<SysUser> sysUsers = null;
        Assert.notNull(sysUsers, "用户不存在！！！");
        logger.info("SysUserController sysUser{}", JsonUtils.toJson(sysUsers));
        return Result.success(sysUsers);
    }

    @PostMapping("/login")
    public Result<List<SysUser>> selectUser(@RequestBody UserDTO user) {
        logger.info("获取当前登录人：{}", ShiroUtil.whoAmI());
        List<SysUser> sysUsers = selectService.selectUserByName(user.getName());
        if (CollectionUtils.isNotEmpty(sysUsers)) {
            if (!ShiroUtil.isMe(sysUsers.get(0).getUserName())) {
                return Result.failureMsg("请登录后尝试访问,谢谢");
            }
        }
        logger.info("SysUserController selectUser{}", JsonUtils.toJson(sysUsers));
        return Result.success(sysUsers);
    }

    /**
     * redisson锁测试
     */
    @ApiOperation(value = "redisson锁测试", httpMethod = "GET", notes = "dev - Ming")
    @GetMapping("/testRedisLock")
    public Result<String> testRedisLock() {
        String lockName = "TEST-REDIS-LOCK";
        RLock rLock = redissonClient.getLock(lockName);
        try {
            boolean isLocked = rLock.tryLock(0, 1, TimeUnit.MINUTES);
            if (isLocked) {
                // TODO 业务逻辑
                logger.info("拿到锁，哈哈哈哈哈哈{}", lockName);
                SysUser sysUser = new SysUser();
                sysUser.setUserId("12");
                sysUser.setUserName("derek.huang");
                sysUser.setUserAge((byte) 1);

                stringRedisTemplate.opsForValue().set("STRING_REDIS_TEST", "sysUser");
                redisTemplate.opsForValue().set("REDIS_TEST", sysUser);

                return Result.failureMsg("拿到锁，哈哈哈哈哈哈");
            } else {
                logger.info("没有拿到锁.....{}", lockName);
                return Result.failureMsg("没有拿到锁...");
            }
        } catch (Exception e) {
            logger.info("异常", e);
        } finally {
            try {
                rLock.unlock();
            } catch (Exception e) {
                logger.info("锁释放异常", e);
            }
        }
        return Result.success("测试成功");
    }

    /**
     * redisson锁测试
     */
    @ApiOperation(value = "redisson锁测试", httpMethod = "GET", notes = "dev - Ming")
    @GetMapping("/testRedisLock1")
    public Result<String> testRedisLock1() {

        String stringRedisTest = stringRedisTemplate.opsForValue().get("STRING_REDIS_TEST");
        User redisTest = (User) redisTemplate.opsForValue().get("REDIS_TEST");
        System.out.println(stringRedisTest + redisTest);
        redisTemplate.opsForValue().getAndSet("REDIS_TEST", "REDIS_TEST_getAndSet");
        User redis_test = (User) redisTemplate.opsForValue().get("REDIS_TEST");
        System.out.println(redis_test);

        String lockName = "TEST-REDIS-LOCK";
        RLock rLock = redissonClient.getLock(lockName);
        try {
            boolean isLocked = rLock.tryLock(0, 1, TimeUnit.MINUTES);
            if (isLocked) {
                // TODO 业务逻辑
                logger.info("拿到锁，哈哈哈哈哈哈{}", lockName);
                return Result.failureMsg("拿到锁，哈哈哈哈哈哈");
            } else {
                logger.info("没有拿到锁.....{}", lockName);
                return Result.failureMsg("没有拿到锁...");
            }
        } catch (Exception e) {
            logger.info("异常", e);
        } finally {
            try {
                rLock.unlock();
            } catch (Exception e) {
                logger.info("锁释放异常", e);
            }
        }
        return Result.success("测试成功");
    }

    @ApiOperation(value = "redis测试")
    @GetMapping("/redisTest")
    public void redisTest() {
        redisTemplate.opsForValue().set("redis_test", "redis_test");
        try {
            redisTemplate.opsForValue().set("redis_test", "redis_test1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SysUser sysUser = new SysUser();
        sysUser.setUserId("12");
        sysUser.setUserName("derek.huang");
        sysUser.setUserAge((byte) 1);
        redisTemplate.opsForValue().set("stringValue", sysUser);
        SysUser stringValue = (SysUser) Optional.ofNullable(redisTemplate.opsForValue().get("stringValue")).orElse(new SysUser());
        logger.info("stringValue{}", JsonUtils.toJson(stringValue));

        try {
            String key = stringRedisTemplate.opsForValue().get("key");
            logger.info("key{}", key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stringRedisTemplate.opsForValue().append("key", "appendValue");
        String stringValueAppend = stringRedisTemplate.opsForValue().get("key");
        System.out.println("通过append(K key, String value)方法修改后的字符串:" + stringValueAppend);

        String cutString = stringRedisTemplate.opsForValue().get("key", 0, 3);
        System.out.println("通过get(K key, long start, long end)方法获取截取的字符串:" + cutString);

        String oldAndNewStringValue = stringRedisTemplate.opsForValue().getAndSet("key", "ccc");
        System.out.print("通过getAndSet(K key, V value)方法获取原来的值：" + oldAndNewStringValue);
        String newStringValue = stringRedisTemplate.opsForValue().get("key");
        System.out.println("修改过后的值:" + newStringValue);

        stringRedisTemplate.opsForValue().set("absentKey","dd",1);
        String overrideString = stringRedisTemplate.opsForValue().get("absentKey");
        System.out.println("通过set(K key, V value, long offset)方法覆盖部分的值:"+overrideString);

    }
}
