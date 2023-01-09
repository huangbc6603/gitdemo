package org.example.rest;

import org.example.dto.FinanceInterestStatementListVo;
import org.example.dto.QueryFinanceInterestStatementDto;
import org.example.dto.UserDTO;
import org.example.entity.SysUser;
import org.example.utils.PageResp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Derek-huang
 */
public interface SelectService {

    PageResp<SysUser> selectAllUser(UserDTO user);

    SysUser selectByUserId(String stuId);

    String importTemplateField(MultipartFile file, String userName);

    void testTransaction();

    List<FinanceInterestStatementListVo> getInterestList(Integer current, Integer size, QueryFinanceInterestStatementDto dto);
}