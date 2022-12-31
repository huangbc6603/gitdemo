package org.example.rest;

import org.apache.ibatis.annotations.Param;
import org.example.entity.SysUser;

import java.util.List;

/**
 * @author Derek-huang
 */
public interface SelectService {

    /**
     * 查询所有用户
     * @return
     */
    List<SysUser> selectAllUser();

    /**
     * 根据用户id查询用户
     * @param stuId
     * @return
     */
    SysUser selectByUserId(String stuId);

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    List<SysUser> selectUserByName(String name);

}