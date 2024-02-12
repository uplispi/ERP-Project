package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailPO {
    Integer id;//员工编号
    PostType type;//薪资类型
    BigDecimal basic;//基本工资
    BigDecimal push;//提成
    BigDecimal bonus;//年终奖
    Integer times;//打卡次数
    String name;//名字
    String bankCard;//卡号
}
