package org.example.dto;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Derek-huang
 */
@Data
@Accessors(chain = true)
@ApiModel("利息情况vo")
public class FinanceInterestStatementListVo implements Serializable {

    @Excel(name = "序号", orderNum = "0", width = 15)
    @ApiModelProperty("序号")
    private String sort;

    @Excel(name = "借款人", orderNum = "1", width = 15)
    @ApiModelProperty("借款人")
    private String borrower;

    @Excel(name = "联系电话", orderNum = "2", width = 15)
    @ApiModelProperty("联系电话")
    private String phone;

    @Excel(name = "合同编号", orderNum = "3", width = 15)
    @ApiModelProperty("合同编号")
    private String contractNo;

    @Excel(name = "管理类别", orderNum = "4", width = 15)
    @ApiModelProperty("管理类别")
    private String managementCategory;

    @Excel(name = "贷款种类", orderNum = "5", width = 15)
    @ApiModelProperty("贷款种类")
    private String loanTypes;

    @Excel(name = "期限", orderNum = "6", width = 15)
    @ApiModelProperty("期限")
    private String timeLimit;

    @Excel(name = "贷款起止日期", orderNum = "7", width = 15)
    @ApiModelProperty("贷款起止日期")
    private String loanStartStop;

    @Excel(name = "借款金额", orderNum = "8", width = 15)
    @ApiModelProperty("借款金额")
    private BigDecimal borrowingAmount;

    @Excel(name = "计息金额", orderNum = "9", width = 15)
    @ApiModelProperty("计息金额")
    private BigDecimal determinedAmount;

    @Excel(name = "合同月利率", orderNum = "10", width = 15)
    @ApiModelProperty("合同月利率")
    private BigDecimal monthlyRate;

    @Excel(name = "咨询费率", orderNum = "11", width = 15)
    @ApiModelProperty("咨询费率")
    private BigDecimal consultingRate;

    @Excel(name = "起息日", orderNum = "12", width = 15)
    @ApiModelProperty("起息日")
    private String breathDay;

    @Excel(name = "止息日", orderNum = "13", width = 15)
    @ApiModelProperty("止息日")
    private String restDay;

    @Excel(name = "收息日", orderNum = "14", width = 15)
    @ApiModelProperty("收息日")
    private String getDay;

    @Excel(name = "合同利息(元)", groupName = "应收利息（元）", orderNum = "15", width = 15)
    @ApiModelProperty("合同利息(元)")
    private BigDecimal contractInterest;

    @Excel(name = "咨询费(元)", groupName = "应收利息（元）", orderNum = "16", width = 15)
    @ApiModelProperty("咨询费(元)")
    private BigDecimal consultingFees;

    @Excel(name = "应收合计(元)", groupName = "应收利息（元）", orderNum = "17", width = 15)
    @ApiModelProperty("应收利息-应收合计(元)")
    private BigDecimal receivableAmount;

    @Excel(name = "实际收息日", orderNum = "18")
    @ApiModelProperty("实际收息日")
    private String actualGetDay;

    @Excel(name = "实收利息(元)", groupName = "实收利息（元）", orderNum = "19", width = 15)
    @ApiModelProperty("实收利息(元)")
    private BigDecimal actualInterest;

    @Excel(name = "实收咨询费(元)", groupName = "实收利息（元）", orderNum = "20", width = 15)
    @ApiModelProperty("实收咨询费(元)")
    private BigDecimal actualConsultingFees;

    @Excel(name = "实收合计(元)", groupName = "实收利息（元）", orderNum = "21", width = 15)
    @ApiModelProperty("实收利息-实收合计(元)")
    private BigDecimal actualReceivableAmount;

    @Excel(name = "未收利息(元)", groupName = "未收利息（元）", orderNum = "22", width = 15)
    @ApiModelProperty("未收利息(元)")
    private BigDecimal notReceiveInterest;

    @Excel(name = "未收咨询费(元)", groupName = "未收利息（元）", orderNum = "23", width = 15)
    @ApiModelProperty("未收咨询费(元)")
    private BigDecimal notReceiveConsultingFees;

    @Excel(name = "未收合计(元)", groupName = "未收利息（元）", orderNum = "24", width = 15)
    @ApiModelProperty("未收利息-未收合计(元)")
    private BigDecimal notReceiveReceivableAmount;
}





