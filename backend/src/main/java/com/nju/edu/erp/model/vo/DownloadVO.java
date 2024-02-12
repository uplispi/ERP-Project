package com.nju.edu.erp.model.vo;

import com.nju.edu.erp.enums.SheetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadVO {
    private String beginDate;//起始日期
    private String endDate;//终止日期
    private SheetType sheetType;//单据类型
    private Integer customer;//客户
    private String  salesMan;//业务员
}
