package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearEndBonusPO {
    private Integer year;//年份
    private Integer employId;//员工id
    private BigDecimal yearEndBonus;//年薪
}
