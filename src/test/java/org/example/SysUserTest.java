package org.example;

import org.example.dto.Result;
import org.example.dto.UserDTO;
import org.example.entity.SysUser;
import org.example.rest.SelectService;
import org.example.utils.JsonUtils;
import org.example.utils.PageResp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Derek-huang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserTest {

    @Autowired
    private SelectService selectService;

    @Test
    public void demo(){
        List<SysUser> sysUsers = selectService.selectAllUser();
        System.out.println(JsonUtils.toJson(sysUsers));
    }

    @Test
    public void selectAllUser(){
        UserDTO user = new UserDTO();
        user.setStartIndex(2);
        user.setPageSize(1);
        PageResp<SysUser> pageResp = selectService.selectAllUser(user);
        Result<PageResp<SysUser>> success = Result.success(pageResp);
    }

    @Test
    public void test(){
        List<SysUser> sysUsers = selectService.selectUserByName("derek.huang");
        System.out.println(sysUsers);
    }
}
