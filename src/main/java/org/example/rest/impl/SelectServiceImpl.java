package org.example.rest.impl;

import com.github.pagehelper.page.PageMethod;
import org.example.dao.SysUserBaseMapper;
import org.example.dao.SysUserMapper;
import org.example.dto.UserDTO;
import org.example.entity.SysUser;
import org.example.entity.SysUserExample;
import org.example.rest.SelectService;
import org.example.utils.PageResp;
import org.example.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Derek-huang
 */
@Service
public class SelectServiceImpl implements SelectService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserBaseMapper sysUserBaseMapper;

    @Override
    public List<SysUser> selectAllUser() {
        return sysUserMapper.selectByExample(new SysUserExample());
    }

    @Override
    public SysUser selectByUserId(String stuId) {
        return sysUserMapper.selectByPrimaryKey("1");
    }

    @Override
    public List<SysUser> selectUserByName(String name) {
        return sysUserBaseMapper.selectUserByName(name);
    }

    @Override
    public PageResp<SysUser> selectAllUser(UserDTO user) {
        PageMethod.offsetPage(user.getStartIndex(), user.getPageSize());
        List<SysUser> sysUsers = sysUserBaseMapper.listByQuery(user);
        return PageUtil.getPageResp(sysUsers, sysUsers);
    }
}
