package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSheetContentPO<DigDecimal> {
    Integer id; // 自增

    // 银行账户
    Integer accountId;

    // 收款单id
    String paymentSheetId;

    // 转账金额
    BigDecimal total;

    // 备注
    String remark;
}
