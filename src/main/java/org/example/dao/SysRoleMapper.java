package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.example.entity.SysRole;
import org.example.entity.SysRoleExample;

import java.util.List;

public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(SysRole row);

    int insertSelective(SysRole row);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("row") SysRole row, @Param("example") SysRoleExample example);

    int updateByExample(@Param("row") SysRole row, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole row);

    int updateByPrimaryKey(SysRole row);
}