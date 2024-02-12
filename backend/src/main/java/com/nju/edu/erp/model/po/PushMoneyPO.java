package com.nju.edu.erp.model.po;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * /*@Author: Han
 * /*@Description:员工提成表
 * /*@Date: Created in 10:07 2022/6/29
 * /*@Version:1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushMoneyPO {
    /**
     *员工id
     */
    private Integer empId;

    /**
     * 提成
     */
    private BigDecimal pushMoney;
}
