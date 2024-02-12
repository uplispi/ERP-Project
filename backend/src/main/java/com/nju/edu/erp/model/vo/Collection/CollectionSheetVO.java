package com.nju.edu.erp.model.vo.Collection;

import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
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
public class CollectionSheetVO {
    // 单据编号
    String id;

    // 客户id
    Integer customer;

    // 操作员
    String operator;

    // 总额
    BigDecimal total;

    // 状态
    CollectionSheetState state;

    // 转账列表
    List<CollectionSheetContentVO> collectionSheetContentList;
}
