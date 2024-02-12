package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearEndBonusVO {
    Integer year;//年份
    Integer employId;//员工id
    BigDecimal yearEndBonus;//年薪
}
