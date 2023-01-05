package org.example.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;

/**
 * @author Derek-huang
 */
public class TemplateFieldExcelDTO implements IExcelDataModel {
    @Excel(name = "字段名称（中文）*")
    private String fieldCnName;
    @Excel(name = "字段名称（英文）*")
    private String fieldEnName;
    @Excel(name = "业务类型*")
    private String businessType;
    @Excel(name = "是否必填*")
    private String required;
    @Excel(name = "字段类型*")
    private String fieldType;
    @Excel(name = "数字保留位数", fixedIndex = 5)
    private String keepDecimals;
    @Excel(name = "值列表数据字段", fixedIndex = 6)
    private String lovCode;
    //行号
    private Integer rowNum;

    public String getFieldCnName() {
        return fieldCnName;
    }

    public void setFieldCnName(String fieldCnName) {
        this.fieldCnName = fieldCnName;
    }

    public String getFieldEnName() {
        return fieldEnName;
    }

    public void setFieldEnName(String fieldEnName) {
        this.fieldEnName = fieldEnName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getKeepDecimals() {
        return keepDecimals;
    }

    public void setKeepDecimals(String keepDecimals) {
        this.keepDecimals = keepDecimals;
    }

    public String getLovCode() {
        return lovCode;
    }

    public void setLovCode(String lovCode) {
        this.lovCode = lovCode;
    }

    @Override
    public Integer getRowNum() {
        return rowNum;
    }

    @Override
    public void setRowNum(Integer i) {
        this.rowNum = i;
    }
}
