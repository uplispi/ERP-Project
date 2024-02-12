package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 9:33 2022/6/29
 * /*@Version:1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostPO {
    /**
     * 岗位id
     */
    private Integer id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 基本工资
     */
    private BigDecimal basicWage;

    /**
     * 岗位工资
     */
    private BigDecimal postWage;

    /**
     * 岗位薪资类型
     */
    private PostType type;
}
