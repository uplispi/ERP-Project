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
// 银行账户
public class AccountPO {
    // id 自增
    Integer id;
    // 姓名
    String name;
    // 余额
    BigDecimal balance;
}
