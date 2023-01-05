package org.example.rest.impl;

import base.ServiceMockTestBase;
import org.example.dao.SysUserBaseMapper;
import org.example.dto.UserDTO;
import org.example.entity.SysUser;
import org.example.utils.PageResp;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author Derek-huang
 */
public class SelectServiceImplTest extends ServiceMockTestBase {

    @InjectMocks
    private SelectServiceImpl selectService;

    @Mock
    private SysUserBaseMapper sysUserBaseMapper;

    @Test
    public void should_result_success_when_selectAllUser() {
        UserDTO user = new UserDTO();
        user.setName("derek");
        user.setPassword("123admin");

        List<SysUser> sysUsers = new ArrayList<>();
        SysUser sysUser = new SysUser();
        sysUser.setName("name");
        sysUsers.add(sysUser);
        Mockito.when(sysUserBaseMapper.listByQuery(any())).thenReturn(sysUsers);
        PageResp<SysUser> pageResp = selectService.selectAllUser(user);

        Assert.assertEquals("name", pageResp.getDatas().get(0).getName());
        Mockito.verify(sysUserBaseMapper, Mockito.times(1)).listByQuery(any());
    }

    @Test
    public void selectByUserId() {
    }

    @Test
    public void importTemplateField() {
    }
}