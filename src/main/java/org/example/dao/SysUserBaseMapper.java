package org.example.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.example.dto.UserDTO;
import org.example.entity.SysUser;

import java.util.List;

/**
 * @author Derek-huang
 */
public interface SysUserBaseMapper extends SysUserMapper{

    @Select("SELECT * FROM sys_user t where t.name= #{name}")
    @ResultType(org.example.entity.SysUser.class)
    List<SysUser> selectUserByName(@Param("name") String name);

    List<SysUser> listByQuery(UserDTO user);

}
