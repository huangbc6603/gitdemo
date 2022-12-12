package org.example.rest.impl;

import org.example.dao.SysUserMapper;
import org.example.entity.SysUser;
import org.example.entity.SysUserExample;
import org.example.rest.SelectService;
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

    @Override
    public List<SysUser> selectAllUser() {
        return sysUserMapper.selectByExample(new SysUserExample());
    }

    @Override
    public SysUser selectByUserId(String stuId) {
        return sysUserMapper.selectByPrimaryKey("1");
    }
}
