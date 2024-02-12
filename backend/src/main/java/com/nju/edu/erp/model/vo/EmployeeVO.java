package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * /*@Author: Han
 * /*@Description:
 * /*@Date: Created in 22:30 2022/6/28
 * /*@Version:1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeVO {
    /**
     * 员工id，入职日期(yyyyMMdd)+index
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 联系电话
     */
    private String phoneNum;
    /**
     * 住址
     */
    private String address;
    /**
     * 岗位编号
     */
    private Integer postId;
    /**
     * 银行卡号
     */
    private String bankCard;
    /**
     * 入职日期
     */
    private Date hiredate;
    /**
     * 岗位名
     */
    private String postName;
}
