package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.entity.SysUser;
import org.example.entity.SysUserExample;

import java.util.List;

public interface SysUserMapper {
    long countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(SysUser row);

    int insertSelective(SysUser row);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("row") SysUser row, @Param("example") SysUserExample example);

    int updateByExample(@Param("row") SysUser row, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser row);

    int updateByPrimaryKey(SysUser row);
}