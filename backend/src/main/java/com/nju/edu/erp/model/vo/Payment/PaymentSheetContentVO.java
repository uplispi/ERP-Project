package com.nju.edu.erp.model.vo.Payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSheetContentVO {
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
