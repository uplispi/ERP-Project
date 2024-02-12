package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.WarehouseGiftSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseGiftSheetPO{

    // id 格式为 "KCZPD-yyyyMMDD-xxxxx"
    String id;

    // 操作员
    String operator;

    // 状态 三种状态： 待审批、审批通过、审批失败
    WarehouseGiftSheetState state;

    // 创建时间
    Date createTime;

    // 顾客id
    Integer customerId;

    // 关联的销售单
    String saleSheetId;

}