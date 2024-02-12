package com.nju.edu.erp.enums.sheetState;

import com.nju.edu.erp.enums.BaseEnum;

public enum SheetState  implements BaseEnum<SheetState, String> {
    DRAFT("草稿"),
    PENDING_LEVEL_1("待一级审批"),
    PENDING_LEVEL_2("待二级审批"),
    SUCCESS("审批完成"),
    FAILURE("审批失败");

    private final String value;

    SheetState(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
