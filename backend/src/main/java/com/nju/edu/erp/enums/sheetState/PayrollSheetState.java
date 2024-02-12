package com.nju.edu.erp.enums.sheetState;

import com.nju.edu.erp.enums.BaseEnum;

public enum PayrollSheetState implements BaseEnum<PurchaseSheetState, String> {

    PENDING_LEVEL_1("待一级审批"), // 人力资源
    PENDING_LEVEL_2("待二级审批"), // 待总经理审批
    SUCCESS("审批完成"),
    FAILURE("审批失败");

    private final String value;

    PayrollSheetState(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
