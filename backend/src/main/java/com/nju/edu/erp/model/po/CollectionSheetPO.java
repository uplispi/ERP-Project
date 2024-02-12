package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
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
public class CollectionSheetPO {
    // 单据编号
    String id;

    // 客户id
    Integer customer;

    // 操作员
    String operator;

    // 状态
    CollectionSheetState state;

    // 总额
    BigDecimal total;

    // 创建时间
    Date createTime;
}
