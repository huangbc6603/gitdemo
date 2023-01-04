package org.example.rest.impl;

import com.github.pagehelper.page.PageMethod;
import org.example.dao.SysUserBaseMapper;
import org.example.dao.SysUserMapper;
import org.example.dto.UserDTO;
import org.example.entity.SysUser;
import org.example.rest.SelectService;
import org.example.utils.PageResp;
import org.example.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Derek-huang
 */
@Service
public class SelectServiceImpl implements SelectService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserBaseMapper sysUserBaseMapper;

    @Override
    public PageResp<SysUser> selectAllUser(UserDTO user) {
        PageMethod.offsetPage(user.getStartIndex(), user.getPageSize());
        List<SysUser> sysUsers = sysUserBaseMapper.listByQuery(user);
        return PageUtil.getPageResp(sysUsers, sysUsers);
    }

    @Override
    public SysUser selectByUserId(String stuId) {
        return sysUserMapper.selectByPrimaryKey("1");
    }
}
