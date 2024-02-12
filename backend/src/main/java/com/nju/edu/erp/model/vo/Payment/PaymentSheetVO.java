package com.nju.edu.erp.model.vo.Payment;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSheetVO {
    // 单据编号： SKD-yyyyMMdd-xxxxx
    String id;

    // 客户: 同时包含供应商和销售商 的id
    Integer customer;

    // 操作员
    String operator;

    // 总额
    BigDecimal total;

    // 状态
    PaymentSheetState state;

    // 转账列表
    List<PaymentSheetContentVO> paymentSheetContentList;

}
