package org.example.rest;

import org.example.dto.UserDTO;
import org.example.entity.SysUser;
import org.example.utils.PageResp;

/**
 * @author Derek-huang
 */
public interface SelectService {

    PageResp<SysUser> selectAllUser(UserDTO user);

    SysUser selectByUserId(String stuId);

}