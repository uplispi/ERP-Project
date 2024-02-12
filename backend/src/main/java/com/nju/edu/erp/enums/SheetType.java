package com.nju.edu.erp.enums;

public enum SheetType  implements BaseEnum<CustomerType, String>{

    SALE_SHEET("销售单"),
    SALE_RETURNS_SHEET("销售退货单"),
    PURCHASE_SHEET("进货单"),
    PURCHASE_RETURNS_SHEET("进货退货单"),
    PAYMENT_SHEET("付款单"),
    COLLECTION_SHEET("收款单"),
    PAYROLL_SHEET("工资单");

    private final String value;

    SheetType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
