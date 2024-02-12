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
public class CollectionSheetContentPO {
    // id 自增
    Integer id;

    // 银行账户
    Integer accountId;

    // 收款单id
    String collectionSheetId;

    // 转账金额
    BigDecimal total;

    // 备注
    String remark;
}
