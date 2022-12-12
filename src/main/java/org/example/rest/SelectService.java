package org.example.rest;

import org.example.entity.SysUser;

import java.util.List;

/**
 * @author Derek-huang
 */
public interface SelectService {

    List<SysUser> selectAllUser();

    SysUser selectByUserId(String stuId);

}