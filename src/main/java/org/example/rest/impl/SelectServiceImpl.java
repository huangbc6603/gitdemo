package org.example.rest.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.github.pagehelper.page.PageMethod;
import org.apache.commons.collections4.CollectionUtils;
import org.example.dao.SysRoleMapper;
import org.example.dao.SysUserBaseMapper;
import org.example.dao.SysUserMapper;
import org.example.dto.TemplateFieldExcelDTO;
import org.example.dto.UserDTO;
import org.example.entity.SysRole;
import org.example.entity.SysUser;
import org.example.exception.BaseServiceException;
import org.example.exception.BizServiceException;
import org.example.rest.SelectService;
import org.example.utils.PageResp;
import org.example.utils.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Derek-huang
 */
@Service
public class SelectServiceImpl implements SelectService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserBaseMapper sysUserBaseMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageResp<SysUser> selectAllUser(UserDTO user) {
        PageMethod.offsetPage(user.getStartIndex(), user.getPageSize());
        List<SysUser> sysUsers = sysUserBaseMapper.listByQuery(user);
        return PageUtil.getPageResp(sysUsers, sysUsers);
    }

    @Override
    public SysUser selectByUserId(String stuId) {
        return sysUserMapper.selectByPrimaryKey("1");
    }

    @Override
    public String importTemplateField(MultipartFile file, String userName) {
        ImportParams params = new ImportParams();
        List<TemplateFieldExcelDTO> templateFieldExcelDTOS;
        try {
            templateFieldExcelDTOS = ExcelImportUtil.importExcel(file.getInputStream(), TemplateFieldExcelDTO.class, params);
        } catch (Exception e) {
            logger.error("导入模板字段错误", e);
            throw new BaseServiceException("400", "导入模板字段错误，请联系IT处理");
        }

        checkExcelEmpty(templateFieldExcelDTOS);
        //某些特殊场景下（如表格删了数据，但是没有删干净）会导致解析出来空的数据，将这种数据删除掉
        templateFieldExcelDTOS.removeIf(nextFieldExcelDto -> nextFieldExcelDto.getFieldCnName() == null
                && nextFieldExcelDto.getFieldEnName() == null && nextFieldExcelDto.getFieldType() == null
                && nextFieldExcelDto.getBusinessType() == null && nextFieldExcelDto.getRequired() == null
                && nextFieldExcelDto.getKeepDecimals() == null && nextFieldExcelDto.getLovCode() == null);
        checkExcelEmpty(templateFieldExcelDTOS);

        if (templateFieldExcelDTOS.size() > 100) {
            throw new BaseServiceException("400", "导入数据不允许超过100行，请检查后重新导入");
        }
        return "导入成功";
    }

    private void checkExcelEmpty(List<TemplateFieldExcelDTO> templateFieldExcelDTOS) {
        if (CollectionUtils.isEmpty(templateFieldExcelDTOS)) {
            throw new BaseServiceException("400", "导入数据为空，请检查后重新导入");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTransaction() {
        SysUser sysUser = new SysUser();
        sysUser.setUserId("2");
        sysUser.setName("zhangsan");
        int i = sysUserMapper.updateByPrimaryKey(sysUser);
        SysRole sysRole = new SysRole();
        sysRole.setRoleId("24");
        sysRole.setName("HRBP");
        sysRoleMapper.updateByPrimaryKey(sysRole);
        SysUser user = sysUserMapper.selectByPrimaryKey("2");
        logger.info("user>>>>{}", user.getName());
        if (i == 1) {
            throw new BizServiceException("400", "testTransaction success!");
        }
    }
}
