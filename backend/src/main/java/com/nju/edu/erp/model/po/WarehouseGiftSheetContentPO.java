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
public class WarehouseGiftSheetContentPO{
    // id
    Integer id;

    // 关联的库存赠品单
    String warehouseGiftSheetId;

    // 商品id
    private String pid;

    //数量
    private Integer quantity;

    // 单价
    private BigDecimal unitPrice;

    // 备注
    private String remark;
}
