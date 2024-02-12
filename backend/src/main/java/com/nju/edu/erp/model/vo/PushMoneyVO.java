package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 10:10 2022/6/29
 * /*@Version:1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushMoneyVO {
    /**
     *员工id
     */
    private Integer empId;

    /**
     * 提成
     */
    private BigDecimal pushMoney;
}
