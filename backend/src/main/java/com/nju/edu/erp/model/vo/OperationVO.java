package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationVO {
    //销售
    private BigDecimal totalBeforeDiscount;//折让前总额
    private BigDecimal totalAfterDiscount;//折让后总额
    private BigDecimal totalAboutDiscount;//折让金额

    private BigDecimal totalReturns;//销售退货总额


    //商品
    private BigDecimal purchaseExpenditure;//进货支出
    private BigDecimal purchaseReturnIncome;//进货收入

    //人力
    private BigDecimal labourCosts;//人力成本


    private BigDecimal totalExpenditure;//总支出
    private BigDecimal totalRevenue;//总收入
    private BigDecimal totalAccount;//净利润
}
