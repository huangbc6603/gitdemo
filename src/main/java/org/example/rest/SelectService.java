package org.example.rest;

import org.example.dto.UserDTO;
import org.example.entity.SysUser;
import org.example.utils.PageResp;

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

    PageResp<SysUser> selectAllUser(UserDTO user);


}