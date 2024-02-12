package com.nju.edu.erp.model.vo;


import com.nju.edu.erp.enums.sheetState.PayrollSheetState;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.enums.sheetState.SheetState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayrollVO {
    private Date createTime;//创建日期
    private String operator;//操作员
    private String id;//单据编号
    private String name;//员工名join
    private Integer employeeId;//员工id
    private String bankCard;//银行卡号
    private BigDecimal payableWages;//应发工资
    private BigDecimal unemploymentInsurance;//失业保险
    private BigDecimal personalIncomeTax;//个人所得税
    private BigDecimal housingProvidentFund;//住房公积金
    private BigDecimal actualAmountIssued;//实发金额
    private SheetState state;//状态
    private BigDecimal yearEndBonus;//年终奖
    private Integer Attendance;//考勤
    private Integer times;
}
