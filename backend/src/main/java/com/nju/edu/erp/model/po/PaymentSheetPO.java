package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSheetPO {
    // 单据编号： FKD-yyyyMMdd-xxxxx
    String id;

    // 客户: 同时包含供应商和销售商
    Integer customer;

    // 操作员
    String operator;

    // 总额
    BigDecimal total;

    // 状态
    PaymentSheetState state;

    // 创建时间
    Date createTime;
}
